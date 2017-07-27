package org.march.report.entity;

import java.lang.reflect.Type;

import org.march.report.service.BaseFormat;

/**
 * 
 * @author Vic Chu
 *
 */
public class FieldPro {
	
	private String fieldName;
	
	private String displayName;
	
	private Object value;
	
	private int queryIndex;
	
	private Type fieldType;

	private transient Object entityValue;
	
	private Class<? extends BaseFormat> format;
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getQueryIndex() {
		return queryIndex;
	}

	public void setQueryIndex(int queryIndex) {
		this.queryIndex = queryIndex;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Type getFieldType() {
		return fieldType;
	}

	public void setFieldType(Type fieldType) {
		this.fieldType = fieldType;
	}

	public Class<? extends BaseFormat> getFormat() {
		return format;
	}

	public void setFormat(Class<? extends BaseFormat> format) {
		this.format = format;
	}

	public Object getEntityValue() {
		return entityValue;
	}

	public void setEntityValue(Object entityValue) {
		this.entityValue = entityValue;
	}

	public FieldPro getCopy(){
		FieldPro newFp = new FieldPro();
		newFp.setDisplayName(displayName);
		newFp.setFieldName(fieldName);
		newFp.setFieldType(fieldType);
		newFp.setQueryIndex(queryIndex);
		newFp.setFormat(format);
		return newFp;
	}
	
}
