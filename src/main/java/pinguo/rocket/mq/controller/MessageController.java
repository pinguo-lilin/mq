package pinguo.rocket.mq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

import pinguo.rocket.mq.comm.ApplicationContextUtil;

@Controller
@RequestMapping(value = "msg")
public class MessageController {

	@RequestMapping(value = "send", method = RequestMethod.GET)
	public void send() {
		DefaultMQProducer producer = (DefaultMQProducer) ApplicationContextUtil.getBean("PinGuoProducer");
		System.out.println(producer.toString());
		System.out.println(producer.getSendMsgTimeout());
	}
}
