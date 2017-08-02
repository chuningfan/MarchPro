package org.march.persistence.entity;

import java.io.Serializable;
import java.util.Collection;

public class PaginationResult<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 52343584777845874L;
	private Collection<T> resultSet;

	public Collection<T> getResultSet() {
		return resultSet;
	}

	public void setResultSet(Collection<T> resultSet) {
		this.resultSet = resultSet;
	}
	
}
