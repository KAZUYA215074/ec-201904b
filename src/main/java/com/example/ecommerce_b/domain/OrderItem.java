package com.example.ecommerce_b.domain;

import java.util.List;

/**
 * 注文商品のためのドメイン.
 * 
 * @author ryuheisugita
 */
public class OrderItem {
	
	/** ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** 商品 */
	private Item item;
	/** 注文トッピングリスト */
	private List<OrderTopping> orderToppingList;
	
	/**
	 * 注文商品から、合計金額を計算する.
	 * 
	 * @return 合計金額
	 */
	public int getSubTotal() {
		int itemSum=0;
		int toppingSum=0;
		if(this.size=='M' ||this.size=='m') {
			itemSum=item.getPriceM();
			toppingSum=orderToppingList.size()*200;
		}else if(this.size=='L' ||this.size=='l') {
			itemSum=item.getPriceL();			
			toppingSum=orderToppingList.size()*300;
			
		}
		
		return (itemSum+toppingSum)*this.quantity;
	}
	
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ",\n\t item=" + item + ",\n\t\t orderToppingList=" + orderToppingList + "]";
	}

	/** 引数なしのコンストラクタ */
	public OrderItem() {}
	
	/** コンストラクタ */
	public OrderItem(Integer id, Integer itemId, Integer orderId, Integer quantity, Character size, Item item,
			List<OrderTopping> orderToppingList) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.size = size;
		this.item = item;
		this.orderToppingList = orderToppingList;
	}
	
	 /** getter/setter */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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
	public Character getSize() {
		return size;
	}
	public void setSize(Character size) {
		this.size = size;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}
	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

}
