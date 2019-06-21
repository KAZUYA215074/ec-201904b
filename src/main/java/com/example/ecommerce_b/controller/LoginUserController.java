package com.example.ecommerce_b.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginUserController {
	
	@RequestMapping("/to-login")
	public String toLogin() {
		return "login";
	}
	@RequestMapping("/login")
	public String login() {
		return "item_list";
	}
}
