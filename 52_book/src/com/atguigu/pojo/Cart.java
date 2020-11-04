package com.atguigu.pojo;
/**
 * 购物车对象
 * @author 19683
 *
 */

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	
//	private Integer totalCount;
//	private BigDecimal totalPrice;
	/**
	 * key是商品编号
	 * value 是商品信息
	 */
	private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();
	
	/**
	 * 添加商品项
	 * @param cartItem
	 */
	public void addItem(CartItem cartItem) {
		//先查看购物车是否以添加此商品，如果已添加，则数量累加。总金额更新。如果没有添加，直接放到集合中即可
	    CartItem item = items.get(cartItem.getId());
	    if (item == null) {
			//之前没有添加过。直接放入集合
	    	items.put(cartItem.getId(), cartItem);
		}else {
			//有添加过
			item.setCount(item.getCount() + 1);//数据累加
			//更新总金额
			item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
		}
	
	}
	
	/**
	 * 删除商品项
	 * @param cartItem
	 */
	public void deleteItem(Integer id) {
		items.remove(id);
	}
	
	/**
	 * 清空购物车
	 * @param cartItem
	 */
	public void clear() {
		items.clear();
	}
	
	/**
	 * 修改商品数量
	 * @param cartItem
	 */
	public void updateCount(Integer id, Integer count) {
		//先查看次购物车是否有此商品，如果有，则修改商品数量，更新总金额
	    CartItem cartItem = items.get(id);
	    if (cartItem != null) {
			//找到商品，修改商品数量，总金额
	    	cartItem.setCount(count);
	    	cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));

		}
	}
	
	
	public Integer getTotalCount() {
	   Integer	totalCount = 0;
		for(Map.Entry<Integer, CartItem> entry:items.entrySet()) {
			totalCount += entry.getValue().getCount();
		}
		return totalCount;
	}

	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = new BigDecimal(0);
		for(Map.Entry<Integer, CartItem> entry:items.entrySet()) {
			totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
		}
		return totalPrice;
	}
	
	
	public Map<Integer, CartItem> getItems() {
		return items;
	}

	public void setItems(Map<Integer, CartItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Cart [totalCount=" + getTotalCount() + ", totalPrice=" + getTotalPrice() + ", items=" + items + "]";
	}

    
}
