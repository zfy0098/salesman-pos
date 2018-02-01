package com.rhjf.salesman.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author hadoop
 */
public class PropertyUtils {
	private Properties pro = new Properties();
	private static PropertyUtils propertyUtils = new PropertyUtils();

	private PropertyUtils() {
		try {
			pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("trade.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			
		}
	}

	public static PropertyUtils getInstance() {
		return propertyUtils;
	}

	public static String getValue(String key) {
		return (String) getInstance().pro.get(key);
	}

}
