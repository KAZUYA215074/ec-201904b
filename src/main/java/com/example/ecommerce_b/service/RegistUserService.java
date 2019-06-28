package com.example.ecommerce_b.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce_b.domain.User;
import com.example.ecommerce_b.form.RegistUserForm;
import com.example.ecommerce_b.repository.UserRepository;

/**
 * ユーザ登録のサービスクラス.
 * 
 * @author knmrmst
 *
 */
@Service
public class RegistUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * ユーザ情報をDBに登録する.
	 * 
	 * @param form ユーザ登録フォーム
	 * @throws ParseException 
	 */
	public void registUser(RegistUserForm form) throws ParseException {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setMailAddress(form.getEmail());
		user.setPassword(encodePassword(user.getPassword()));
		String zipCode = user.getZipcode().replace("-", "");
		user.setZipcode(zipCode);
		String telephone = user.getTelephone().replace("-", "");
		user.setTelephone(telephone);
		Date birthday = stringToDate(form.getBirthday());
		user.setBirthday(birthday);
		userRepository.insert(user);
	}

	/**
	 * 引数のメールアドレスと一致するユーザが登録されているかを確認する.
	 * 
	 * @param email メールアドレス
	 * @return 登録されていればtrue,登録されていなければfalse
	 */
	public boolean isExist(String email) {
		if (userRepository.findByMailAddress(email) != null) {
			return true;
		}
		return false;
	}

	private String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	static Date stringToDate(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Date型変換
		Date formatDate = sdf.parse(dateStr);
		return formatDate;
	}

}
