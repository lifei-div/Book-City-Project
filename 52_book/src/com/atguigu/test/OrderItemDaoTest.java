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
		orderItemDao.saveOrderItem(new OrderItem(null, "java从入门到放弃", 1, new BigDecimal(100), new BigDecimal(100), "121243432"));
		orderItemDao.saveOrderItem(new OrderItem(null, "数据结构与算法", 2, new BigDecimal(100), new BigDecimal(100), "121243432"));
		orderItemDao.saveOrderItem(new OrderItem(null, "javaweb", 1, new BigDecimal(100), new BigDecimal(100), "121243432"));
	}

}
