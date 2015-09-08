package pinguo.rocket.mq.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pinguo.rocket.mq.comm.BeanManage;
import pinguo.rocket.mq.entity.Consumer;
import pinguo.rocket.mq.service.ConsumerService;

@Controller
@RequestMapping(value = "/web")
public class WebController {

	@Resource
	ConsumerService consumerService;
	
	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	@ResponseBody
	public String refreshBeans(){
		List<String> pinguoConsumers = new ArrayList<String>();
		List<Consumer> consumers = consumerService.list();
		for (Consumer consumer : consumers) {
			pinguoConsumers.add(consumer.getName());
		}
		BeanManage.refreshBeans(pinguoConsumers);
		
		return "true";
	}
}
