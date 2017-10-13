package com.etoak.utils;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

//切面类
public class ThrowAdvice implements ThrowsAdvice{
	
	//通知
	public void afterThrowing(
			Method method, Object[] args, Object target, Exception e){
		System.out.println("throw:" + method.getName());
		System.out.println("throw:" + e.getMessage());
	}
	
}
