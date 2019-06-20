package com.example.ecommerce_b.domain;

/**
 * ユーザーのためのドメイン.
 * 
 * @author ryuheisugita
 */
public class User {
	
	/** ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String telephone;
	/**　郵便番号　*/
	private String zipcode;
	
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", address=" + address + ", telephone=" + telephone + "zipcode="+zipcode+"]";
	}
		
	/** 引数なしのコンストラクタ */
	public User() {}
	
	/** コンストラクタ */
	public User(Integer id, String name, String mailAddress, String password, String address, String telephone) {
		super();
		this.id = id;
		this.name = name;
		this.mailAddress = mailAddress;
		this.password = password;
		this.address = address;
		this.telephone = telephone;
	}

	/** getter/setter */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}	

}
