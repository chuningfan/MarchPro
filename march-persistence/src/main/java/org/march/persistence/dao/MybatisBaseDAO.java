package org.march.persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.march.persistence.basic.IMybatisDAO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class MybatisBaseDAO<T> extends SqlSessionDaoSupport implements IMybatisDAO<T> {

	
	@SuppressWarnings("unused")
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public void addObject(String statementName, Object obj) throws DataAccessException {
		 getSqlSession().insert(statementName, obj); 
	}
	
	public int deleteObject(String statementName, Serializable objId) throws DataAccessException {
		return getSqlSession().delete(statementName, objId);
	}

	public Object findObject(String statementName, Serializable objId) throws DataAccessException {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(statementName, objId);
	}
	
	@SuppressWarnings("rawtypes")
	public int deleteForMap(String statementName, Map map) throws DataAccessException {
		 return getSqlSession().delete(statementName, map);  
	}

	public int updateObject(String statementName, Object obj) throws DataAccessException {
		 return getSqlSession().update(statementName, obj);
	}

	public int updateObjectState(String statementName, Map<String, Object> map) throws DataAccessException {
		 return getSqlSession().update(statementName, map);  
	}

	public int getObjectCount(String statementName, String filter) {
		 Long obj = getSqlSession().selectOne(statementName, filter);  
	     return obj.intValue();  
	}

	public int getObjectCount(String statementName, Map<String, Object> map) {
		 Long obj = getSqlSession().selectOne(statementName, map);  
	     return obj.intValue();  
	}

	public Object findObject(String statementName, Map<String, Object> map) {
		 return getSqlSession().selectOne(statementName, map);  
	}

	public <R> List<R> listByPage(String statementName, Map<String, Object> map, int index, int pageSize) {
		return getSqlSession().selectList(statementName, map, new RowBounds(index, pageSize));
	}

	public <R> List<R> listByPage(String statementName, String filter, int skipResults, int pageSize) {
		 return getSqlSession().selectList(statementName, filter, new RowBounds(skipResults, pageSize));  
	}

	public <R> List<R> list(String statementName, String filter) {
		return getSqlSession().selectList(statementName, filter);  
	}

	public <R> List<R> list(String statementName, Map<String, Object> map) {
		return getSqlSession().selectList(statementName, map);  
	}
	

}
