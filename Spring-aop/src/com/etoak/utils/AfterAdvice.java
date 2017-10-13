package com.etoak.utils;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

//??????
public class AfterAdvice implements AfterReturningAdvice{

	//??
	@Override
	public void afterReturning(
			Object arg0, //arg0????????
			Method arg1, 
			Object[] arg2, 
			Object arg3) throws Throwable {
		System.out.println("after:" + arg0.getClass().getName());
		System.out.println("after:" + arg1.getName());
		if(arg2.length > 0){
			for(Object obj : arg2){
				System.out.println("after:" + 
						obj.getClass().getName() + "|" + obj);
			}
		}
		System.out.println("after:" + arg3.getClass().getName());
		System.out.println("?????????????????????");
	}

}
