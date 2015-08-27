package pinguo.rocket.mq.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

import pinguo.rocket.mq.comm.ApplicationContextUtil;
import pinguo.rocket.mq.entity.User;

@Controller
@RequestMapping(value = "msg")
public class MessageController {

	@RequestMapping(value = "send", method = RequestMethod.GET)
	@ResponseBody
	public User send() {
		DefaultMQProducer producer = (DefaultMQProducer) ApplicationContextUtil.getBean("PinGuoProducer");
		System.out.println(producer.toString());
		System.out.println(producer.getSendMsgTimeout());
		User user = new User();
		user.setName("stelin博弈");
		user.setAge(19);
		user.setContent("my name is boby");
		
		String str = "[{name:'a',value:'aa'},{name:'b',value:'bb'},{name:'c',value:'cc'},{name:'d',value:'dd'}]" ;  // 一个未转化的字符串
		JSONArray jsonAry = JSON.parseArray(str);
		
		for (Object object : jsonAry) {
			Map map = (Map) object;
			System.out.println("name"+map.get("name"));
			System.out.println("value"+map.get("value"));
		}
		
		return user;
	}
}
