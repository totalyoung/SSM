package com.common;

import java.lang.reflect.Method;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AspectLog {

//	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
//	public void pointCut(){
//		
//	}
	
	private Logger logger = LoggerFactory.getLogger(AspectLog.class);
	
	@Before("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void beforeOperation(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException{
		String name = joinPoint.getSignature().getName();
		Class<?> clazz = joinPoint.getTarget().getClass();
		Object[] params = joinPoint.getArgs();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
//		Class<?>[] args = new Class<?>[params.length];
//		
//		for (int i = 0; i < params.length; i++) {
//			args[i] = params[i].getClass();
//		}
		//Method declaredMethod = clazz.getDeclaredMethod(name, args);
		//declaredMethod.getp
		long beforeTime = System.currentTimeMillis();
		HttpServletRequest request = ServletUtil.getRequset();
		request.setAttribute("beforeTime", beforeTime);
		System.out.println("kkddd");
	}
	
//	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
//	public void executionTime(ProceedingJoinPoint joinPoint){
//		String name = Thread.currentThread().getContextClassLoader().getClass().getName();
//		System.out.println("addLog....." +name);
//	}
//	
//	@After("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
//	public void addLog(JoinPoint joinPoint){
//
//		System.out.println("addLog.....");
//	}
//	
	@AfterReturning(pointcut="@annotation(org.springframework.web.bind.annotation.RequestMapping)",returning="retVal")
	public void afterReturningOperation(Object retVal){
		HttpServletRequest request = ServletUtil.getRequset();
		Long afterTime = System.currentTimeMillis();
		Long beforeTime = (Long) request.getAttribute("beforeTime");
		String str = MessageFormat.format("返回值：{0},执行时间：{1}",retVal.toString(), (afterTime-beforeTime)/1000);
		System.out.println(str);
	}	
//	
//	@AfterThrowing(pointcut="",throwing ="ex")
//	public void afterThrowingOperation(JoinPoint joinPoint,Exception ex){
//		System.out.println("addLog.....");
//	}
	
	public static void main(String[] args) {
		System.out.println(3/1000);
	}
}
