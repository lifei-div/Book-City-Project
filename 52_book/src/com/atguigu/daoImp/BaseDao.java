package com.atguigu.daoImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.eclipse.jdt.internal.compiler.parser.ScannerHelper;

import com.atguigu.pojo.Order;
import com.atguigu.utils.JdbcUtils;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

public abstract class BaseDao {
	// 使用DbUtils 使用数据库
	private QueryRunner queryRunner = new QueryRunner();

	/**
	 * update() 方法用来执行：Insert、Update\Delete语句
	 * @return 返回 -1，执行失败。其他表明影响行数
	 */
	public int update(String sql, Object... args) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.update(conn, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
			//异常往外抛，供提交，回滚 解决
			throw new RuntimeException(e);
		} 
		
	}

	/**
	 * 查询一个Bean Sql语句
	 * 
	 * @param <T>  返回的类型的泛型
	 * @param type 返回的对象类型
	 * @param sql  执行sql语句
	 * @param args sql 对应的参数值
	 * @return
	 */
	public <T> T queryForOne(Class<T> type, String sql, Object... args) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//异常往外抛，供提交，回滚 解决
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 查询返回多个 个Bean Sql语句
	 * 
	 * @param <T>  返回的类型的泛型
	 * @param type 返回的对象类型
	 * @param sql  执行sql语句
	 * @param args sql 对应的参数值
	 * @return
	 */
	public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//异常往外抛，供提交，回滚 解决
			throw new RuntimeException(e);
		} 
		
	}

	/**
	 * 
	 * 执 行查询 返 回 一 行 一 列 的 sql 语 句
	 * 
	 * @param sql  执 行 的 sql 语 句
	 * @param args sql 对 应 的 参 数 值
	 * 
	 */
	public Object queryForSingValue(String sql, Object... args) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.query(conn, sql, new ScalarHandler(), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//异常往外抛，供提交，回滚 解决
			throw new RuntimeException(e);
		} 
		
	}

	

}
