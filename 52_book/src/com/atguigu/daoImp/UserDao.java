package com.atguigu.daoImp;

import com.atguigu.pojo.User;

public interface UserDao {
	// �����û�����ѯ�û���Ϣ
	public User queryUserByUsername(String username);

	// �����û����������ѯ�û���Ϣ
	public User queryUserByUsernameAndPassword(String username, String password);

	// �����û�ע����Ϣ
	public int saveUser(User user);

}
