package com.etoak.service;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

@Service
@WebService
public class UserServiceImpl implements UserService {

	@Override
	public String login(String username, String password) {
		System.out.println("用户名：" + username);
		System.out.println("密码：" + password);
		return "用户名：" + username + ",密码：" + password;
	}
	
}
