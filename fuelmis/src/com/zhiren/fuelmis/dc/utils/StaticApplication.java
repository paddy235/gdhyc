package com.zhiren.fuelmis.dc.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class StaticApplication implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
 
    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
    	StaticApplication.applicationContext = applicationContext;
    }
 
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
	
	
	public static Object getBean(String name) {
		if (applicationContext == null || StaticApplication.applicationContext == null) {
			throw new RuntimeException(
					"需要在SPRING的ApplicationContext上下文环境中配置StaticApplicationContext类！");
		}
		return StaticApplication.applicationContext.getBean(name);
	}
	

}
