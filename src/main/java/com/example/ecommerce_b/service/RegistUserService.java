package com.example.ecommerce_b.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce_b.domain.User;
import com.example.ecommerce_b.form.RegistUserForm;
import com.example.ecommerce_b.repository.RegistUserRepository;

@Service
public class RegistUserService {

	@Autowired
	private RegistUserRepository registUserRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void registUser(RegistUserForm form) {
		System.out.println(form);
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setMailAddress(form.getEmail());
		user.setPassword(encodePassword(user.getPassword()));
		registUserRepository.insert(user);
	}
	
	private String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
}
