package com.app.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceAdvice {
	
	@Before("execution(* com.app.service..*.*(..))")
	public void serviceBefore(){
		System.out.println("############# Before Service ###############");
	}

}
