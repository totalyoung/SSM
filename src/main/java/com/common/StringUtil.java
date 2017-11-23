package com.common;

public class StringUtil {

	public static String[] getParamNames(String signatureStr) {
		return signatureStr.substring(signatureStr.indexOf('(') + 1, signatureStr.indexOf(')')).split(",");
	}

	public static String getMethodName(String signatureStr) {
		return signatureStr.substring(signatureStr.indexOf(' ') + 1);
	}

	public static void main(String[] args) {
		String str = "String com.total.index.controller.IndexController.SSMIndex(String,String)";
		String s1 = str.substring(str.indexOf('(') + 1, str.indexOf(')'));
		String[] a = s1.split(",");
		System.out.println("a");
	}
}
