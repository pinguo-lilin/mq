package pinguo.rocket.mq.comm;

import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;

public class BeanManage {

	public static void addBeanToFactory(Class<?> beanClass, String beanName, Map<String, Object> properties){
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
