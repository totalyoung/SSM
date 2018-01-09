package com.application;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;

	}

	/**
	 * 根据bean的id来查找对象
	 * 
	 * @param id
	 * @return
	 */
	public static Object getBeanById(String id) {
		return context.getBean(id);
	}

	/**
	 * 根据bean的class来查找对象
	 * 
	 * @param clszz
	 * @return
	 */
	public static <T> T getBeanByClass(Class<T> clszz) {
		return context.getBean(clszz);
	}

	/**
	 * 根据bean的class来查找所有的对象(包括子类)
	 * 
	 * @param clszz
	 * @return
	 */
	public static <T> Map<String, T> getBeansByClass(Class<T> clszz) {
		return context.getBeansOfType(clszz);
		// ContextLoaderListener
	}

}
