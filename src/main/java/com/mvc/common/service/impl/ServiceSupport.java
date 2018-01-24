package com.mvc.common.service.impl;

import com.mvc.common.dao.Dao;
import com.mvc.common.service.Service;

public abstract class ServiceSupport implements Service {

	private Dao dao;

	public abstract void setDao(Dao dao);

	public <T> int insert(T t) {
		return dao.insert(t);
	};

	public <T> int update(T t) {
		return dao.update(t);
	}

	public int delete(String id) {
		return dao.delete(id);
	}

	public <T> T get(String id) {
		return dao.get(id);
	}
}
