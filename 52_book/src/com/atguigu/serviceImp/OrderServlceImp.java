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
		//������ Ψһ��
		String orderId = System.currentTimeMillis()+""+userId;
		//��������
		Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
		//���涩��
		orderDao.saveOrder(order);
		
		int i = 12 / 0;
		
		//�������ﳵÿһ����Ʒ�ת���ɶ�����浽���ݿ�
		for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
			//��ȡÿһ�����ﳵ��Ʒ��
			CartItem cartItem = entry.getValue();
			//ת���ɶ�����
			OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
		    //���涩����
			orderItemDao.saveOrderItem(orderItem);
			
			Book book = bookDao.queryBookById(cartItem.getId());
			book.setSales(book.getSales() + cartItem.getCount());
			book.setStock(book.getStock() - cartItem.getCount());
			bookDao.updateBook(book);
		}
		//��չ��ﳵ
		cart.clear();
		return orderId;
	}

}
