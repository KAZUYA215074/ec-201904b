package com.example.ecommerce_b.domain;

import java.util.List;

/**
 * 注文されたセットメニュー.
 * 
 * @author ayane.tanaka
 *
 */
public class OrderSet {

	/** id*/
	private Integer id;
	/** セットid*/
	private Integer setId;
	/** 注文id*/
	private Integer orderId;
	/** 数量*/
	private Integer quantity;
	/** セット*/
	private Set set;
	/** セットの中身のリスト*/
	private List<OrderItem> orderItemList;
	
	public OrderSet() {
	}
	
	
	
	public OrderSet(Integer id, Integer setId, Integer orderId, Integer quantity, Set set,
			List<OrderItem> orderItemList) {
		super();
		this.id = id;
		this.setId = setId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.set = set;
		this.orderItemList = orderItemList;
	}



	@Override
	public String toString() {
		return "OrderSet [id=" + id + ", setId=" + setId + ", orderId=" + orderId + ", quantity=" + quantity + ", set="
				+ set + ", orderItemList=" + orderItemList + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSetId() {
		return setId;
	}
	public void setSetId(Integer setId) {
		this.setId = setId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Set getSet() {
		return set;
	}
	public void setSet(Set set) {
		this.set = set;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	
}
