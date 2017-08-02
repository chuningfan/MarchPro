package org.march.persistence.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pagination")
public class Pagination implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3756527838181933146L;

	private int pageSize = 10;
	
	private int index = 1;
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
