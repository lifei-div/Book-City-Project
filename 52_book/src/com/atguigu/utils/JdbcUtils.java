package com.atguigu.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtils {
	private static DruidDataSource dataSource;
	private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
	static {

		try {
			Properties properties = new Properties();
			// ��ȡjdbc.properties���������ļ�
			InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			// �����м�������
			properties.load(inputStream);
			// ���� ���ݿ����ӳ�
			dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// ��ȡ����
	public static Connection getConnection() {
         
		Connection conn = conns.get();

		if (conn == null) {
			try {
				conn = dataSource.getConnection();//�����ݿ����ӳػ�ȡ����
				
				conns.set(conn);//���浽ThreadLocal�����У��������JDBCʹ��
				
				conn.setAutoCommit(false);//�����ֶ���������
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	
	/**
	 * �ύ���񣬲��ر��ͷ�����
	 */
	public static void commitAndClose() {
		Connection connection = conns.get();
		if (connection != null) {//���������null,˵��ʹ�ù����ӣ����������ݿ�
			try {
				connection.commit();//�ύ����
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();//�ر����ӣ���Դ�ͷ�
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//һ��Ҫִ��remove��������������(��ΪTomact�ײ�ʹ�����̳߳ؼ���)
	    conns.remove();
	}
	/**
	 * �ع����񣬲��ر��ͷ�����
	 */
	public static void rollbackAndClose() {
		Connection connection = conns.get();
		if (connection != null) {//���������null,˵��ʹ�ù����ӣ����������ݿ�
			try {
				connection.rollback();//�ع�����
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();//�ر����ӣ���Դ�ͷ�
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//һ��Ҫִ��remove��������������(��ΪTomact�ײ�ʹ�����̳߳ؼ���)
	    conns.remove();
	}
/*
	// �ر�����
	public static void close(Connection conn) {
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}*/
}
