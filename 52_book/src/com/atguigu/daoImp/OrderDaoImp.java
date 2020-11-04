package com.atguigu.daoImp;

import com.atguigu.pojo.Order;

public class OrderDaoImp extends BaseDao implements OrderDao{
    @Override
	public int saveOrder(Order order) {
		String sql = "insert into t_order(order_id,create_time,price,status,user_id)values(?,?,?,?,?)";
		return update(sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
		
	}

	

	

}
