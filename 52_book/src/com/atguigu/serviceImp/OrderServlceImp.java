package com.atguigu.serviceImp;

import java.util.Date;
import java.util.Map;

import com.atguigu.daoImp.BookDao;
import com.atguigu.daoImp.BookDaoImp;
import com.atguigu.daoImp.OrderDao;
import com.atguigu.daoImp.OrderDaoImp;
import com.atguigu.daoImp.OrderItemDao;
import com.atguigu.daoImp.OrderItemDaoImp;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

public class OrderServlceImp implements OrderService {

	private OrderDao orderDao = new OrderDaoImp();
	private OrderItemDao orderItemDao = new OrderItemDaoImp();
	private BookDao bookDao = new BookDaoImp();
	@Override
	public String createOrder(Cart cart, Integer userId) {
		//订单号 唯一性
		String orderId = System.currentTimeMillis()+""+userId;
		//创建订单
		Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
		//保存订单
		orderDao.saveOrder(order);
		
		int i = 12 / 0;
		
		//遍历购物车每一个商品项，转换成订单项保存到数据库
		for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
			//获取每一个购物车商品项
			CartItem cartItem = entry.getValue();
			//转换成订单项
			OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
		    //保存订单项
			orderItemDao.saveOrderItem(orderItem);
			
			Book book = bookDao.queryBookById(cartItem.getId());
			book.setSales(book.getSales() + cartItem.getCount());
			book.setStock(book.getStock() - cartItem.getCount());
			bookDao.updateBook(book);
		}
		//清空购物车
		cart.clear();
		return orderId;
	}

}
