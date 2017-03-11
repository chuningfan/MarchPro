package org.march.persistence.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.march.persistence.basic.IHibernateDAO;
import org.march.persistence.entity.Pagination;
import org.march.persistence.entity.PaginationResult;
import org.march.persistence.util.GenericsUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class HibernateBaseDAO<T> extends HibernateDaoSupport implements IHibernateDAO<T> {

	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());  
	
	public List<T> findTbyHql(String hql) {
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) getHibernateTemplate().find(hql);
		return list;
	}

	public List<T> findAll() {
		List<T> list = getHibernateTemplate().loadAll(entityClass);  
		return list;
	}

	public void fulsh() {
		getHibernateTemplate().flush(); 
	}


	public void clear() {
		getHibernateTemplate().clear();
	}

	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	public void update(Object entity) {
		getHibernateTemplate().update(entity);
	}

	public void delete(Serializable... entityids) {
		if(entityids != null){
			for(Serializable id : entityids){
				T entity = findById(id);  
		        delete(entity);  
			}
		}
	}

	public void delete(T entity){
		getHibernateTemplate().delete(entity);
	}
	
	public T findById(Serializable entityId) {
		T entity = getHibernateTemplate().get(entityClass, entityId);
		return entity;
	}

	public PaginationResult<T> getHQLPaginationList(final Pagination pagination, final String hql, final Map<String, Object> criteria) {
		return getHibernateTemplate().execute(new HibernateCallback<PaginationResult<T>>(){
			public PaginationResult<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				GenericsUtils.setQuery(criteria, query);
				if(pagination != null){
					int pageSize = pagination.getPageSize();
					int pageIndex = pagination.getIndex();
					if ((pageSize > 0) && (pageIndex > 0)) {
						query.setFirstResult((pageIndex < 2) ? 0 : (pageIndex - 1) * pageSize);  
			            query.setMaxResults(pageSize); 
					}else{
						return null;
					}
				}
				@SuppressWarnings("unchecked")
				Collection<T> coll = query.list();
	            PaginationResult<T> pr = new PaginationResult<T>();
				pr.setResultSet(coll);
				return pr;
			}
		});
	}

	public PaginationResult<T> getSQLPaginationList(final Pagination pagination, final String sql, final Map<String, Object> criteria) {
		return getHibernateTemplate().execute(new HibernateCallback<PaginationResult<T>>(){
			public PaginationResult<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql).addEntity(entityClass);
				GenericsUtils.setQuery(criteria, query);
				if(pagination != null){
					int pageSize = pagination.getPageSize();
					int pageIndex = pagination.getIndex();
			        if ((pageSize > 0) && (pageIndex > 0)) { 
			            query.setFirstResult((pageIndex < 2) ? 0 : (pageIndex - 1) * pageSize);  
			            query.setMaxResults(pageSize); 
			        }else{
			        	return null;
			        }
				}
				@SuppressWarnings("unchecked")
				List<T> result = query.list();
	            PaginationResult<T> pr = new PaginationResult<T>();
	            pr.setResultSet(result);
	            return pr;  
			}
		});
	}

	public int executeDML(final String sql) {
		return getHibernateTemplate().execute(new HibernateCallback<Integer>(){
			public Integer doInHibernate(Session session) throws HibernateException {
				return session.createSQLQuery(sql).executeUpdate();
			}
		});
	}

	public Long getCountBySQL(final String sql, final Map<String, Object> criteria) {
		return getHibernateTemplate().execute(new HibernateCallback<Long>(){
			public Long doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				GenericsUtils.setQuery(criteria, query);
				Object obj = query.uniqueResult();
				if(obj == null){
					obj = 0;
				}
				return Long.valueOf(obj.toString());
			}
			
		});
	}

	public Long getCountBYHQL(final String hql, final Map<String, Object> criteria) {
		return getHibernateTemplate().execute(new HibernateCallback<Long>(){
			public Long doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				GenericsUtils.setQuery(criteria, query);
				Object obj = query.uniqueResult();
				if(obj == null){
					obj = 0;
				}
				return Long.valueOf(obj.toString());
			}
			
		});
	}


}
