package com.etoak.utils;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

//BeforeAdvice 这个类表示 切面（切面类）
public class BeforeAdvice implements MethodBeforeAdvice{
	
	//这个before方法表示通知
	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("before:" + arg0.getName());
		if(arg1.length > 0){
			for(Object obj : arg1){
				System.out.println("before:" + obj);
			}
		}
		System.out.println("before:" + arg2.getClass().getName());
		System.out.println("执行之前操作O(∩_∩)O~！！！！");
	}

}
