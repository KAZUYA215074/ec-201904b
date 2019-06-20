package com.example.ecommerce_b.form;

/**
 * 注文のためのフォーム
 * 
 * @author ryuheisugita
 */
public class OderForm {
	
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
	
	@Override
	public String toString() {
		return "OderForm [destinationName=" + destinationName + ", destinationEmail=" + destinationEmail
				+ ", destinationZipcode=" + destinationZipcode + ", destinationAddress=" + destinationAddress
				+ ", destinationTell=" + destinationTell + ", deliveryTime=" + deliveryTime + ", pyamentMethod="
				+ pyamentMethod + "]";
	}

	/** 引数なしのコンストラクタ */
	public OderForm() {}
	
	/** コンストラクタ */
	public OderForm(String destinationName, String destinationEmail, String destinationZipcode,
			String destinationAddress, String destinationTell, String deliveryTime, Integer pyamentMethod) {
		super();
		this.destinationName = destinationName;
		this.destinationEmail = destinationEmail;
		this.destinationZipcode = destinationZipcode;
		this.destinationAddress = destinationAddress;
		this.destinationTell = destinationTell;
		this.deliveryTime = deliveryTime;
		this.pyamentMethod = pyamentMethod;
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

}
