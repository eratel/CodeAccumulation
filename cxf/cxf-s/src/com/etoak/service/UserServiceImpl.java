package com.etoak.service;

import javax.jws.WebService;

@WebService(
			endpointInterface="com.etoak.service.UserService",
			serviceName="UserServiceImpl",
			portName="UserServiceImplPort"
		)
public class UserServiceImpl implements UserService {

	@Override
	public String login(String username, String password) {
		System.out.println("用户名：" + username);
		System.out.println("密码：" + password);
		return "用户名：" + username + ",密码：" + password;
	}

	//@WebMethod(exclude=true) //使用jdk发布服务生效，隐藏该方法
	@Override
	public void del() {
		
	}

}
