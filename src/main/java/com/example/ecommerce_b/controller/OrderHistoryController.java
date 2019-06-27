package com.example.ecommerce_b.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.form.OrderItemForm;
import com.example.ecommerce_b.service.CartService;
import com.example.ecommerce_b.service.OrderHistoryService;

/**
 * 注文をするためのコントローラー
 * 
 * @author ryuheisugita
 */
@Controller
@RequestMapping("/")
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService orderHistoryService;
	
	@Autowired
	private CartService cartService;

	@Autowired
	private HttpSession session;

	/**
	 * 注文履歴画面を表示する. test2
	 * 
	 * @param model
	 * @return 注文履歴画面
	 */
	@RequestMapping("/history")
	public String toOrderHistory(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		List<Order> orderList = orderHistoryService.showOrderHistory(userId);
		model.addAttribute("historyStatus", false);
		if (orderList == null) {
			model.addAttribute("historyStatus", true);
			return "order_history";
		}
		
		for(int i = 0 ; i < orderList.size() ; i++ ) {
			model.addAttribute("historyItemStatus"+i, false);				
			model.addAttribute("historySetStatus"+i, false);				
			if( orderList.get(i).getOrderItemList().size() == 0) {
				model.addAttribute("historyItemStatus"+i, true);				
			}
			if( orderList.get(i).getOrderSetList().size() == 0) {
				model.addAttribute("historySetStatus"+i, true);				
			}
		}
		
		model.addAttribute("orderList", orderList);
		return "order_history";
	}

	/**
	 * 注文履歴からショッピングカートに注文単品商品を追加する.
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/buy-again-item")
	public String buyAgainItem(OrderItemForm form, Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		System.out.println(form);
		System.out.println(form.getOrderToppingIdList());
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(form, orderItem);
		orderItem.setSize(form.getSize().charAt(0));
		orderItem.setSetId(0);
		cartService.addOrderItem(userId, orderItem, form.getOrderToppingIdList());
		return "redirect:/show-cart";
	}
}
