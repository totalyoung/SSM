package com.mvc.common.service.impl;

import com.mvc.common.dao.Dao;
import com.mvc.common.service.Service;

public abstract class ServiceSupport implements Service {

	public abstract Dao getDao();

	public <T> int insert(T t) {
		return getDao().insert(t);
	};

	public <T> int update(T t) {
		return getDao().update(t);
	}

	public int delete(String id) {
		return getDao().delete(id);
	}

	public <T> T get(String id) {
		return getDao().get(id);
	}
}
