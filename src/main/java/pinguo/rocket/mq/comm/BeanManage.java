package pinguo.rocket.mq.comm;

import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;

public class BeanManage {

	/**
	 * 自动注册bean
	 * 
	 * @param beanClass		bean类
	 * @param beanName		bean名称
	 * @param properties	需要注册的属性
	 */
	public static void addConsumerBeanToFactory(Class<?> beanClass, String beanName, Map<String, Object> properties){
		ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) ApplicationContextUtil.getApplicationContext();
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
		 
		if(!beanFactory.containsBean(beanName)){
			BeanDefinitionBuilder beanDefinitionBuilder= BeanDefinitionBuilder.rootBeanDefinition(beanClass);
			beanDefinitionBuilder.addConstructorArgValue(beanName);
			beanDefinitionBuilder.addPropertyValue("namesrvAddr", "10.1.2.236:9876");
			
			for(Map.Entry<String, Object> property:properties.entrySet()){    
				beanDefinitionBuilder.addPropertyValue(property.getKey(), property.getValue());
			}
			beanDefinitionBuilder.setScope("singleton");
			beanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
		}
	}
}
