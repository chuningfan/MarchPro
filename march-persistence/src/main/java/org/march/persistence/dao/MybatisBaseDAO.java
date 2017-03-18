package org.march.persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.march.persistence.basic.IMybatisDAO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

public class MybatisBaseDAO<T> extends SqlSessionDaoSupport implements IMybatisDAO<T> {

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
	
	public int deleteForMap(String statementName, Map map) throws DataAccessException {
		 return getSqlSession().delete(statementName, map);  
	}

	public int updateObject(String statementName, Object obj) throws DataAccessException {
		 return getSqlSession().update(statementName, obj);
	}

	public int updateObjectState(String statementName, Map map) throws DataAccessException {
		 return getSqlSession().update(statementName, map);  
	}

	public int getObjectCount(String statementName, String filter) {
		 Long obj = getSqlSession().selectOne(statementName, filter);  
	     return obj.intValue();  
	}

	public int getObjectCount(String statementName, Map map) {
		 Long obj = getSqlSession().selectOne(statementName, map);  
	     return obj.intValue();  
	}

	public Object findObject(String statementName, Map map) {
		 return getSqlSession().selectOne(statementName, map);  
	}

	public List listByPage(String statementName, Map map, int index, int pageSize) {
		return getSqlSession().selectList(statementName, map, new RowBounds(index, pageSize));
	}

	public List listByPage(String statementName, String filter, int skipResults, int pageSize) {
		 return getSqlSession().selectList(statementName, filter, new RowBounds(skipResults, pageSize));  
	}

	public List list(String statementName, String filter) {
		return getSqlSession().selectList(statementName, filter);  
	}

	public List list(String statementName, Map map) {
		return getSqlSession().selectList(statementName, map);  
	}


}
