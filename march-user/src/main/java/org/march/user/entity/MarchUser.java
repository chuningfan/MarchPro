package org.march.user.entity;

import java.io.Serializable;
import java.util.List;

import org.march.role.entity.UserRole;

public class MarchUser extends BaseType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2212982361137464849L;

	private String loginName;
	
	private List<UserRole> roles;
	
	private List<MarchGroup> groups;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public List<MarchGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<MarchGroup> groups) {
		this.groups = groups;
	}
	
}
