package com.example.ecommerce_b.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import com.example.ecommerce_b.service.SendMailService;

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
	
	@Autowired
	private SendMailService sendMailService;
	
	
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
	@SuppressWarnings("deprecation")
	@RequestMapping("/to-order")
	public String toOrder(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		Order order = orderService.serchByUserIdNotOrdered(userId);
		model.addAttribute("cartItemStatus",true);
		model.addAttribute("cartSetStatus",true);
		if(order.getOrderItemList().size()==0) {
			model.addAttribute("cartItemStatus",false);
		}
		if(order.getOrderSetList().size()==0) {
			model.addAttribute("cartSetStatus",false);
		}
		model.addAttribute("order",order);
		model.addAttribute("tax",order.getTax());
		model.addAttribute("totalPrice",order.getCalcTotalPrice());
		// 年齢確認
		Date date = order.getUser().getBirthday();
		if (date.getYear() + 20 > new Date().getYear()) {
			model.addAttribute("age", "配達時に年齢を確認させていただきます。");
		} else if (date.getYear() == new Date().getYear()) {
			if (date.getMonth() > new Date().getMonth()) {
				model.addAttribute("age", "配達時に年齢を確認させていただきます。");
			} else if (date.getMonth() == new Date().getMonth()) {
				if (date.getDay() > new Date().getDay()) {
					model.addAttribute("age", "配達時に年齢を確認させていただきます。");
				}
			}
		}
		for (int i = 0; i < order.getOrderItemList().size(); i++) {
			if (order.getOrderItemList().get(i).getItem().getId() == 27) {
				model.addAttribute("alcohol", true);
			}
		}
		LocalDate localDate = LocalDate.now();
		String minDate;
		String maxDate;
		if(localDate.getMonthValue() < 10) {
		minDate = localDate.getYear() + "-0" +
				               localDate.getMonthValue()+"-"+
				               localDate.getDayOfMonth();
		localDate=localDate.plusMonths(1);
		maxDate = localDate.getYear() + "-0" +
	               localDate.getMonthValue()+"-"+
	               localDate.getDayOfMonth();
		}else {
		minDate = localDate.getYear() + "-" +
					localDate.getMonthValue()+"-"+
					localDate.getDayOfMonth();
		localDate=localDate.plusMonths(1);
		maxDate = localDate.getYear() + "-0" +
	               localDate.getMonthValue()+"-"+
	               localDate.getDayOfMonth();
		}
		
		model.addAttribute("minDate", minDate);
		model.addAttribute("maxDate",maxDate);
		return "order_confirm";
	}
	
	/**
	 * 注文をする.
	 * 
	 * @param form
	 * @return 注文確認画面
	 * @throws ParseException 
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/order")
	public String order(@Validated OrderForm form,
			                   BindingResult result,
			                    Model model ) throws ParseException{
        //過去の日付を選択出来ない
		if (form.getDeliveryDate() != "") {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdFormat.parse(form.getDeliveryDate());
			if (date.getYear() < new Date().getYear()) {
				result.rejectValue("deliveryDate", null, "その日時には配達できません");
			} else if (date.getYear() == new Date().getYear()) {
				if (date.getMonth() < new Date().getMonth()) {
					result.rejectValue("deliveryDate", null, "その日時には配達できません");
				} else if (date.getMonth() == new Date().getMonth()) {
					if (date.getDay() < new Date().getDay()) {
						result.rejectValue("deliveryDate", null, "その日時には配達できません");
					} else if (date.getDay() == new Date().getDay()) {
						if (Integer.parseInt(form.getDeliveryHour()) <= new Date().getHours()) {
							result.rejectValue("deliveryDate", null, "その日時には配達できません");
						}
					}
				}
			}
		}

		if(result.hasErrors()) {
			return toOrder(model);
		}
		
		String strDeliveryTime = form.getDeliveryDate() + " " + form.getDeliveryHour() +":00:00";
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		Order order = orderService.serchByUserIdNotOrdered(userId);
		BeanUtils.copyProperties(form, order);
		
		order.setDeliveryTime(Timestamp.valueOf(strDeliveryTime));
		
		order.setOrderDate(new Date());
		
		if(form.getPaymentMethod() == 1) {
			order.setStatus(1);
		}else {
			order.setStatus(2);			
		}
		
		orderService.order(order);
		sendMailService.sendMail(order);
		
		return "redirect:/finish";
	}
	
	/**
	 * 決済画面を表示する.
	 * 
	 * @return 決済画面
	 */
	@RequestMapping("/finish")
	public String finish() {
		return "order_finished";
	}

}
