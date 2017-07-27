package org.march.report.constant;

import java.util.HashMap;
import java.util.Map;

import org.march.report.entity.FieldPro;
import org.march.report.service.BaseFormat;

/**
 * 
 * @author Vic Chu
 *
 */
public class DTOConfigPool {
	
	private static final Map<String, FieldPro[]> pool = new HashMap<>();
	
	private static final Map<Class<? extends BaseFormat>, Object> FormatPool = new HashMap<>();
	
	public static void putConfig(String key, FieldPro[] fps){
		pool.put(key, fps);
	}
	
	public static FieldPro[] getConfig(String key){
		return pool.get(key);
	}
	
	public static void putFormat(Class<? extends BaseFormat> clazz, Object instance){
		FormatPool.put(clazz, instance);
	}
	
	public static Object getFormatInstance(Class<? extends BaseFormat> clazz){
		return FormatPool.get(clazz);
	}
	
}
