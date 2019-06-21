package com.example.ecommerce_b.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * ユーザのログイン情報を格納するエンティティ.
 * 
 * @author knmrmst
 *
 */
public class LoginUser extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** ユーザ情報*/
	private final com.example.ecommerce_b.domain.User user;

	public LoginUser(com.example.ecommerce_b.domain.User user, Collection<GrantedAuthority> authorityList) {
		super(user.getMailAddress(), user.getPassword(), authorityList);
		this.user = user;
	}

	public com.example.ecommerce_b.domain.User getUser() {
		return user;
	}

}
