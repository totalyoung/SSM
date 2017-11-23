package com.common;

public enum ResponseCode {

	CODE_SUCCESS("10000","成功"),
	
	CODE_10001("10001","第一个ResponseCode"),
	
	CODE_10002("10002","第2个ResponseCode");
	
	private String code;
	
	private String msg;
	
	//private String 
	private ResponseCode(String code,String msg){
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static void main(String[] args) {
		ResponseCode s = ResponseCode.valueOf("CODE_10001");
		s.setCode("666666");
		System.out.println(s.getCode());
		
	}
}
