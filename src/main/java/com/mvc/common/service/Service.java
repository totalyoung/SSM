package com.mvc.common.service;

public interface Service {

	<T> int insert(T t);

	<T> int update(T t);

	int delete(String id);

	<T> T get(String id);
}
