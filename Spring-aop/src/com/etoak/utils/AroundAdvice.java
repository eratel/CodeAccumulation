package com.etoak.utils;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

//切面类
public class AroundAdvice implements MethodInterceptor{

	//通知
	@Override
	public Object invoke(
			MethodInvocation invocation) throws Throwable {
		Object[] args = invocation.getArguments();
		if(args.length > 0){
			for(Object obj : args){
				System.out.println("around:" + 
						obj.getClass().getName() + "| " + obj);
			}
		}
		Method method = invocation.getMethod();
		System.out.println("around:" + method.getName());
		
		System.out.println("开启事务");
		Object result = invocation.proceed();//连接点
		System.out.println("提交事务");
		return result;
	}

}
