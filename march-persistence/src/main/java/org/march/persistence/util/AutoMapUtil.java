package org.march.persistence.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AutoMapUtil {
	
	private static final String GET = "get";

	private static final String IS = "is";

	/**
	 * @author Vic.Chu
	 * @param instance
	 * @param keys
	 * @return
	 */
	public static Map<String, Object> getConditionMapByKeys(Object instance, String... keys) {
		Map<String, Object> condition = new HashMap<String, Object>();
		if (keys != null) {
			for (String key : keys) {
				try {
					Field field = instance.getClass().getDeclaredField(key);
					String methodName = "";
					if (field.getType().getName().equals("java.lang.Boolean")
							|| field.getType().getName().equals("boolean")) {
						try {
							methodName = IS + firstLetterToUpper(key);
							instance.getClass().getDeclaredMethod(methodName);
						} catch (NoSuchMethodException e) {
							methodName = GET + firstLetterToUpper(key);
						}
					} else {
						methodName = GET + firstLetterToUpper(key);
					}
					Method method = instance.getClass().getDeclaredMethod(methodName);
					Object value = method.invoke(instance);
					if (value != null) {
						condition.put(key, value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return condition;
		}
		return null;
	}

	private static String firstLetterToUpper(String letter) {
		if (letter.length() > 1) {
			String firstLetter = letter.substring(0, 1);
			String rest = letter.substring(1);
			return firstLetter.toUpperCase() + rest;
		} else {
			String firstLetter = letter.substring(0, 1);
			return firstLetter.toUpperCase();
		}
	}
	
}
