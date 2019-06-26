package com.example.ecommerce_b.domain;

/**
 * 注文されたドリンクのドメイン.
 * 
 * @author ayane.tanaka
 *
 */
public class OrderDrink {
	/** id */
	private Integer id;
	/** ドリンクid */
	private Integer drinkId;
	/** 注文id */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/**  サイズ*/
	private Character size;
	/** ドリンク */
	private Item drink;
	/**単品=false ,セット=true */
	private Boolean setOrder;
	
	/** コンストラクタ */
	public OrderDrink() {
	}

	/** コンストラクタ */
	public OrderDrink(Integer id, Integer drinkId, Integer orderId, Integer quantity, Character size, Item drink,
			Boolean orderOrSet) {
		super();
		this.id = id;
		this.drinkId = drinkId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.size = size;
		this.drink = drink;
		this.setOrder = orderOrSet;
	}

	@Override
	public String toString() {
		return "OrderDrink [id=" + id + ", drinkId=" + drinkId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", drink=" + drink + ", orderOrSet=" + setOrder + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDrinkId() {
		return drinkId;
	}

	public void setDrinkId(Integer drinkId) {
		this.drinkId = drinkId;
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

	public Item getDrink() {
		return drink;
	}

	public void setDrink(Item drink) {
		this.drink = drink;
	}

	public Boolean getOrderOrSet() {
		return setOrder;
	}

	public void setOrderOrSet(Boolean orderOrSet) {
		this.setOrder = orderOrSet;
	}
	

}
