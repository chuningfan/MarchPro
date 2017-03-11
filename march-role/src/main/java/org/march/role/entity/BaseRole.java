package org.march.role.entity;

import java.io.Serializable;

public class BaseRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -102649090615910457L;

	protected Serializable roleId;
	
	protected String roleName;
	
	protected String roleCode;
	
	protected String description;
	
	protected boolean isValid = true;

	public Serializable getRoleId() {
		return roleId;
	}

	public void setRoleId(Serializable roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
}
