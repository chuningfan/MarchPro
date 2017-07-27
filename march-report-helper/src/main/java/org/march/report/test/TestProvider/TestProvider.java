package org.march.report.test.TestProvider;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.march.report.entity.DataRow;
import org.march.report.entity.FieldPro;
import org.march.report.entity.ReturnData;
import org.march.report.exception.ReportException;
import org.march.report.mapper.DataMapper;
import org.march.report.mapper.DataMapperHandler;
import org.march.report.service.ReportDataProvider;
import org.march.report.test.dto.TestDto;

public class TestProvider extends ReportDataProvider<TestDto> {

	@Override
	protected List<Object[]> query() {
		List<Object[]> list = new ArrayList<>();
		for(int i=1;i<=3;i++){
			Object[] data = new Object[5];
			for(int j=0;j<5;j++){
				if(j == 1){
					data[j] = i + "name";
				}else if(j==0){
					data[j] = i + "address";
				}else if(j==2){
					data[j] = Long.valueOf(i+"");
				}else if (j == 3){
					data[j] = new Date();
				} else {
					data[j] = j%2;
				}
			}
			list.add(data);
		}
		return list;
	}

	@Override
	protected Class<? extends TestDto> registerEntity() {
		return TestDto.class;
	}

	@Override
	protected ReturnData<TestDto> getReportData() {
		try {
			return super.getData(true, true);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException | ReportException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		TestProvider tp = new TestProvider();
		ReturnData<TestDto> res = tp.getReportData();
		List<TestDto> list = res.getDataList();
		List<DataRow> returnList = res.getRows();
		System.out.println("entity list begin..");
		int rownum = 1;
		for(TestDto dto : list){
			Long id = dto.getId();
			String name = dto.getName();
			String addr = dto.getAddr();
			Date date = dto.getDate();
			System.out.println("row " + (rownum ++) + "--- id: " + id + "; name: " + name + "; address: " + addr + " date: " + date);
		}
		System.out.println("data returned to front-end");
		int rownumber = 1;
		for(DataRow dr : returnList){
			System.out.print("row " + rownumber + "--- ");
			List<FieldPro> columns = dr.getColumns();
			int columnnum = 1;
			for(FieldPro fp : columns){
				String fieldName = fp.getFieldName();
				Object value = fp.getValue();
				String typeName = fp.getFieldType().toString();
				String displayName = fp.getDisplayName();
				System.out.println("column " + (columnnum ++) + ">>>" + fieldName + ": " + value + "; valueType: " + typeName + "; displayName: " + displayName);
				
			}
			rownumber ++;
		}
	}

	@Override
	protected DataMapper<TestDto> getDataMapper() {
		DataMapper<TestDto> mapper = new DataMapper<TestDto>();
		mapper.register("gender", new DataMapperHandler(){
			@Override
			public Object returnSpecificValueWhenGet(Object value) {
				if (Short.valueOf(value.toString()) == 1) {
					return "male";
				} else {
					return "female";
				}
			}
			
		});
		return mapper;
	}

}
