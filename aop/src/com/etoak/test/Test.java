package com.etoak.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.etoak.controller.UserController;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		UserController	userController = 
				(UserController)ac.getBean("userController");
		String result  = userController.login("etoak", "et");
		System.out.println("·µ»Ø½á¹û£º" + result);
	}
}
