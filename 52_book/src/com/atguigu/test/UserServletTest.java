package com.atguigu.test;

import java.lang.reflect.Method;

public class UserServletTest {
	
	public void login() {
		System.out.println("这是login()方法被调用了");
	}
	
	public void regist() {
		System.out.println("这是regist()方法被调用了");
	}
	
	public void updateUser() {
		System.out.println("这是updateUser()方法被调用了");
	}
	
	public void updateUserPassword() {
		System.out.println("这是updateUserPassword()方法被调用了");
	}
	
	
	public static void main(String[] args) {
		String action = "login";
		try {
			//获取action业务鉴别字符串，获取响应的业务 方法反射对象
			Method method = UserServletTest.class.getDeclaredMethod(action);
			System.out.println(method);
			//调用目标业务 方法
			method.invoke(new UserServletTest());
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
