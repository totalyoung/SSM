package com.common;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletUtil {

	public ServletRequestAttributes getServletRequestAttributes(){
		return (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
	}
	
	public HttpServletRequest getRequset(){
		return getServletRequestAttributes().getRequest();
	}
	
	public HttpSession getSession(){
		return getServletRequestAttributes().getRequest().getSession();
	}
	
	public ServletContext getServletContext(){
		return getServletRequestAttributes().getRequest().getSession().getServletContext();
	}
	
	public HttpServletResponse getResponse(){
		return getServletRequestAttributes().getResponse();
	}
}
