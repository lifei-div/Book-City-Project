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
			// 读取jdbc.properties属性配置文件
			InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			// 从流中加载数据
			properties.load(inputStream);
			// 创建 数据库连接池
			dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 获取连接
	public static Connection getConnection() {
         
		Connection conn = conns.get();

		if (conn == null) {
			try {
				conn = dataSource.getConnection();//从数据库连接池获取连接
				
				conns.set(conn);//保存到ThreadLocal对象中，供后面的JDBC使用
				
				conn.setAutoCommit(false);//设置手动管理事务
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	
	/**
	 * 提交事务，并关闭释放连接
	 */
	public static void commitAndClose() {
		Connection connection = conns.get();
		if (connection != null) {//如果不等于null,说明使用过连接，操作过数据库
			try {
				connection.commit();//提交事务
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();//关闭连接，资源释放
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//一定要执行remove操作，否则会出错。(因为Tomact底层使用了线程池技术)
	    conns.remove();
	}
	/**
	 * 回滚事务，并关闭释放连接
	 */
	public static void rollbackAndClose() {
		Connection connection = conns.get();
		if (connection != null) {//如果不等于null,说明使用过连接，操作过数据库
			try {
				connection.rollback();//回滚事务
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();//关闭连接，资源释放
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//一定要执行remove操作，否则会出错。(因为Tomact底层使用了线程池技术)
	    conns.remove();
	}
/*
	// 关闭连接
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
