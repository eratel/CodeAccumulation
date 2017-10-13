package com.etoak.test;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.etoak.service.UserService;
import com.etoak.service.UserServiceImpl;

public class Test {

	public static void main(String[] args) {
		
		String url = "http://localhost:8088/ws/user";
		cxfws(url);
		//jdkws(url);
		
	}
	
	public static void cxfws(String url){
		//1.定义一个服务端，用来发布服务
		JaxWsServerFactoryBean service = 
				new JaxWsServerFactoryBean();
		//2.定义一个url地址，用来客户端进行访问
		service.setAddress(url);
		//3.给客户端暴露远程接口
		service.setServiceClass(UserService.class);
		//4.给客户端暴露远程实现类
		service.setServiceBean(new UserServiceImpl());
		//5.发布服务
		service.create();
	}
	
	public static void jdkws(String url){
		Endpoint.publish(url, new UserServiceImpl());
	}

}
