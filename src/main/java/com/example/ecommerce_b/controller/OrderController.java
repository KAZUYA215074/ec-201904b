package com.example.ecommerce_b.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.form.OrderForm;
import com.example.ecommerce_b.service.OrderService;

/**
 * 注文をするためのコントローラー
 * 
 * @author ryuheisugita
 */
@Controller
@RequestMapping("/")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public OrderForm setUpOrderForm() {
		return new OrderForm();
	}
	
	/**
	 * 注文確認画面を表示する.
	 * 
	 * @param model
	 * @return　注文確認画面
	 */
	public String toOrder(Model model) {
		int userId = 0;//(仮)
		Order order = orderService.serchByUserIdNotOrdered(userId);
		model.addAttribute("order",order);
		return "order_cofirm";
	}
	
	/**
	 * 注文をする.
	 * 
	 * @param form
	 * @return 注文確認画面
	 */
	public String order(OrderForm form) {
		int userId = 0;//(仮)
		Order order = orderService.serchByUserIdNotOrdered(userId);
		BeanUtils.copyProperties(form, order);
		order.setOrderDate(new Date());
		if(form.getPyamentMethod() == 1) {
			order.setStatus(1);
		}else {
			order.setStatus(2);			
		}
		orderService.order(order);
//		sendMailService.mail(order);
		return "oder_finished";
	}

}
