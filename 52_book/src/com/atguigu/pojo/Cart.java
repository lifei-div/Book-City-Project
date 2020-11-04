package com.atguigu.pojo;
/**
 * ���ﳵ����
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
	 * key����Ʒ���
	 * value ����Ʒ��Ϣ
	 */
	private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();
	
	/**
	 * �����Ʒ��
	 * @param cartItem
	 */
	public void addItem(CartItem cartItem) {
		//�Ȳ鿴���ﳵ�Ƿ�����Ӵ���Ʒ���������ӣ��������ۼӡ��ܽ����¡����û����ӣ�ֱ�ӷŵ������м���
	    CartItem item = items.get(cartItem.getId());
	    if (item == null) {
			//֮ǰû����ӹ���ֱ�ӷ��뼯��
	    	items.put(cartItem.getId(), cartItem);
		}else {
			//����ӹ�
			item.setCount(item.getCount() + 1);//�����ۼ�
			//�����ܽ��
			item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
		}
	
	}
	
	/**
	 * ɾ����Ʒ��
	 * @param cartItem
	 */
	public void deleteItem(Integer id) {
		items.remove(id);
	}
	
	/**
	 * ��չ��ﳵ
	 * @param cartItem
	 */
	public void clear() {
		items.clear();
	}
	
	/**
	 * �޸���Ʒ����
	 * @param cartItem
	 */
	public void updateCount(Integer id, Integer count) {
		//�Ȳ鿴�ι��ﳵ�Ƿ��д���Ʒ������У����޸���Ʒ�����������ܽ��
	    CartItem cartItem = items.get(id);
	    if (cartItem != null) {
			//�ҵ���Ʒ���޸���Ʒ�������ܽ��
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
