package org.march.jta.register;

import org.springframework.context.ConfigurableApplicationContext;

public class XaSpringBeanRegister {
	
	private XaSpringBeanRegister(){}
	
	private static final ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) SpringUtil
            .getApplicationContext();
	
	private static final class XaSpringBeanRegisterHolder{
		public static final XaSpringBeanRegister instance = new XaSpringBeanRegister();
	}
	
	public static XaSpringBeanRegister getInstance(){
		return XaSpringBeanRegisterHolder.instance;
	}
	
	public <T> void addToSpringContainer(String beanName,T t){
		
	}
	
}
