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

	@SuppressWarnings("rawtypes")
	public int updateObjectState(String statementName, Map map) throws DataAccessException {
		 return getSqlSession().update(statementName, map);  
	}

	public int getObjectCount(String statementName, String filter) {
		 Long obj = getSqlSession().selectOne(statementName, filter);  
	     return obj.intValue();  
	}

	@SuppressWarnings("rawtypes")
	public int getObjectCount(String statementName, Map map) {
		 Long obj = getSqlSession().selectOne(statementName, map);  
	     return obj.intValue();  
	}

	@SuppressWarnings("rawtypes")
	public Object findObject(String statementName, Map map) {
		 return getSqlSession().selectOne(statementName, map);  
	}

	@SuppressWarnings("rawtypes")
	public List<Object> listByPage(String statementName, Map map, int index, int pageSize) {
		return getSqlSession().selectList(statementName, map, new RowBounds(index, pageSize));
	}

	public List<Object> listByPage(String statementName, String filter, int skipResults, int pageSize) {
		 return getSqlSession().selectList(statementName, filter, new RowBounds(skipResults, pageSize));  
	}

	public List<Object> list(String statementName, String filter) {
		return getSqlSession().selectList(statementName, filter);  
	}

	@SuppressWarnings("rawtypes")
	public List<Object> list(String statementName, Map map) {
		return getSqlSession().selectList(statementName, map);  
	}
	

}
