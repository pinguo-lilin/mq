package pinguo.rocket.mq.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.MessageExt;

import pinguo.rocket.mq.comm.ApplicationContextUtil;
import pinguo.rocket.mq.comm.BeanManage;
import pinguo.rocket.mq.comm.ConsumerHelper;
import pinguo.rocket.mq.entity.Consumer;
import pinguo.rocket.mq.entity.Subscribe;
import pinguo.rocket.mq.service.ConsumerService;
import pinguo.rocket.mq.service.RoutingService;
import pinguo.rocket.mq.service.SubscribeService;

public class InitRocketMq implements InitializingBean {

	@Resource
	ConsumerService consumerService;
	
	@Resource
	SubscribeService subscribeService;
	
	@Resource
	RoutingService routingService;
	
	/**
	 * spring所有bean初始化完后，执行该方法
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("init rocket mq ......");
		
		//初始化消费者、订阅、路由信息
		List<String> pinguoConsumers = new ArrayList<String>();
		List<Consumer> consumers = consumerService.list();
		for (Consumer consumer : consumers) {
			pinguoConsumers.add(consumer.getName());
			System.out.println("addBean="+consumer.getName());
			//自动注册consumer消费者bean
			Map<String, Object> properties = ConsumerHelper.convertConsumerBeanProperties(consumer);
			BeanManage.addConsumerBeanToFactory(DefaultMQPushConsumer.class, consumer.getName(), properties);
		}
		Map<String, List<Subscribe>> pinguoSubscribes = subscribeService.listByConsumers(consumers);
		Map<String, Map<String, String>> pinguoRoutings = routingService.listByConsumers(consumers);
		
		
		//初始化producer,只需初始化一次
		DefaultMQProducer producer = (DefaultMQProducer) ApplicationContextUtil.getBean("PinGuoProducer");
		producer.start();
		
		for (String consumerName : pinguoConsumers) {
			System.out.println("initBean="+consumerName);
			Boolean isCreate = ApplicationContextUtil.contain(consumerName);
			if(isCreate == false){
				System.out.println("consumer bean未创建， name=" + consumerName);
				continue;
			}
			if(pinguoSubscribes.containsKey(consumerName) == false){
				System.out.println("consumer subscribes,不存在");
				continue;
			}
			
			DefaultMQPushConsumer pushConsumer = (DefaultMQPushConsumer) ApplicationContextUtil
					.getBean(consumerName);
			List<Subscribe> subscribes = pinguoSubscribes.get(consumerName);
			for (Subscribe  subscribe: subscribes) {
				pushConsumer.subscribe(subscribe.getTopic(), subscribe.getTag());
			}
			pushConsumer.registerMessageListener(new MessageListenerConcurrently() {

				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
					System.out.println("开始消费");
					MessageExt msgExt = msgs.get(0);
					byte[] msg = msgExt.getBody();
					String tag = msgExt.getTags();
					String strMsg = new String(msg);
					if (pinguoRoutings.get(consumerName).containsKey(tag) == false) {
						System.out.println("routing未找到，忽略消息");
						return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
					}
					// 消费
					System.out.println("消费消息，tag=" + tag + " msg=" + strMsg + " routeUrl=" + pinguoRoutings.get(consumerName).get(tag));
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				}
			});
			
			ConsumerThread consumerThread = new ConsumerThread(pushConsumer);
			Thread thread = new Thread(consumerThread);
			thread.start();
		}
	}
}
