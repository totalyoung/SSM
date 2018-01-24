package com.mvc.common.dao;

public interface Dao {


	<T> int insert(T t);

	<T> int update(T t);

	int delete(String id);

	<T> T get(String id);

}
