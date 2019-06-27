package com.example.ecommerce_b.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.domain.OrderSet;
import com.example.ecommerce_b.domain.OrderTopping;
import com.example.ecommerce_b.form.OrderItemForm;
import com.example.ecommerce_b.form.OrderSetForm;
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
	
	/**
	 * ショッピングカートに注文セットを追加する.
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/buy-again-set")
	public String buyAgainSet(OrderSetForm form,Integer orderSetId, Model model) {
		System.out.println(form);

		Integer userId = (Integer) session.getAttribute("userId");

		OrderSet orderSet = new OrderSet();
		BeanUtils.copyProperties(form, orderSet);
		
//		Integer userIdSet = (Integer) session.getAttribute("userId");
//		List<Order> orderList = orderHistoryService.showOrderHistory(userIdSet);
//		for(Order order : orderList) {
//			for(OrderSet orderSet1 : order.getOrderSetList())
//			if(orderSet1.getId() == orderSetId) {
//				form.setItemId1(orderSet1.getOrderItemList().get(0).getItemId());
//				form.setItemId2(orderSet1.getOrderItemList().get(1).getItemId());
//				form.setItemId3(orderSet1.getOrderItemList().get(2).getItemId());
//				List<Integer> toppingIdList1 =new ArrayList<>();
//				for(OrderTopping orderTopping : orderSet1.getOrderItemList().get(0).getOrderToppingList()) {
//					toppingIdList1.add(orderTopping.getToppingId());
//				}
//				form.setToppingIdList1(toppingIdList1);
//				List<Integer> toppingIdList2 =new ArrayList<>();
//				for(OrderTopping orderTopping : orderSet1.getOrderItemList().get(1).getOrderToppingList()) {
//					toppingIdList1.add(orderTopping.getToppingId());
//				}
//				form.setToppingIdList2(toppingIdList2);
//				List<Integer> toppingIdList3 =new ArrayList<>();
//				for(OrderTopping orderTopping : orderSet1.getOrderItemList().get(2).getOrderToppingList()) {
//					toppingIdList1.add(orderTopping.getToppingId());
//				}
//				form.setToppingIdList3(toppingIdList3);
//			}
//		}
		System.out.println(form);
		cartService.addOrderSet(userId, orderSet, form);
		return "redirect:/show-cart";
	}
}
