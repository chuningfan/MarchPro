package org.march.authentication.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.march.authentication.dao.AuthenticationDao;
import org.march.persistence.dao.HibernateBaseDAO;

@SuppressWarnings("rawtypes")
public class AuthenticationDaoImpl extends HibernateBaseDAO implements AuthenticationDao {
	
	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;
	
	public boolean isValidUser(String loginName, String password) throws Exception {
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("loginName", loginName);
		criteria.put("loginPassword", password);
		@SuppressWarnings("unchecked")
		Long res = super.getCountBySQL("select count(1) from user_authentication where login_name=:loginName and login_password=:loginPassword", criteria);
		if(res == 1){
			return true;
		}else if(res > 1){
			throw new Exception("for authenticating user the expected value is 1 but we got " + res);
		}
		return false;
	}
	
}
