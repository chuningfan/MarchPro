package org.march.organization.entity;

import java.io.Serializable;
import java.util.List;

import org.march.user.constract.UserType;
import org.march.user.entity.BaseType;

public class BaseNode implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2136680299962212429L;

	private Serializable nodeId;
	
	private BaseNode parentNode;
	
	private String nodeName;
	
	private UserType nodeType;
	
	private String picPath;
	
	private String description;
	
	private BaseType userOgGroup;
	
	private List<BaseNode> childrenNodes;
	
	private boolean isValid = true;

	public Serializable getNodeId() {
		return nodeId;
	}

	public void setNodeId(Serializable nodeId) {
		this.nodeId = nodeId;
	}

	public BaseNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(BaseNode parentNode) {
		this.parentNode = parentNode;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public UserType getNodeType() {
		return nodeType;
	}

	public void setNodeType(UserType nodeType) {
		this.nodeType = nodeType;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BaseType getUserOgGroup() {
		return userOgGroup;
	}

	public void setUserOgGroup(BaseType userOgGroup) {
		this.userOgGroup = userOgGroup;
	}

	public List<BaseNode> getChildrenNodes() {
		return childrenNodes;
	}

	public void setChildrenNodes(List<BaseNode> childrenNodes) {
		this.childrenNodes = childrenNodes;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	} 
	
}
