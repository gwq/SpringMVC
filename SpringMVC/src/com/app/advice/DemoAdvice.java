package com.app.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DemoAdvice {
	
	@Before("execution(* com.app.springtest..*.*(..))")
	public void beforeTest(JoinPoint jp){
		System.out.println("=================BeforeTest================");
		System.out.println(jp.getSignature().getDeclaringTypeName()+"."+jp.getSignature().getName());
	}
	
	@Before("execution(* com.app.user..*.*(..))")
	public void before(JoinPoint jp){
		System.out.println("=================Before================");
		System.out.println(jp.getSignature().getDeclaringTypeName()+"."+jp.getSignature().getName());
	}
	
	 @Pointcut("execution(* com.app.user..*.*(..))")  
    public void sleeppoint(){}
    
     @Before("sleeppoint()")
    public void beforeSleep(){
        System.out.println("=================Before2=============");
    }

}
