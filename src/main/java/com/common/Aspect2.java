package com.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class Aspect2 {

	@Before("execution(* com.total.index.controller.*.*(..))")
	public void addLog(JoinPoint joinPoint){
		System.out.println("addLog2.....");
	}
}
