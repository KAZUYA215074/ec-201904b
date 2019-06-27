package com.example.ecommerce_b.form;

import java.util.List;

import com.example.ecommerce_b.domain.OrderTopping;

/**
 * 注文商品を追加するフォーム.
 * 
 * @author ayane.tanaka
 *
 */
public class OrderItemForm {

	/** 商品id */
	private Integer itemId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private String size;
	/** トッピングのidのリスト */
	private List<Integer> orderToppingIdList;	
	
	@Override
	public String toString() {
		return "OrderItemForm [itemId=" + itemId + ", quantity=" + quantity + ", size=" + size + ", orderToppingIdList="
				+ orderToppingIdList + "]";
	}
	
	//get&set
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public List<Integer> getOrderToppingIdList() {
		return orderToppingIdList;
	}
	public void setOrderToppingIdList(List<Integer> orderToppingIdList) {
		this.orderToppingIdList = orderToppingIdList;
	}
	
	
}
