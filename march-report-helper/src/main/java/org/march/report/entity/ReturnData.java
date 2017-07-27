package org.march.report.entity;

import java.util.List;

/**
 * 
 * @author Vic Chu
 *
 * @param <T>
 */
public class ReturnData<T> {

	
	List<DataRow> rows;

	List<T> dataList;
	
	public List<DataRow> getRows() {
		return rows;
	}

	public void setRows(List<DataRow> rows) {
		this.rows = rows;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
}
