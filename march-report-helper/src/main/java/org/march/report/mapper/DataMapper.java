package org.march.report.mapper;

import java.util.HashMap;
import java.util.Map;

public class DataMapper<T> {

	private final Map<String, DataMapperHandler> MappingMap = new HashMap<>();
	
	public DataMapper<T> register(String fieldName, DataMapperHandler handler) {
		MappingMap.put(fieldName, handler);
		return this;
	}
	
	public DataMapperHandler getDataMappeHandlerByFieldName(String fieldName) {
		return MappingMap.get(fieldName);
	}
}
