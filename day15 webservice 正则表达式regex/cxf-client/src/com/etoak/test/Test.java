package com.etoak.test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.etoak.service.IHelloService;

public class Test {

	public static void main(String[] args) {
		/**
		 * 通过接口'IHelloService'访问服务器发布的webservice程序
		 * 1 CXF - 工厂 
		 */
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		// 2 设置访问的webservice程序地址
		factory.setAddress("http://localhost:8080/cxf-server/ws/hello");
		// 3 设置访问的webservice程序类型 [接口]
		factory.setServiceClass(IHelloService.class);
		// 4 创建访问到的接口实例
		IHelloService service = (IHelloService)factory.create();
		
		service.print();
		
		String result = service.hello("sheldon");
		System.out.println("客户端->"+result);
		
	}

}







