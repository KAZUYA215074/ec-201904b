package com.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.repository.OrderItemRepository;
import com.example.ecommerce_b.repository.OrderRepository;
import com.example.ecommerce_b.repository.OrderToppingRepository;
import com.example.ecommerce_b.repository.ToppingRepository;

/**
 * ショッピングカートを管理するサービス.
 * 
 * @author ayane.tanaka
 *
 */
@Service
@Transactional
public class CartService {

	@Autowired 
		private OrderRepository orderRepository; 
	@Autowired 
		private OrderItemRepository orderItemRepository; 
	@Autowired 
		private OrderToppingRepository orderToppingRepository; 
//	@Autowired 
//		private UserRepository userRepository; 
	@Autowired 
		private GetItemDetailService getItemDetailService;  
	@Autowired 
		private ToppingRepository ToppingRepository;
	
	
	/**
	 * 現在のショッピングカート内容をユーザidから取得する.
	 * 
	 * @param userId ログインしている(または一時的に発行されている)ユーザid
	 * @return ショッピングカート内容
	 */
	public Order loadOrder(Integer userId) {
		Order order=orderRepository.findByUserIdAndStatus(userId, 0);
		if(order==null) {
			return null;
		}
		//order.setOrderList(orderList);
		return null;
	}
	
	/**
	 * 注文商品をショッピングカートに追加する.
	 * 
	 * @param userId ログインしている(または一時的に発行されている)ユーザid
	 * @param orderItemId 追加する注文商品のid
	 * @param toppingIdList 注文商品につけるトッピングidのリスト
	 */
	public void addOrderItem(Integer userId,Integer orderItemId, List<Integer> toppingIdList) {

	}
	
	/**
	 * 注文商品をショッピングカートから削除する.
	 * 
	 * @param userId ログインしている(または一時的に発行されている)ユーザid
	 * @param orderItemId 削除する注文商品のid
	 */
	public void deleteOrderItem(Integer userId,Integer orderItemId) {
		
	}
	
}
