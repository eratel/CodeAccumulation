package com.etoak.web;

//UserWeb类型表示目标对象
public class UserWeb {
	
	//切入点
	public String add(String username,String password){
		System.out.println("add操作！！！");
		return "用户名：" + username + "|密码：" + password;
	}
	
	//切入点
	public String del(String id){
		System.out.println("删除条件ID：" + id);
		//int i = 1/0; 抛异常 触发异常通知
		return id;
	}

}
