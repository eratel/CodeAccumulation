package com.etoak.test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.etoak.client.UserService;
import com.etoak.client.UserServiceService;

public class Test {

	public static void main(String[] args) {
		//jdkws();
		cxfws();
	}
	
	public static void jdkws(){
		UserServiceService uss = new UserServiceService();
		UserService us = uss.getUserServicePort();
		String result = us.login("张三", "123456");
		System.out.println("客户端：" + result);
	}
	
	public static void cxfws(){
		
		String url = "http://localhost:8088/ws/user";
		
		//1.生成客户端
		JaxWsProxyFactoryBean client = 
				new JaxWsProxyFactoryBean();
		//2.调用服务端url地址
		client.setAddress(url);
		//3.调用服务端暴露的接口
		client.setServiceClass(UserService.class);
		//4.调用服务端接口业务逻辑
		UserService us = (UserService)client.create();
		
		String result = us.login("李四", "123456");
		
		System.out.println("客户端：" + result);
	}

}
