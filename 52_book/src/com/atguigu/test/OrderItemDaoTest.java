package com.atguigu.test;

import java.math.BigDecimal;

import org.junit.Test;

import com.atguigu.daoImp.OrderItemDao;
import com.atguigu.daoImp.OrderItemDaoImp;
import com.atguigu.pojo.OrderItem;

public class OrderItemDaoTest {

	@Test
	public void test() {
		OrderItemDao orderItemDao = new OrderItemDaoImp();
		orderItemDao.saveOrderItem(new OrderItem(null, "java�����ŵ�����", 1, new BigDecimal(100), new BigDecimal(100), "121243432"));
		orderItemDao.saveOrderItem(new OrderItem(null, "���ݽṹ���㷨", 2, new BigDecimal(100), new BigDecimal(100), "121243432"));
		orderItemDao.saveOrderItem(new OrderItem(null, "javaweb", 1, new BigDecimal(100), new BigDecimal(100), "121243432"));
	}

}
