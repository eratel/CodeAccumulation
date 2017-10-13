package com.etoak.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//非核心业务
public class MyAspect {

	//以下通知
	
	public void before(JoinPoint jp) {
		System.out.println("before-被代理方法名字："+jp.getSignature().getName());
		System.out.println("before-被代理方法参数："+jp.getArgs());
		System.out.println("before-被代理对象："+jp.getTarget());
		System.out.println("before-现在调用的是权限验证");
	}
	
	public void afterreturning(JoinPoint jp,Object rvt){
		System.out.println("afterreturning-被代理方法名字："+jp.getSignature().getName());
		System.out.println("afterreturning-被代理方法参数："+jp.getArgs());
		System.out.println("afterreturning-被代理对象："+jp.getTarget());
		System.out.println("afterreturning-被代理对象的返回值"+rvt);
		System.out.println("afterreturning-现在调用的是日志管理");
	}
	

	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("现在调用的是事务开启");		
		//得到业务方法的参数
		Object[] args=pjp.getArgs();
		System.out.println("业务方法的参数："+args);
		//被代理对象的业务方法
		Object result=pjp.proceed(args);
		System.out.println("现在调用的是事务提交或回滚");
	    return result;
	}
	
	
	public void after(){
		System.out.println("资源已经被释放！");		
	}
	
	public void throwEx(Exception ex){
		System.out.println("异常信息：" + ex.getMessage());
	}
	
}
