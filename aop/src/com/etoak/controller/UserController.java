package com.etoak.controller;

//核心业务
public class UserController {
	
	//切入点
	public String login(String username,String password){
		System.out.println("核心业务逻辑：" + username + "|" + password);
		//注释打开int i = 1/0;测试异常通知
		//int i = 1/0;
		return username + "|" + password;
	}

}
