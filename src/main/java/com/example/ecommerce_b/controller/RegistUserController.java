package com.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.form.RegistUserForm;
import com.example.ecommerce_b.service.RegistUserService;

@Controller
@RequestMapping("/")
public class RegistUserController {
	
	@Autowired
	private RegistUserService registUserService;
	
	@RequestMapping("toRegist")
	public String toRegist() {
		return "register_user";
	}
	
	@ModelAttribute
	public RegistUserForm setUpRegistUserForm() {
		return new RegistUserForm();
	}
	
	@RequestMapping("regist")
	public String regist(@Validated RegistUserForm form ,BindingResult result) {
		if(!form.getPassword().equals(form.getConfirmationPassword())) {
			result.rejectValue("password", null, "passwordが一致しません");
		}
		if(result.hasErrors()) {
			return"redirect:toRegist";
		}
		registUserService.registUser(form);
		return "login";
	}
}
