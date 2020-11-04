package com.atguigu.daoImp;

import com.atguigu.pojo.OrderItem;

public class OrderItemDaoImp extends BaseDao implements OrderItemDao{
	@Override
	public int saveOrderItem(OrderItem orderItem) {
		String sql = "insert into t_order_item(name,count,price,total_price,order_id)values(?,?,?,?,?)";
		return update(sql, orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getPrice(),orderItem.getOrderId());
	}

}
