package com.etoak.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.etoak.client.UserService;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ac =
		new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService us = (UserService)ac.getBean("userService");
		String result = us.login("Ð¡Áù", "654321");
		System.out.println("¿Í»§¶Ë£º" + result);
	}

}
