package com.etoak.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect//切面类注解
public class MyAspect {
	
	@Pointcut("execution(* com.etoak.web.*.*(..))")
	public void etoak(){}
	
	@Before("etoak()")
	public void doBefore(JoinPoint jp){
		Object[] args = jp.getArgs();
		if(args.length > 0){
			for(Object obj : args){
				System.out.println("before:" + 
						obj.getClass().getName() + "|" + obj);
			}
		}
		Object target = jp.getTarget();
		System.out.println("before:" + target.getClass().getName());
		Signature st = jp.getSignature();
		System.out.println("目标类方法名：" + st.getName());
		String kind = jp.getKind();
		System.out.println("before:" + kind);
		System.out.println("执行之前操作！！！");
	}

	@After("etoak()")
	public void doAfter(JoinPoint jp){
		System.out.println("执行之后操作！！！");
	}
	
	@AfterReturning("etoak()")
	public void doAfterReturning(JoinPoint jp){
		System.out.println("执行afterReturning操作！！！！");
	}
	
	@Around("etoak()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("开启事务");
		Object result = pjp.proceed();
		System.out.println("提交事务");
		return result;
	}
	
	@AfterThrowing(value="etoak()",throwing="ex")
	public void doThrowing(JoinPoint jp,Exception ex){
		System.out.println("throw：" + ex.getMessage());
	}

}
