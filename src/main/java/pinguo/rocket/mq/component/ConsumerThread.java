package pinguo.rocket.mq.component;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;

public class ConsumerThread implements Runnable{

	private DefaultMQPushConsumer consumer;
	
	public ConsumerThread(DefaultMQPushConsumer consumer){
		this.consumer = consumer;
	}
	
	@Override
	public void run() {
		try {
			this.consumer.start();
			System.out.println("消费者启动成功，name="+consumer.getConsumerGroup());
		} catch (MQClientException e) {
			e.printStackTrace();
		}
	}

}
