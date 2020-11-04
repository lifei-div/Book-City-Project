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
			System.out.println("�û������ã�");
		} else {
			System.out.println("�û����Ѵ��ڣ�");
		}
	}

	@Test
	public void testQueryUserByUsernameAndPassword() {

		if (userDao.queryUserByUsernameAndPassword("admin", "admin") == null) {
			System.out.println("�û������������");
		} else {
			System.out.println("��¼�ɹ�");
		}
	}

	@Test
	public void testSaveUser() {
		UserDao userDao = new UserDaoImp();
		System.out.println(userDao.saveUser(new User("lf12", "123456", "lf@qq.com")));
	}

}
