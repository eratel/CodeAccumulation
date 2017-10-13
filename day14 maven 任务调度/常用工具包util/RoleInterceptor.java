package com.etoak.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.etoak.bean.Employee;
import com.etoak.bean.Premission;
import com.etoak.bean.Role;

public class RoleInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		Employee emp = (Employee)request.getSession().getAttribute("emp");
		if(emp!=null){ //已登陆
			//当前执行的请求名 addBook.do 
			StringBuffer sb = request.getRequestURL();
			String path = sb.toString().substring(sb.toString().lastIndexOf("/")+1);
			//System.out.println("当前提交的请求---"+path);
		
			//遍历权限表中所有的权限
			List<String> allPres = (List<String>)request.getSession().getAttribute("allPres");
			//System.out.println(allPres+"============");
			boolean flat = false;
			for(String pre:allPres){
				//System.out.println(pre+"--ALL--PATH--"+path);
				if(pre.equals(path)){
					System.out.println("可操作权限...");
					flat = true;
					//start - 当前用户具备权限
					List<String> thisPers = (List<String>)request.getSession().getAttribute("thisPres");
					//System.out.println(thisPers+"---------");
					for(String thisPre:thisPers){
						System.out.println(thisPre+"--ALL--PATH--"+path);
						if(thisPre.equals(path)) {
							//System.out.println("具备该权限...");
							return true;
						}
					}
					//end - 当前用户具备权限
				}
			}
			//flat  false 不在权限列表中 |  true  可操作权限
			if(!flat){
				System.out.println("不可操作权限...");
				return true;
			}
			else {
				//System.out.println("可操作权限...");
				System.out.println("不具备权限...");
				response.sendRedirect("/index.jsp?manage=norole");
				return false;
			}
		}else{
			response.sendRedirect("/login.jsp");
			return false;
		}
	}

}








