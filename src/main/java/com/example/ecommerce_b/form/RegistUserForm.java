package com.example.ecommerce_b.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegistUserForm {
	@NotBlank(message="お名前を入力して下さい")
	private String name;
	@Email(message = "アドレスが不正です")
	@NotBlank(message="メールアドレスを入力して下さい")
	private String email;
	@NotBlank(message="パスワードを入力して下さい")
	private String password;
	@NotBlank(message="郵便番号を入力してください")
	@Pattern(regexp = "^\\d{3}\\-?\\d{4}$",message="郵便番号は7桁の数字で入力してください")
	private String zipcode;
	@NotBlank(message="住所を入力して下さい")
	private String address;
	@NotBlank(message="電話番号を入力して下さい")
	private String telephone;
	@NotBlank(message="確認用パスワードを入力して下さい")
	private String confirmationPassword;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	@Override
	public String toString() {
		return "RegistUserForm [name=" + name + ", email=" + email + ", password=" + password + ", zipcode=" + zipcode
				+ ", address=" + address + ", telephone=" + telephone + ", confirmationPassword=" + confirmationPassword
				+ "]";
	}

}
