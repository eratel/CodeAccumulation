package com.etoak.web;

import org.springframework.stereotype.Controller;

@Controller
public class UserWeb {
	public String add(String username,String password){
		System.out.println("add操作！！！！");
		int i = 1/0;
		return "用户名：" + username + ",密码：" + password;
	}
}
