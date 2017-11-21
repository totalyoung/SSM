package com.common;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletUtil {

	public static ServletRequestAttributes getServletRequestAttributes(){
		return (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
	}
	
	public static HttpServletRequest getRequest(){
		return getServletRequestAttributes().getRequest();
	}
	
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	public static ServletContext getServletContext(){
		return getSession().getServletContext();
	}
	
	public static HttpServletResponse getResponse(){
		return getServletRequestAttributes().getResponse();
	}
	
	public static void test(){
		getRequest().getRemoteAddr();
	}
	
	public static String getIpAddr(){
		HttpServletRequest request = getRequest();
		String ipAddress = request.getHeader("x-forwarded-for");  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getRemoteAddr();  
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                //根据网卡取本机配置的IP  
                InetAddress inet=null;  
                try {  
                    inet = InetAddress.getLocalHost();  
                } catch (UnknownHostException e) {  
                    e.printStackTrace();  
                }  
                ipAddress= inet.getHostAddress();  
            }  
        }  
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
            if(ipAddress.indexOf(",")>0){  
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
            }  
        }  
        return ipAddress; 
	}
	
	public static List<Object> getAllParamValues(){
		Map<String, String[]> parameterMap = getRequest().getParameterMap();
		List<Object> result = new LinkedList<>();
		for(Map.Entry<String, String[]> entry:parameterMap.entrySet()){
			String[] value = entry.getValue();
			if(value.length>1){
				result.add(Arrays.asList(value));
			}else{
				result.add(value[0]);
			}
		}
		return result;
	}
	
	public static List<String> getAllParam(){
		List<String> result = new LinkedList<>();
		Enumeration<String> parameterNames = getRequest().getParameterNames();
		while(parameterNames.hasMoreElements()){
			result.add(parameterNames.nextElement());
		}
		return result;
	}
}
