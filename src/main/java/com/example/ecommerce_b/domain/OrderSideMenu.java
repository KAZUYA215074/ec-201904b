package com.example.ecommerce_b.domain;

/**
 * 注文されたサイドメニューのドメイン.
 * 
 * @author ayane.tanaka
 *
 */
public class OrderSideMenu {
	/** id */
	private Integer id;
	/** サイドメニューid */
	private Integer sideMenuId;
	/** 注文id */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイドメニュー */
	private Item sideMenu;
	/**単品=false ,セット=true */
	private Boolean setOrder;
	
	/** コンストラクタ */
	public OrderSideMenu() {
	}
	/** コンストラクタ */
	public OrderSideMenu(Integer id, Integer sideMenuId, Integer orderId, Integer quantity, Item sideMenu,
			Boolean orderOrSet) {
		super();
		this.id = id;
		this.sideMenuId = sideMenuId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.sideMenu = sideMenu;
		this.setOrder = orderOrSet;
	}

	@Override
	public String toString() {
		return "OrderSideMenu [id=" + id + ", sideMenuId=" + sideMenuId + ", orderId=" + orderId + ", quantity="
				+ quantity + ", sideMenu=" + sideMenu + ", orderOrSet=" + setOrder + "]";
	}

	//get&set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSideMenuId() {
		return sideMenuId;
	}

	public void setSideMenuId(Integer sideMenuId) {
		this.sideMenuId = sideMenuId;
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

	public Item getSideMenu() {
		return sideMenu;
	}

	public void setSideMenu(Item sideMenu) {
		this.sideMenu = sideMenu;
	}

	public Boolean getOrderOrSet() {
		return setOrder;
	}

	public void setOrderOrSet(Boolean orderOrSet) {
		this.setOrder = orderOrSet;
	}
	
	
}
