package org.march.persistence.basic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.march.persistence.entity.Pagination;
import org.march.persistence.entity.PaginationResult;

public interface IHibernateDAO<T> {

	List<T> findTbyHql(String hql);

	List<T> findAll();

	void fulsh();

	void clear();

	void save(T entity);

	void update(T entity);

	void delete(Serializable... entityids);

	T findById(Serializable entityId);

	PaginationResult<T> getHQLPaginationList(Pagination pagination, String hql, Map<String, Object> criteria);

	PaginationResult<T> getSQLPaginationList(Pagination pagination, String sql, Map<String, Object> criteria);
	
	int executeDML(final String sql);
	
	Long getCountBySQL(String sql, Map<String, Object> criteria);
	
	Long getCountBYHQL(String hql, Map<String, Object> criteria);
	
	public void delete(T entity);
}
