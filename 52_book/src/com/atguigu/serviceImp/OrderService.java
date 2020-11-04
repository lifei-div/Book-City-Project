package com.atguigu.serviceImp;

import com.atguigu.pojo.Cart;

public interface OrderService {

	public String createOrder(Cart cart, Integer userId);
}
