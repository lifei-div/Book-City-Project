package com.atguigu.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import com.atguigu.daoImp.OrderDao;
import com.atguigu.daoImp.OrderDaoImp;
import com.atguigu.pojo.Order;

public class OrderDaoTest {

	@Test
	public void testSaveOrder() {
		OrderDao orderDao = new OrderDaoImp();
		orderDao.saveOrder(new Order("121243432", new Date(), new BigDecimal(100), 0, 1));
	}

}
