package com.example.ecommerce_b.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

		@Autowired
		private CartService cartService;
		
		@Autowired
		private HttpSession session;

		
		@RequestMapping("/show-cart")
		public String showCart(Model model) {
			session.setAttribute("userId", 1);
			Integer userId=(Integer)session.getAttribute("userId");
			if(userId==null) {
				model.addAttribute("cartStatus",false);
				return "cart_list";
			}
			Order cart=cartService.loadOrder(userId);
			if(cart==null) {
				model.addAttribute("cartStatus",false);
				return "cart_list";
			}
			System.out.println(cart);
			model.addAttribute("cartStatus", true);
			model.addAttribute("cart", cart);
			return "cart_list";
		}
}
