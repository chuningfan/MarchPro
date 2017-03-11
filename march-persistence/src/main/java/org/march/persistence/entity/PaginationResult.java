package org.march.persistence.entity;

import java.util.Collection;

public class PaginationResult<T> {
	
	private int pageCount;
	
	private Collection<T> resultSet;

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Collection<T> getResultSet() {
		return resultSet;
	}

	public void setResultSet(Collection<T> resultSet) {
		this.resultSet = resultSet;
	}
	
}
