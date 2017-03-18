package org.march.authentication.dao;

public interface AuthenticationDao {
	
	boolean isValidUser(String loginName, String password) throws Exception ;
	
}
