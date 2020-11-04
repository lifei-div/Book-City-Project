package com.atguigu.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;

public class CartTest {

	@Test
	public void testAddItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法入门到放弃", 1, new BigDecimal(100), new BigDecimal(100)));
	    
        System.out.println(cart);
	}

	@Test
	public void testDeleteItem() {
		Cart cart = new Cart();
		cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(1000), new BigDecimal(1000)));
		cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(1000), new BigDecimal(1000)));
		cart.addItem(new CartItem(2, "数据结构与算法入门到放弃", 1, new BigDecimal(100), new BigDecimal(100)));
	    cart.deleteItem(1);
	    System.out.println(cart);
	}

	@Test
	public void testClear() {
		Cart cart = new Cart();
		cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(1000), new BigDecimal(1000)));
		cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(1000), new BigDecimal(1000)));
		cart.addItem(new CartItem(2, "数据结构与算法入门到放弃", 1, new BigDecimal(100), new BigDecimal(100)));
	    cart.clear();
	    System.out.println(cart);
	}

	@Test
	public void testUpdateCount() {
		Cart cart = new Cart();
		cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(1000), new BigDecimal(1000)));
		cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(1000), new BigDecimal(1000)));
		cart.addItem(new CartItem(2, "数据结构与算法入门到放弃", 1, new BigDecimal(100), new BigDecimal(100)));
	    cart.updateCount(1, 10);
	    System.out.println(cart);
	}

}
