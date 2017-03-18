package org.march.foundation.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="demo")
public class Demo implements Serializable {
	
	private static final long serialVersionUID = 1257640027222322184L;

	/**
	 * �ǵ�Ҫʵ�� Serializable ���ܱ�֤���������Ա����紫�� ��ү
	 * ������ÿһ����Ҫ���紫���ʵ����� ������ʵ������ӿ�
	 */

	private String name;
	
	private String addr;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Demo(String name, String addr, int age) {
		this.name = name;
		this.addr = addr;
		this.age = age;
	}

	public Demo() {
	}
	
	
	
}
