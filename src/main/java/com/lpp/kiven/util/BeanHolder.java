package com.lpp.kiven.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

@Service
public class BeanHolder implements ApplicationContextAware {
	private static ApplicationContext context = null;

	@Override
    public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		setApplicationContextStatic(arg0);
	}

	private static void setApplicationContextStatic(ApplicationContext arg0) {
		context = arg0;
	}

	public static <E> E getBean(String name) {
		if (context == null) {
			return null;
		}
		return (E) context.getBean(name);
	}

	public static <E> Map<String, ? extends E> getBeans(Class<? extends E> clazz) {
		if (context == null) {
			return null;
		}
		return context.getBeansOfType(clazz);
	}

	public static String getMessage(String msg, Object[] args, Locale locale) {
		if (context == null) {
			return msg;
		}
		return context.getMessage(msg, args, msg, locale);
	}

	public static String getMessage(String msg, Object[] args) {
		if (context == null) {
			return msg;
		}
		return context.getMessage(msg, args, msg, Locale.getDefault());
	}

	public static String getMessage(String msg) {
		if (context == null) {
			return msg;
		}
		return context.getMessage(msg, null, msg, Locale.getDefault());
	}

	public static String getMessage(String msg, Locale locale) {
		if (context == null) {
			return msg;
		}
		return context.getMessage(msg, null, msg, locale);
	}
}
