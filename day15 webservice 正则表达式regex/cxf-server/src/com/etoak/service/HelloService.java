package com.etoak.service;

import javax.jws.WebService;

// 服务层 服务对象 - 用于提供各项服务
@WebService
public class HelloService implements IHelloService{

	// 提供一个打印服务
	public void print(){
		System.out.println("helloworld...");
	}
	
	public String hello(String name){
		
		String msg = name + " say hello to everyone";
		System.out.println(msg);
		return msg;
	}
	
}









