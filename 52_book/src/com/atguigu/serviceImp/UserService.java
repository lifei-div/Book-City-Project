package com.atguigu.serviceImp;

import com.atguigu.pojo.User;

public interface UserService {
	/**
	 * ע���û�
	 * @param user
	 */
	public void registUser(User user);
	
	/**
	 * ��¼
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * ��� �û����Ƿ����
	 * @param username
	 * @return ����true��ʾ�û����Ѿ����ڣ�false��ʾ�û�������
	 */
	public boolean existsUsername(String username);
}
