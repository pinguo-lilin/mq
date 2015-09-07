package pinguo.rocket.mq.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import pinguo.rocket.mq.comm.ApplicationContextUtil;
import pinguo.rocket.mq.entity.ResponseData;

@Controller
@RequestMapping(value = "msg")
public class MessageController {

	private final static Logger logger = LoggerFactory.getLogger(MessageController.class);

	@RequestMapping(value = "send", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData send(HttpServletRequest request, HttpServletResponse response) {

		DefaultMQProducer producer = (DefaultMQProducer) ApplicationContextUtil.getBean("PinGuoProducer");
		String topic = request.getParameter("topic");
		String tag = request.getParameter("tag");
		String key = request.getParameter("key");
		String info = request.getParameter("info");

		Message text = null;
		SendResult sendResult = null;
		ResponseData responseData = new ResponseData();
		try {
			// 普通发送
			if (key == null || key.isEmpty()) {
				text = new Message(topic, tag, info.getBytes());
			// 顺序发送
			} else {
				text = new Message(topic, tag, key, info.getBytes());
			}
			sendResult = producer.send(text);
			
			if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
				responseData.setStatus(200);
				responseData.setMessage("消息发送成功");
				logger.trace("mq消息发送成功，topic=" + topic + " tag=" + tag + " info" + info);
			}
		} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
			logger.error("mq消息发送失败，topic=" + topic + " tag=" + tag + " info" + info + " error=" + e.getMessage());
			responseData.setStatus(206);
			e.printStackTrace();
		}
		return responseData;
	}

	@RequestMapping(value = "showPage", method = RequestMethod.GET)
	public String showSendPage(Model model) {
		return "show_page";
	}
}
