package com.atguigu.test;

import java.math.BigDecimal;

import org.junit.Test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.serviceImp.OrderService;
import com.atguigu.serviceImp.OrderServlceImp;

public class OrderServlceImpTest {

	@Test
	public void testCreateOrder() {
		Cart cart = new Cart();
		cart.addItem(new CartItem(1, "java���ŵ�����", 1, new BigDecimal(1000), new BigDecimal(1000)));
		cart.addItem(new CartItem(1, "java���ŵ�����", 1, new BigDecimal(1000), new BigDecimal(1000)));
		cart.addItem(new CartItem(2, "���ݽṹ���㷨���ŵ�����", 1, new BigDecimal(100), new BigDecimal(100)));
	    
		OrderService orderServlce = new OrderServlceImp();
	
	   System.out.println("�����ţ�"+orderServlce.createOrder(cart, 1));
	}

}
