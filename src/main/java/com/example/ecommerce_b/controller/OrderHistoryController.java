package com.example.ecommerce_b.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.domain.OrderSet;
import com.example.ecommerce_b.domain.OrderTopping;
import com.example.ecommerce_b.domain.Set;
import com.example.ecommerce_b.domain.Topping;
import com.example.ecommerce_b.domain.User;
import com.example.ecommerce_b.service.GetItemDetailService;
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
	private HttpSession session;
	
	@Autowired
	private GetItemDetailService getItemDetailService;

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

}
