package com.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

		@Autowired
		private CartService cartService;
		
		
		@RequestMapping("/show-cart")
		public String showCart() {
			
			Order cart=cartService.loadOrder(1);
			System.out.println(cart);
			return "cart_list";
		}
}
