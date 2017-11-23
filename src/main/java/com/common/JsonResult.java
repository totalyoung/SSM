package com.common;

import org.apache.commons.lang3.StringUtils;

public class JsonResult {

	private String msg;
	
	private Object data;
	
	private String code;
	
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setResponseCode(ResponseCode responseCode){
		this.success = StringUtils.equals(responseCode.getCode(),"10000");
		this.msg = responseCode.getMsg();
		this.code = responseCode.getCode();
		
	}
	
}
