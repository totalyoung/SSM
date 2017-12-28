package com.common;

import org.springframework.data.redis.connection.RedisConnectionFactory;

public class RedisUtil {

	private static final RedisConnectionFactory CF;
	
	private static 

	static {
		CF = SpringContextUtil.getBeanByClass(RedisConnectionFactory.class);

	}

}
