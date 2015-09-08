package pinguo.rocket.mq.comm;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pinguo.rocket.mq.controller.MessageController;
import pinguo.rocket.mq.entity.Consumer;

public class ConsumerHelper {

	private final static Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@SuppressWarnings("serial")
	private static List<String> pushConsumerConfigs = new ArrayList<String>(){{
		add("pollNameServerInteval");
		add("heartbeatBrokerInterval");
		add("persistConsumerOffsetInterval");
		add("consumeMessageBatchMaxSize");
		add("consumeThreadMax");
	}};
	
	
	/**
	 * 消费者转换成功bean属性对象
	 * 
	 * @param consumer
	 * @return
	 */
	public static Map<String, Object> convertConsumerBeanProperties(Consumer consumer){
		Map<String, Object> properties = new HashMap<String, Object>();
		Class<? extends Consumer> clazz = consumer.getClass();
		for (String configName : pushConsumerConfigs) {
			try {
				String methodName = "get"+UtilHelper.toUpperFirstCase(configName);
				Method getMethod = clazz.getDeclaredMethod(methodName);
				Object returnValue = getMethod.invoke(consumer);
				properties.put(configName, returnValue);
			} catch (Exception e) {
				logger.error("getMethod调用失败");
				continue;
			}
		}
		return properties;
	}
}
