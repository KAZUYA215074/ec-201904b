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
		model.addAttribute("orderList", orderList);
		return "order_history";
	}
	@RequestMapping("/history2")
	public String toOrderHistory2(Model model) {
		
		User user = new User(5, "sugita", "sugita@ryuhei", "helloworld", "狛江市", "0334300535", "2010005");
		
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		Item item = new Item(2, "name", "description", 100, 200, "image", true, null);
		item.setItemCategory(1);
//		Item item = new Item(id, name, description, priceM, priceL, imagePath, deleted, toppingList);
		Topping topping = new Topping(1, "オニオン", 200, 300);
		OrderTopping orderTopping = new OrderTopping(1, 1, 3, topping);
        List<OrderTopping> orderToppingList =new ArrayList<OrderTopping>() ;
        orderToppingList.add(0, orderTopping);
		OrderItem orderItem = new OrderItem(3, 10, 5, null, 2, 'L', item, orderToppingList);
		orderItemList.add(0, orderItem);
		
		List<OrderSet> orderSetList = new ArrayList<>();
		Set set = new Set(1, "nameset", "description", 300, "img", true,1000);
//		Set set = new Set(id, name, description, price, imagePath, deleted);
		OrderItem orderItemPiza = new OrderItem(3, 10, 5, null, 2, 'L', item, orderToppingList);
//		OrderItem item = new OrderItem(id, itemId, orderId, setId, quantity, size, item, orderToppingList);
		Item drink = getItemDetailService.getDetail(21);
		Item drink2 = getItemDetailService.getDetail(21);
		drink.setItemCategory(2);
		drink2.setItemCategory(2);
		OrderItem orderItemDrink = new OrderItem(3, 10, 5, null, 2, 'L', drink, orderToppingList);
		OrderItem orderItemDrink2 = new OrderItem(3, 10, 5, null, 2, 'L', drink2, orderToppingList);
		List<OrderItem> orderItemList2 = new ArrayList<>();
		orderItemList2.add(0, orderItemPiza);
		orderItemList2.add(0, orderItemDrink);
		orderItemList2.add(0, orderItemDrink2);
		OrderSet orderSet = new OrderSet(1, 1, 5, 1, set,orderItemList2);
//		OrderSet set = new OrderSet(id, setId, orderId, quantity, set, orderItemList)
		orderSetList.add(0, orderSet);
		
		Order cart = new Order(5, 5, 0, 300, null, null, null, null, null, null, null, null, user, orderItemList, orderSetList);
		List<Order> orderList = new ArrayList<>();
		orderList.add(0, cart);
		model.addAttribute("orderList", orderList);
		
		return "order_history";
	}

}
