package com.atguigu.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.pojo.User;
import com.atguigu.serviceImp.UserService;
import com.atguigu.serviceImp.UserServiceImp;

public class UserServiceTest {

	UserService userSerivce = new UserServiceImp();

	@Test
	public void testRegistUser() {
		userSerivce.registUser(new User("bbjj", "6666", "bbjj@66"));
	}

	@Test
	public void testLogin() {
		System.out.println(userSerivce.login(new User("bbjj", "6666", null)));
	}

	@Test
	public void testExistsUsername() {
		if (userSerivce.existsUsername("bbjj")) {
			System.out.println("用户名存在");
		}else {
			System.out.println("用户名可用");
		}
	}

}
