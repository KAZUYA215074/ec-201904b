package com.example.ecommerce_b.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.domain.OrderSet;
import com.example.ecommerce_b.form.OrderItemForm;
import com.example.ecommerce_b.form.OrderSetForm;
import com.example.ecommerce_b.service.CartService;

@Controller
@RequestMapping("/")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private HttpSession session;

	/**
	 * 注文商品を受け取るフォーム.
	 * 
	 * @return 注文商品のフォーム
	 */
	@ModelAttribute
	public OrderItemForm setupOrderItemForm() {
		return new OrderItemForm();
	}

	/**
	 * ショッピングカートの中身を表示する.
	 * 
	 * @param model リクエストスコープ
	 * @return ショッピングカート画面
	 */
	@RequestMapping("/show-cart")
	public String showCart(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			model.addAttribute("cartStatus", false);
			return "cart_list";
		}
		Order cart = cartService.loadOrder(userId);
		if (cart == null || (cart.getOrderItemList().size() == 0 && cart.getOrderSetList().size() == 0)) {
			model.addAttribute("cartStatus", false);
			return "cart_list";
		}
		System.out.println(cart);
		model.addAttribute("cartStatus", true);
		model.addAttribute("cart", cart);
		return "cart_list";
	}

	/**
	 * ショッピングカートに注文単品商品を追加する.
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/add-item")
	public String addItem(OrderItemForm form, Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		System.out.println(userId);
		if (userId == null) {
			userId = (int) (Math.random() * (100000) * (-1));
			System.out.println("発行したuserId=" + userId);
			session.setAttribute("userId", userId);
		}

		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(form, orderItem);
		orderItem.setSize(form.getSize().charAt(0));
		orderItem.setSetId(0);
		cartService.addOrderItem(userId, orderItem, form.getOrderToppingIdList());
		return "redirect:/show-cart";
	}

	/**
	 * ショッピングカートから注文商品を削除する.
	 * 
	 * @param orderItemId 削除する注文商品のid
	 * @return ショッピングカート画面
	 */
	@RequestMapping("/delete-item")
	public String deleteItem(String orderItemId, Integer subTotal, Boolean setOrder) {
		cartService.deleteOrderItem(Integer.parseInt(orderItemId), subTotal, setOrder);
		return "redirect:/show-cart";
	}

	/**
	 * ショッピングカートに注文セットを追加する.
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/add-set")
	public String addItem(OrderSetForm form, Model model) {
		// 仮
		form.setSetId(1);
		form.setQuantity(1);
		form.setItemId1(1);
		form.setItemId2(2);
		form.setItemId3(3);
		List<Integer> toppingList = new ArrayList<>();
		toppingList.add(1);
		form.setToppingIdList1(toppingList);
		form.setToppingIdList2(null);
		form.setToppingIdList3(null);
		form.setSideMenuId(23);
		form.setDrinkId(24);

		Integer userId = (Integer) session.getAttribute("userId");
		System.out.println(userId);
		if (userId == null) {
			userId = (int) (Math.random() * (100000) * (-1));
			System.out.println("発行したuserId=" + userId);
			session.setAttribute("userId", userId);
		}

		OrderSet orderSet = new OrderSet();
		BeanUtils.copyProperties(form, orderSet);
		cartService.addOrderSet(userId, orderSet, form);
		return "redirect:/show-cart";
	}

}
