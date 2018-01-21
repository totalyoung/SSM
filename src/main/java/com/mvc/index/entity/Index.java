package com.mvc.index.entity;

import java.io.Serializable;

public class Index implements Serializable {

	private static final long serialVersionUID = 1L;

	String id;

	String name;

	String phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
