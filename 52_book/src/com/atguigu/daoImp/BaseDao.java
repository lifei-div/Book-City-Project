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
	// ʹ��DbUtils ʹ�����ݿ�
	private QueryRunner queryRunner = new QueryRunner();

	/**
	 * update() ��������ִ�У�Insert��Update\Delete���
	 * @return ���� -1��ִ��ʧ�ܡ���������Ӱ������
	 */
	public int update(String sql, Object... args) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.update(conn, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
			//�쳣�����ף����ύ���ع� ���
			throw new RuntimeException(e);
		} 
		
	}

	/**
	 * ��ѯһ��Bean Sql���
	 * 
	 * @param <T>  ���ص����͵ķ���
	 * @param type ���صĶ�������
	 * @param sql  ִ��sql���
	 * @param args sql ��Ӧ�Ĳ���ֵ
	 * @return
	 */
	public <T> T queryForOne(Class<T> type, String sql, Object... args) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//�쳣�����ף����ύ���ع� ���
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * ��ѯ���ض�� ��Bean Sql���
	 * 
	 * @param <T>  ���ص����͵ķ���
	 * @param type ���صĶ�������
	 * @param sql  ִ��sql���
	 * @param args sql ��Ӧ�Ĳ���ֵ
	 * @return
	 */
	public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//�쳣�����ף����ύ���ع� ���
			throw new RuntimeException(e);
		} 
		
	}

	/**
	 * 
	 * ִ �в�ѯ �� �� һ �� һ �� �� sql �� ��
	 * 
	 * @param sql  ִ �� �� sql �� ��
	 * @param args sql �� Ӧ �� �� �� ֵ
	 * 
	 */
	public Object queryForSingValue(String sql, Object... args) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.query(conn, sql, new ScalarHandler(), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//�쳣�����ף����ύ���ع� ���
			throw new RuntimeException(e);
		} 
		
	}

	

}
