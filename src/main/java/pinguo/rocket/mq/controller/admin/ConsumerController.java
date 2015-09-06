package pinguo.rocket.mq.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pinguo.rocket.mq.entity.Consumer;
import pinguo.rocket.mq.entity.Routing;
import pinguo.rocket.mq.entity.Subscribe;
import pinguo.rocket.mq.service.ConsumerService;
import pinguo.rocket.mq.service.RoutingService;
import pinguo.rocket.mq.service.SubscribeService;

/**
 * 后台consumer配置管理
 */
@Controller
@RequestMapping(value = "/consumer")
public class ConsumerController {
	
	@Resource
	ConsumerService consumerService;
	
	@Resource
	SubscribeService subscribeService;
	
	@Resource
	RoutingService routingService;
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add() {
		return "/admin/consumer_add";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request, HttpServletResponse response){
		
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		
		Consumer consumer = new Consumer();
		consumer.setName(name);
		consumer.setType(type);
		consumerService.addConsumer(consumer);
		int id = consumer.getId();
		if(id != 0){
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model){
		List<Consumer> consumers = consumerService.list();
		
		for (Consumer consumer : consumers) {
			int cid = consumer.getId();
			List<Subscribe> subscribes= subscribeService.findByCid(cid);
			List<Routing> routings = routingService.findByCid(cid);
			
			consumer.setRoutings(routings);
			consumer.setSubscribes(subscribes);
		}
		model.addAttribute("consumerList", consumers);
		return "/admin/consumer_list";
	}
	
	@RequestMapping(value = "addSubscribe/{cid}", method = RequestMethod.GET)
	public String addSubscribe(Model model, @PathVariable int cid){
		model.addAttribute("cid", cid);
		return "/admin/add_subscribe";
	}
	
	@RequestMapping(value = "saveSubscribe", method = RequestMethod.POST)
	@ResponseBody
	public String saveSubscribe(HttpServletRequest request, HttpServletResponse response){
		int cid = Integer.parseInt(request.getParameter("cid"));
		String topic = request.getParameter("topic");
		String tag = request.getParameter("tag");
		
		Subscribe subscribe = new Subscribe();
		subscribe.setCid(cid);
		subscribe.setTopic(topic);
		subscribe.setTag(tag);
		
		subscribeService.addSubscribe(subscribe);
		
		int id = subscribe.getId();
		if(id != 0){
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value = "addRouting/{cid}", method = RequestMethod.GET)
	public String addRouting(Model model, @PathVariable int cid){
		model.addAttribute("cid", cid);
		return "/admin/add_routing";
	}
	
	@RequestMapping(value = "saveRouting", method = RequestMethod.POST)
	@ResponseBody
	public String saveRouting(HttpServletRequest request, HttpServletResponse response){
		int cid = Integer.parseInt(request.getParameter("cid"));
		String tag = request.getParameter("tag");
		String url = request.getParameter("url");
		
		Routing routing = new Routing();
		routing.setCid(cid);
		routing.setTag(tag);
		routing.setUrl(url);
		
		routingService.addRouting(routing);
		
		int id = routing.getId();
		if(id != 0){
			return "true";
		}
		return "false";
	}
}
