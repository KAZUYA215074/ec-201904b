package com.example.ecommerce_b.domain;

import java.util.Date;
import java.util.List;

/**
 * 注文のためのドメイン.
 * 
 * @author ryuheisugita
 */
public class Order {
	
	/** ID */
	private Integer id;
	/** ユーザーID */
	private Integer userId;
	/** 状態 */
	private Integer status;
	/** 合計金額 */
	private Integer totalPrice;
	/** 注文日 */
	private Date orderDate;
	/** 宛先氏名 */
	private String destinationName;
	/** 宛先Eメール */
	private String destinationEmail;
	/** 宛先郵便番号 */
	private String destinationZipcode;
	/** 宛先住所 */
	private String destinationAddress;
	/** 宛先TEL */
	private String destinationTell;
	/** 配達時間 */
	private String deliveryTime;
	/** 支払い方法 */
	private Integer pyamentMethod;
	/** ユーザー */
	private User user;
	/** 注文アイテムリスト */
	private List<Order> orderList;
	
	/**
	 * 合計金額から消費税を計算する.
	 * 
	 * @ 消費税
	 */
	public int getTax() {
		return (int) (this.getTotalPrice()*0.08);
	}
	
	/**
	 * 合計金額から消費税込みの金額を計算する.
	 * 
	 * @ 消費税込みの金額
	 */
	public int getCalcTotalPrice() {
		return (int) (this.getTotalPrice()*1.08);		
	}

	/** 引数なしのコンストラクタ */
	public Order() {}
	
	/** コンストラクタ */
	public Order(Integer id, Integer userId, Integer status, Integer totalPrice, Date orderDate, String destinationName,
			String destinationEmail, String destinationZipcode, String destinationAddress, String destinationTell,
			String deliveryTime, Integer pyamentMethod, User user, List<Order> orderList) {
		super();
		this.id = id;
		this.userId = userId;
		this.status = status;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.destinationName = destinationName;
		this.destinationEmail = destinationEmail;
		this.destinationZipcode = destinationZipcode;
		this.destinationAddress = destinationAddress;
		this.destinationTell = destinationTell;
		this.deliveryTime = deliveryTime;
		this.pyamentMethod = pyamentMethod;
		this.user = user;
		this.orderList = orderList;
	}

	/** getter/setter */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTell() {
		return destinationTell;
	}

	public void setDestinationTell(String destinationTell) {
		this.destinationTell = destinationTell;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPyamentMethod() {
		return pyamentMethod;
	}

	public void setPyamentMethod(Integer pyamentMethod) {
		this.pyamentMethod = pyamentMethod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
}
