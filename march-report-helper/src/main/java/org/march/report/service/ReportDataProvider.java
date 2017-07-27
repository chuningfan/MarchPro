package org.march.report.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.march.report.annotation.ReportDataMarker;
import org.march.report.annotation.ReportEntityMarker;
import org.march.report.constant.DTOConfigPool;
import org.march.report.entity.DataRow;
import org.march.report.entity.FieldPro;
import org.march.report.entity.ReturnData;
import org.march.report.exception.ReportException;
import org.march.report.mapper.DataMapper;
import org.march.report.mapper.DataMapperHandler;

/**
 * 
 * @author Vic Chu
 *
 * @param <T>
 */
public abstract class ReportDataProvider<T> {
	
	private Class<? extends T> clazz;
	
	private DataMapper<T> mapper;
	
	protected abstract List<Object[]> query();
	
	protected abstract Class<? extends T> registerEntity();
	
	protected abstract ReturnData<T> getReportData();
	
	protected abstract DataMapper<T> getDataMapper();
	
	private static FieldPro[] entityFields;
	
	private static final String SET = "set";
	
	private static final String FORMAT_METHOD_NAME = "val";
	/**
	 * 
	 * @param needEntity> If the entity list will be in return data.
	 * @param entityFormat	If the entity data use format (when data type changed after formatting, entity data will not be formatted)
	 * @return
	 * @throws ReportException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	protected final ReturnData<T> getData(boolean needEntity, boolean entityFormat) throws ReportException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException{
		this.clazz = registerEntity();
		if(!isReportEntity()){
			throw new ReportException("This entity missed to add 'ReportEntityMarker' annotation!");
		}
		if(entityFields == null || entityFields.length == 0){
			getFields();
		}
		
		//judge if the entity has a data mapper
		if (hasDataMapper()) {
			mapper = getDataMapper();
		}
		
		List<Object[]> result = query();
		
		ReturnData<T> rd = new ReturnData<T>();
		List<DataRow> rows = new ArrayList<>();
		List<T> dataList = new ArrayList<>();
		rd.setRows(rows);
		rd.setDataList(dataList);
		if(!result.isEmpty()){
			for(Object[] objs : result){
				if(objs.length != entityFields.length){
					throw new ReportException("configured column and actual result set are not matched!");
				}
				Object instance = clazz.newInstance();
				DataRow dr = new DataRow();
				List<FieldPro> columns = new ArrayList<>();
				dr.setColumns(columns);
				for(int i=0;i<objs.length;i++){
					FieldPro fp = entityFields[i].getCopy();
					Class<?> type = Class.forName(fp.getFieldType().getTypeName());
					Object value = objs[fp.getQueryIndex()];
					if (mapper != null) {
						DataMapperHandler handler = mapper.getDataMappeHandlerByFieldName(fp.getFieldName());
						if (handler != null) {
							value = handler.returnSpecificValueWhenGet(value);
						}
					}
					Class<? extends BaseFormat> key = fp.getFormat();
					BaseFormat baseFormat = DTOConfigPool.getFormatInstance(key) != null ? (BaseFormat) DTOConfigPool.getFormatInstance(key) : null;
					if(baseFormat == null){
						baseFormat = key.newInstance();
						DTOConfigPool.putFormat(key, baseFormat);
					}
					Method formatMethod = key.getDeclaredMethod(FORMAT_METHOD_NAME, Object.class);
					Object formatValue = formatMethod.invoke(baseFormat, value);
					if(type.isInstance(formatValue) && entityFormat){
						value = formatValue;
					}
					fp.setValue(formatValue);
					fp.setEntityValue(value);
					columns.add(fp);
					//process the instance
					if(needEntity){
						processInstance(instance, fp, type);
					}
				}
				rows.add(dr);
				dataList.add((T) instance);
			}
		}
		return rd;
	}
	
	private final void processInstance(Object instance, FieldPro fp, Class<?> type) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException{
		String fieldName = fp.getFieldName();
		Method method = instance.getClass().getDeclaredMethod(SET + UpperFirstLetter(fieldName), type);
		method.invoke(instance, fp.getEntityValue());
	}
	
	private final String UpperFirstLetter(String str){
		String firstLetter = str.substring(0,1).toUpperCase();
		String rest = str.substring(1);
		return firstLetter + rest;
	}
	
	private final boolean isReportEntity(){
		Annotation[] annotations = clazz.getAnnotations();
		if(annotations != null && annotations.length > 0){
			for(Annotation anno : annotations){
				if(anno instanceof ReportEntityMarker){
					return true;
				}
			}
		}
		return false;
	}
	
	private final void getFields() throws ReportException{
		FieldPro[] fps = DTOConfigPool.getConfig(clazz.getName());
		if(fps != null && fps.length > 0){
			entityFields = fps;
			return;
		}
		List<FieldPro> list = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		Integer validationFlag = 0;
		Set<Integer> validationSet = new HashSet<>();
		for(Field field : fields){
			Annotation[] annotations = field.getAnnotations();
			boolean flag = false;
			Annotation annotation = null;
			for(Annotation anno : annotations){
				flag = anno instanceof ReportDataMarker;
				if(flag){
					annotation = anno;
					break;
				}
			}
			if(flag && annotation != null){
				FieldPro fp = new FieldPro();
				fp.setFieldName(field.getName());
				ReportDataMarker marker = (ReportDataMarker) annotation;
				fp.setQueryIndex(marker.queryIndex());
				fp.setDisplayName(marker.displayName());
				fp.setFieldType(field.getType());
				@SuppressWarnings("unchecked")
				Class<? extends BaseFormat> format = (Class<? extends BaseFormat>) marker.format();
				fp.setFormat(format);
				list.add(fp);
				validationFlag ++;
				validationSet.add(marker.queryIndex());
			}
		}
		if(validationSet.size() < validationFlag){
			throw new ReportException("there may have duplicate query index in entity");
		}
		if(!list.isEmpty()){
			Comparator<FieldPro> cmp = new Comparator<FieldPro>(){
				@Override
				public int compare(FieldPro o1, FieldPro o2) {
					int index1 = o1.getQueryIndex();
					int index2 = o2.getQueryIndex();
					if(index1 > index2){
						return 0;
					}
					return -1;
				}
			};
			Collections.sort(list, cmp);
			entityFields = list.toArray(new FieldPro[list.size()]);
			DTOConfigPool.putConfig(clazz.getName(), list.toArray(new FieldPro[list.size()]));
		}
	}
	
	private boolean hasDataMapper() {
		ReportEntityMarker anno = clazz.getDeclaredAnnotation(ReportEntityMarker.class);
		return anno.hasDataMapper();
	}
}
