package pinguo.rocket.mq.component;

import org.springframework.beans.factory.InitializingBean;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;

import pinguo.rocket.mq.comm.ApplicationContextUtil;

public class InitRocketMq implements InitializingBean {

	/**
	 * spring所有bean初始化完后，执行该方法
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("init rocket mq......");
		DefaultMQPushConsumer defaultMQPushConsumer = (DefaultMQPushConsumer) ApplicationContextUtil
				.getBean("PinGuoPushConsumer");
		System.out.println("namesvrAddr" + defaultMQPushConsumer.getNamesrvAddr());
	}
}
