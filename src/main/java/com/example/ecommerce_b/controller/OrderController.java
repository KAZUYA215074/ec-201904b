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
import com.example.ecommerce_b.domain.User;
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
	@RequestMapping("/to-order")
	public String toOrder(Model model) {
		//(仮)
		int userId = 0;
		Order order = orderService.serchByUserIdNotOrdered(userId);
		User user = new User(1, "name", "mailAddress", "password", "address", "telephone");
		order.setUser(user);
		model.addAttribute("order",order);
		return "order_confirm";
	}
	
	/**
	 * 注文をする.
	 * 
	 * @param form
	 * @return 注文確認画面
	 */
	@RequestMapping("/order")
	public String order(OrderForm form, Integer responsibleCompany) {
		int userId = 0;//(仮)
		Order order = orderService.serchByUserIdNotOrdered(userId);
		BeanUtils.copyProperties(form, order);
		Date deliveryTime = order.getDeliveryTime();
		deliveryTime.setHours(responsibleCompany);
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
