package com.atguigu.serviceImp;

import com.atguigu.daoImp.UserDao;
import com.atguigu.daoImp.UserDaoImp;
import com.atguigu.pojo.User;

public class UserServiceImp implements UserService{

	private UserDao userDao = new UserDaoImp();;
	@Override
	public void registUser(User user) {
		userDao.saveUser(user);
		
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

	@Override
	public boolean existsUsername(String username) {
		if (userDao.queryUserByUsername(username) == null) {
			//����null��˵��û�鵽��û�鵽��ʾ����
			return false;
		}
		return true;
	}

}
