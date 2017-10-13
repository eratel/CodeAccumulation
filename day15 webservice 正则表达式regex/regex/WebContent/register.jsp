<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript">
			function check(){
				var loginname = document.getElementById("loginname").value;
				/**
					/^ 正则表达式的行为开始
					$/ 正则表达式的行为结束
				*/
				var format = "^[a-zA-Z0-9_]{4,8}$";
				
				// RegExp  =  register expression
				var result = new RegExp(format).test(loginname);
				alert(result);
				
				return false;
			}
		</script>
	</head>
	<body>
		<form action="register" method="post" onsubmit="return check()">
			登录名:<input type="text" name="loginname"/><br/>
			真实姓名:<input type="text" name="realname"/><br/>
			年龄:<input type="text" name="age"/><br/>
			身份证号 : 18 数字 [xX]	
				\\d{17}[0-9xX]
			邮箱 : [字母 数字 _]@[数字 字母].[字母]
				\\w[4,8]@\\w{2,}\\.\\w{2,}
			个人主页 : http://www.???.com
				http:\/\/www\\.[.]{2,}\\.com
			
			<input type="submit" value="注册"/>
		</form>
	</body>
</html>





