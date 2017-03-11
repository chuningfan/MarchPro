package org.march.user.entity;

import java.io.Serializable;

import org.march.user.constract.UserType;

public class BaseType {
	
	protected Serializable Id;
	
	protected String name;
	
	protected UserType type;
	
	protected String picPath;
	
	protected boolean isValid = true;

	public Serializable getId() {
		return Id;
	}

	public void setId(Serializable id) {
		Id = id;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
