package com.etoak.test;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.etoak.service.RedisService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac = new 
			ClassPathXmlApplicationContext("applicationContext.xml");
		RedisService rs = ac.getBean(RedisService.class);
		// rs.add("email", "easytour@163.com");
		// String value = rs.get("addr");
		// System.out.println(value);
		/*List list = new ArrayList(); // 有序不唯一
		list.add("easytour");
		list.add(30);
		list.add(true); 
		// [easytour , 30 , true]
		List l1 = rs.getList("list");
		System.out.println("list-->"+l1);
		*/
		/*// rs.setList("list", list);
		Object result = rs.popList("list");
		System.out.println("result-->"+result);
		
		List l2 = rs.getList("list");
		System.out.println("list-->"+l2);*/
		
		/*
		rs.setMap("map", "name", "sheldon");
		rs.setMap("map", "age", 30);
		rs.setMap("map", "addr", "山东");
		*/
		
		rs.getMap("map");
	}

}





