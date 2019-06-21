package com.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.form.RegistUserForm;
import com.example.ecommerce_b.service.RegistUserService;

/**
 * ユーザ登録のコントローラ.
 * 
 * @author knmrmst
 *
 */
@Controller
@RequestMapping("/")
public class RegistUserController {
	
	@Autowired
	private RegistUserService registUserService;
	
	
	@ModelAttribute
	public RegistUserForm setUpRegistUserForm() {
		return new RegistUserForm();
	}

	/**
	 * ユーザ登録画面への遷移.
	 * 
	 * @return ユーザ登録画面
	 */
	@RequestMapping("to-regist")
	public String toRegist() {
		return "register_user";
	}
	
	/**
	 * ユーザを登録する.
	 * 
	 * @param form ユーザ登録フォーム
	 * @param result　エラー処理
	 * @return　ユーザ登録に成功すればログイン画面、失敗すればユーザ登録画面にリダイレクト
	 */
	@RequestMapping("regist")
	public String regist(@Validated RegistUserForm form ,BindingResult result) {
		if(!form.getPassword().equals(form.getConfirmationPassword())) {
			result.rejectValue("password", null, "passwordが一致しません");
		}
		boolean isExist = registUserService.isExist(form.getEmail());
		if(isExist) {
			result.rejectValue("email", null, "すでに登録されています");
		}
		if(result.hasErrors()) {
			return toRegist();
		}
		return "login";
	}
}
