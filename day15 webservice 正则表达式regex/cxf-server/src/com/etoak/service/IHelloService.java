package com.etoak.service;

import javax.jws.WebService;

@WebService
public interface IHelloService {

	// 接口中描述当前webservice应用程序提供了哪些服务
	public void print();
	
	public String hello(String name);
}
