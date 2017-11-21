package com.common;

import java.lang.reflect.Method;

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
		System.out.println("kk");
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
		System.out.println(retVal.toString());
	}	
//	
//	@AfterThrowing(pointcut="",throwing ="ex")
//	public void afterThrowingOperation(JoinPoint joinPoint,Exception ex){
//		System.out.println("addLog.....");
//	}
	
}
