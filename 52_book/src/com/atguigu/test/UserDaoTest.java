package com.atguigu.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.daoImp.UserDao;
import com.atguigu.daoImp.UserDaoImp;
import com.atguigu.pojo.User;

public class UserDaoTest {
	UserDao userDao = new UserDaoImp();

	@Test
	public void testQueryUserByUsername() {

		if ((userDao.queryUserByUsername("admin") == null)) {
			System.out.println("用户名可用！");
		} else {
			System.out.println("用户名已存在！");
		}
	}

	@Test
	public void testQueryUserByUsernameAndPassword() {

		if (userDao.queryUserByUsernameAndPassword("admin", "admin") == null) {
			System.out.println("用户名或密码错误");
		} else {
			System.out.println("登录成功");
		}
	}

	@Test
	public void testSaveUser() {
		UserDao userDao = new UserDaoImp();
		System.out.println(userDao.saveUser(new User("lf12", "123456", "lf@qq.com")));
	}

}
