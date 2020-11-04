package com.atguigu.daoImp;

import com.atguigu.pojo.User;

public class UserDaoImp extends BaseDao implements UserDao{

	@Override
	public User queryUserByUsername(String username) {
		String sql = "select id,username,password,email from t_user where username = ?";
		return queryForOne(User.class, sql, username);
	}

	@Override
	public User queryUserByUsernameAndPassword(String username, String password) {
		String sql = "select id,username,password,email from t_user where username = ? and password = ?";
		return queryForOne(User.class, sql, username,password);
	}

	@Override
	public int saveUser(User user) {
		String sql =  "insert into t_user(username,password,email) values(?,?,?)"; 
		
		return update(sql, user.getUsername(),user.getPassword(),user.getEmail());
	}

}