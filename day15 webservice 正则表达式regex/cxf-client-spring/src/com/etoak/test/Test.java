package com.etoak.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.etoak.service.IHelloService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new 
			ClassPathXmlApplicationContext("applicationContext.xml");
		IHelloService service = (IHelloService)ac.getBean("hs");
		String result = service.hello("sheldon");
		System.out.println(result);
		service.print();
	}

}
