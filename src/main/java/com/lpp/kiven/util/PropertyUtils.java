package com.lpp.kiven.util;

import java.util.ResourceBundle;


/**
 * <p>Title: 	以后要改成加载存到内存里面	</p>
 * <p>Description: 	</p>
 * <p>Copyright: 	Copyright (c) 2017</p>
 * <p>Company: 		SI-TECH </p>
 * @date 2017-7-12 下午5:05:48 
 * @author 		gt
 * @version 		1.0
 */
public class PropertyUtils {
	

	public final static String DEFAULT_BASE_NAME = "param";
	
	
	
	
	public final static ResourceBundle DEFAULT_BUNDLE = ResourceBundle.getBundle(DEFAULT_BASE_NAME);

	public static String getProperty(String key) {
		String value = DEFAULT_BUNDLE.getString(key).trim();
		return value;
	}

	public static int getInt(String key) {
		String value = DEFAULT_BUNDLE.getString(key);
		return Integer.parseInt(value);
	}

	public static String getProperty(String key, String baseName) {		
		ResourceBundle bundle = ResourceBundle.getBundle(baseName);
		String value = bundle.getString(key).trim();
		return value;
	}

	public static int getInt(String key, String baseName) {		
		ResourceBundle bundle = ResourceBundle.getBundle(baseName);
		String value = bundle.getString(key).trim();
		return Integer.parseInt(value);

	}


}
