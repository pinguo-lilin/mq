package pinguo.rocket.mq.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import pinguo.rocket.mq.comm.ApplicationContextUtil;
import pinguo.rocket.mq.entity.Subscribe;

public class InitRocketMq implements InitializingBean {

	/**
	 * spring所有bean初始化完后，执行该方法
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("init rocket mq ......");
		List<String> pinguoConsumers = new ArrayList<String>();
		Map<String, List<Subscribe>> pinguoSubscribes = new HashMap<String, List<Subscribe>>();
		Map<String, String> routings = new HashMap<String, String>();
		
		//初始化
		pinguoConsumers.add("PinGuoPushConsumerOne");
		pinguoConsumers.add("PinGuoPushConsumerTwo");
		
		Subscribe subscribeOne = new Subscribe("topicOne", "tagA");
		Subscribe subscribeOne2 = new Subscribe("topicOne2", "tagB");
		Subscribe subscribeTwo = new Subscribe("topicTwo", "tagC");
		List<Subscribe> subscribesOne = new ArrayList<Subscribe>();
		List<Subscribe> subscribesTwo = new ArrayList<Subscribe>();
		subscribesOne.add(subscribeOne);
		subscribesOne.add(subscribeOne2);
		subscribesTwo.add(subscribeTwo);
		pinguoSubscribes.put("PinGuoPushConsumerOne", subscribesOne);
		pinguoSubscribes.put("PinGuoPushConsumerTwo", subscribesTwo);
		
		routings.put("tagA", "http://www.camera360.com/tagA");
		routings.put("tagB", "http://www.camera360.com/tagB");
		routings.put("tagC", "http://www.camera360.com/tagC");
		
		
		
		
		for (String consumer : pinguoConsumers) {
			Boolean isCreate = ApplicationContextUtil.contain(consumer);
			if(isCreate == false){
				System.out.println("consumer bean未创建， name=" + consumer);
				continue;
			}
			DefaultMQPushConsumer pushConsumer = (DefaultMQPushConsumer) ApplicationContextUtil
					.getBean(consumer);
			if(pinguoSubscribes.containsKey(consumer) == false){
				System.out.println("consumer subscribes,不存在");
				pushConsumer = null;
				continue;
			}
			List<Subscribe> subscribes = pinguoSubscribes.get(consumer);
			for (Subscribe  subscribe: subscribes) {
				pushConsumer.subscribe(subscribe.getTopic(), subscribe.getTags());
			}
			pushConsumer.registerMessageListener(new MessageListenerConcurrently() {

				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
					System.out.println("开始消费");
					MessageExt msgExt = msgs.get(0);
					byte[] msg = msgExt.getBody();
					String tag = msgExt.getTags();
					String strMsg = new String(msg);
					if (routings.containsKey(tag) == false) {
						System.out.println("routing未找到，忽略消息");
						return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
					}
					// 消费
					System.out.println("消费消息，tag=" + tag + " msg=" + strMsg + " routeUrl=" + routings.get(tag));
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				}
			});
			
			ConsumerThread consumerThread = new ConsumerThread(pushConsumer);
			Thread thread = new Thread(consumerThread);
			thread.start();
		}
	}
}
