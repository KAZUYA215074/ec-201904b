package com.example.ecommerce_b.domain;

import java.sql.Timestamp;
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
	/** 状態 */
	private String strStatus;
	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}

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
	private String destinationTel;
	/** 配達時間 */
	private Timestamp deliveryTime;
	/** 支払い方法 */
	private Integer paymentMethod;
	/** ユーザー */
	private User user;
	/** 注文アイテムリスト */
	private List<OrderItem> orderItemList;
	/** 注文セットリスト */
	private List<OrderSet> orderSetList;
	
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryTime=" + deliveryTime
				+ ", paymentMethod=" + paymentMethod + ", user=" + user + ",\\n\\t orderItemList=" + orderItemList +",orderSetList="+orderSetList+ "]";
	}

	/** 引数なしのコンストラクタ */
	public Order() {}
	
	/** コンストラクタ */
	public Order(Integer id, Integer userId, Integer status, Integer totalPrice, Date orderDate, String destinationName,
			String destinationEmail, String destinationZipcode, String destinationAddress, String destinationTel,
			Timestamp deliveryTime, Integer paymentMethod, User user, List<OrderItem> orderItemList,List<OrderSet> orderSetList) {
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
		this.destinationTel = destinationTel;
		this.deliveryTime = deliveryTime;
		this.paymentMethod = paymentMethod;
		this.user = user;
		this.orderItemList = orderItemList;
		this.orderSetList = orderSetList;
	}

	public enum StatusEnum {
		BEFOREORDER(0, "注文前"), 
		BEFOREDEPOSIT(1, "未入金"),
		AFTERDEPOSIT(2, "入金済"), 
		AFTERSHOPING(3, "発送済"),
		CANCEL(9, "キャンセル");

		private int num;
		private String status;

		private StatusEnum(int num, String status) {
			this.num = num;
			this.status = status;
		}

		public static StatusEnum Of(int num) {
			for(StatusEnum statusEnum : StatusEnum.values()) {
				if(statusEnum.num == num) {
				return statusEnum;
			}
		}
		throw new IndexOutOfBoundsException(
				"The value of number doesn't exist.");
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
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

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public List<OrderSet> getOrderSetList() {
		return orderSetList;
	}

	public void setOrderSetList(List<OrderSet> orderSetList) {
		this.orderSetList = orderSetList;
	}
	
}
