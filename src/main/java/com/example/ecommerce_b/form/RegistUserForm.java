package com.example.ecommerce_b.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * ユーザ登録のフォーム.
 * 
 * @author knmrmst
 *
 */
public class RegistUserForm {

	/** ユーザ名 */
	@NotBlank(message = "お名前を入力して下さい")
	private String name;
	/** メールアドレス */
	@Email(message = "アドレスが不正です")
	@NotBlank(message = "メールアドレスを入力して下さい")
	private String email;
	/** パスワード */
	@NotBlank(message = "パスワードを入力して下さい")
	@Size(min=8,max=16,message="パスワードは{min}文字以上{max}文字以下です。")
	@Pattern(regexp="^[a-zA-Z0-9,._=!*]+$",message="パスワードに使用できる文字はの半角英数字のみです")
	private String password;
	/** 郵便番号 */
	@NotBlank(message = "郵便番号を入力してください")
	@Pattern(regexp = "^\\d{3}\\-?\\d{4}$", message = "郵便番号は7桁の数字で入力してください")
	private String zipcode;
	/** 住所 */
	@NotBlank(message = "住所を入力して下さい")
	private String address;
	/** 電話番号 */
	@NotBlank(message = "電話番号を入力して下さい")
	@Pattern(regexp = "^0\\d{1,4}-?\\d{1,4}-?\\d{4}$", message = "電話番号は半角数字で正しく入力してください")
	private String telephone;
	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力して下さい")
	private String confirmationPassword;
	/** 誕生日*/
	private String birthday;

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

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
				+ ", birthday=" + birthday + "]";
	}

}
