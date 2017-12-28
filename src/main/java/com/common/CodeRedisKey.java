package com.common;

import java.io.Serializable;


/**
 * Redis常量KEY值(应保证KEY唯一性)
 * 组成 业务类型+功能
 * 
 * 
 * @date 2017-4-28 下午1:18:48   
 *
 */
public class CodeRedisKey implements Serializable {
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 售后服务号的key
	 */
	public static final String ORDER_SERVICE_NO = "getServiceNo";
	

}
