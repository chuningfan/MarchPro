package org.march.foundation.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="demo")
public class Demo implements Serializable {
	
	private static final long serialVersionUID = 1257640027222322184L;

	/**
	 * 记得要实现 Serializable 才能保证这个对象可以被网络传输 造爷
	 * 所以你每一个需要网络传输的实体对象 都必须实现这个接口
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
