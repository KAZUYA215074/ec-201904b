package com.example.ecommerce_b.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	@RequestMapping("/to-order")
	public String toOrder(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		Order order = orderService.serchByUserIdNotOrdered(userId);
		model.addAttribute("order",order);
		System.out.println(order);
		return "order_confirm";
	}
	
	/**
	 * 注文をする.
	 * 
	 * @param form
	 * @return 注文確認画面
	 */
	@RequestMapping("/order")
	public String order(@Validated OrderForm form,
			                   BindingResult result,
			                    Model model ){
		if(result.hasErrors()) {
			return toOrder(model);
		}
		
		Integer userId = (Integer) session.getAttribute("userId");
		Order order = orderService.serchByUserIdNotOrdered(userId);
		BeanUtils.copyProperties(form, order);
		
		String strDeliveryTime = form.getDeliveryDate() + " " + form.getDeliveryHour() +":00:00";
		System.out.println(strDeliveryTime);
		order.setDeliveryTime(Timestamp.valueOf(strDeliveryTime));
		
		order.setOrderDate(new Date());
		
		if(form.getPaymentMethod() == 1) {
			order.setStatus(1);
		}else {
			order.setStatus(2);			
		}
		
		orderService.order(order);
//		sendMailService.mail(order);
		
		return "order_finished";
	}

}
