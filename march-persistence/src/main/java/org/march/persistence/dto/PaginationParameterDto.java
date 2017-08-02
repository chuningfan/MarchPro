package org.march.persistence.dto;

import java.util.Map;

import org.march.persistence.constant.QueryType;
import org.march.persistence.entity.Pagination;

public class PaginationParameterDto {
	
	private String queryString;
	
	private QueryType queryType = QueryType.HQL;
	
	private Pagination pagination;
	
	private Map<String, Object> criteria;

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public QueryType getQueryType() {
		return queryType;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Map<String, Object> getCriteria() {
		return criteria;
	}

	public void setCriteria(Map<String, Object> criteria) {
		this.criteria = criteria;
	}
	
}
