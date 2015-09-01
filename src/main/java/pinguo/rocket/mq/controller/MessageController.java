package pinguo.rocket.mq.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import pinguo.rocket.mq.comm.ApplicationContextUtil;

@Controller
@RequestMapping(value = "msg")
public class MessageController {

	@RequestMapping(value = "send", method = RequestMethod.POST)
	@ResponseBody
	public String send(@Param(value = "") String topic, @Param(value = "") String tag, @Param(value = "") String msg) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
		DefaultMQProducer producer = (DefaultMQProducer) ApplicationContextUtil.getBean("PinGuoProducer");
		System.out.println(producer.toString());
		System.out.println(producer.getSendMsgTimeout());
		System.out.println(producer.getNamesrvAddr());
		
		System.out.println("topic="+topic);
		System.out.println("tag="+tag);
		System.out.println("msg="+msg);
		producer.start();
		Message text = new Message(topic, tag, msg.getBytes());
		SendResult sendResult = producer.send(text);
		producer.shutdown();
		System.out.println("sendResult="+sendResult.getSendStatus());
		return "true";
	}
	
	@RequestMapping(value = "showPage", method = RequestMethod.GET)
	public String showSendPage(Model model){
		return "show_page";
	}
}
