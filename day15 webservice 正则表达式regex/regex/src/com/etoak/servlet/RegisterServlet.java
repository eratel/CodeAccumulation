package com.etoak.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String loginname = request.getParameter("loginname");
		String realname = request.getParameter("realname");
		String age = request.getParameter("age");
		
		/**
		 * 不能以数字开头  字母+数字+_  4-8
		 * [xxx]  取括号中的任意一个字符
		 * {n,m}  {n}  {n,}
		 * 
		 * age -> <16 - 55>
		 * 		\d == [0-9]
		 * 		\D == [^0-9]
		 * 		\w == [a-zA-Z0-9_]
		 * 		\W == ^\w
		 * 
		 * .  表示任意一个字符
		 */
		// String format = "[a-zA-Z]?\\w{3,7}";
		// String format = "[\u4e00-\u9fa5]{2,}";
		String format = "(1[6-9])|([2-4]\\d)|(5[0-5])";
		// 对表达式进行编译
		Pattern pattern = Pattern.compile(format);
		// 匹配一组字符序列
		Matcher matcher = pattern.matcher(age);
		
		boolean result = matcher.matches();
		
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		
	}

}









