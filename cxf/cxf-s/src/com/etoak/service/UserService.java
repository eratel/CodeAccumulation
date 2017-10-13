package com.etoak.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(
		serviceName="UserServiceImpl",
		portName="UserServiceImplPort"
		) //表示发布服务接口
public interface UserService {
	
	@WebMethod
	@WebResult(name="result")
	public String login(
			@WebParam(name="uesrname") String username,
			@WebParam(name="password") String password);
	
	public void del();
}
