package org.march.user.entity;

import java.io.Serializable;
import java.util.List;

import org.march.role.entity.GroupRole;

public class MarchGroup extends BaseType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1139093399002905567L;

	private List<MarchUser> users;
	
	private List<GroupRole> roles;

	public List<MarchUser> getUsers() {
		return users;
	}

	public void setUsers(List<MarchUser> users) {
		this.users = users;
	}

	public List<GroupRole> getRoles() {
		return roles;
	}

	public void setRoles(List<GroupRole> roles) {
		this.roles = roles;
	}
	
}
