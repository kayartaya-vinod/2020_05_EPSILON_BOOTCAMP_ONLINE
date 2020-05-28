package com.epsilon.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	
	@Before("execution(* com.epsilon..ContactsDao.get*(..))")
	public void logBefore(JoinPoint jp) { // dependency injection AspjectJ proxy
		System.out.println(">>>> intercepting the function " 
				+ jp.getSignature().getDeclaringTypeName() 
				+ "."
				+ jp.getSignature().getName()
				+ " with arguments: " 
				+ Arrays.toString(jp.getArgs()));
	}

}
