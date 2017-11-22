package com.common;

import java.text.MessageFormat;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AspectLog {

	// @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	// public void pointCut(){
	//
	// }

	private Logger logger = LoggerFactory.getLogger(AspectLog.class);

	@Before("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void beforeOperation(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException {
		// String name = joinPoint.getSignature().getName();
		// Class<?> clazz = joinPoint.getTarget().getClass();
		Object[] args = joinPoint.getArgs();
		String name = StringUtil.getMethodName(joinPoint.getSignature().toString());
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		String str = MessageFormat.format("操作 ：{0}，参数：{1}", name, Arrays.toString(args));
		System.out.println(str);
	}

	// @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	// public void executionTime(ProceedingJoinPoint joinPoint){
	// String name =
	// Thread.currentThread().getContextClassLoader().getClass().getName();
	// System.out.println("addLog....." +name);
	// }
	//
	// @After("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	// public void addLog(JoinPoint joinPoint){
	//
	// System.out.println("addLog.....");
	// }
	//
	// @AfterReturning(pointcut="",returning="retVal")
	// public void afterReturningOperation(Object retVal){
	// System.out.println("addLog.....");
	// }
	//
	// @AfterThrowing(pointcut="",throwing ="ex")
	// public void afterThrowingOperation(JoinPoint joinPoint,Exception ex){
	// System.out.println("addLog.....");
	// }

}
