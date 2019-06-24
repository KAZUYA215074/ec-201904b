package com.example.ecommerce_b.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 注文のためのフォーム
 * 
 * @author ryuheisugita
 */
public class OrderForm {
	
	/** 宛先氏名 */
	@NotBlank(message = "お名前を入力して下さい")
	private String destinationName;
	/** 宛先Eメール */
	@Email(message = "アドレスが不正です")
	@NotBlank(message = "メールアドレスを入力して下さい")
	private String destinationEmail;
	/** 宛先郵便番号 */
	@NotBlank(message = "郵便番号を入力してください")
	@Pattern(regexp = "^\\d{3}\\-?\\d{4}$", message = "郵便番号は7桁の数字で入力してください")
	private String destinationZipcode;
	/** 宛先住所 */
	@NotBlank(message = "住所を入力して下さい")
	private String destinationAddress;
	/** 宛先TEL */
	@NotBlank(message = "電話番号を入力して下さい")
	private String destinationTel;
	/** 配達日付 */
	@NotBlank(message = "配達日時を選択して下さい")
	private String deliveryDate;
	/** 配達時間 */
	private String deliveryHour;
	/** 支払い方法 */
	private Integer paymentMethod;

	@Override
	public String toString() {
		return "OrderForm [destinationName=" + destinationName + ", destinationEmail=" + destinationEmail
				+ ", destinationZipcode=" + destinationZipcode + ", destinationAddress=" + destinationAddress
				+ ", destinationTel=" + destinationTel + ", deliveryDate=" + deliveryDate + ", deliveryHour="
				+ deliveryHour + ", paymentMethod=" + paymentMethod + "]";
	}

	/** 引数なしのコンストラクタ */
	public OrderForm() {}
	
	/** コンストラクタ */
	public OrderForm(String destinationName, String destinationEmail, String destinationZipcode,
			String destinationAddress, String destinationTel, String deliveryDate, String deliveryHour,
			Integer paymentMethod) {
		super();
		this.destinationName = destinationName;
		this.destinationEmail = destinationEmail;
		this.destinationZipcode = destinationZipcode;
		this.destinationAddress = destinationAddress;
		this.destinationTel = destinationTel;
		this.deliveryDate = deliveryDate;
		this.deliveryHour = deliveryHour;
		this.paymentMethod = paymentMethod;
	}
	
	/** getter/setter */
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
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryHour() {
		return deliveryHour;
	}
	public void setDeliveryHour(String deliveryHour) {
		this.deliveryHour = deliveryHour;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
