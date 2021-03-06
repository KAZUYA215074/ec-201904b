package com.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.repository.OrderItemRepository;
import com.example.ecommerce_b.repository.OrderRepository;
import com.example.ecommerce_b.repository.OrderToppingRepository;
import com.example.ecommerce_b.repository.UserRepository;

/**
 * 注文情報を操作するサービス.
 * 
 * @author ryuheisugita
 */
@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	/**
	 * ユーザーIDから注文前の注文情報を取得する.
	 * 
	 * @param userId ユーザーID
	 * @return 指定したユーザーIDの注文前の注文情報
	 */
	public Order serchByUserIdNotOrdered(int userId) {
		int status = 0;
		Order order = orderRepository.findByUserIdAndStatus(userId, status);
		if(order==null) {
			return null;
		}
		int orderId=order.getId();
		System.out.println(orderId);
		List<OrderItem> orderItemList=orderItemRepository.findByOrderId(orderId);
		for(int i=0;i<orderItemList.size();i++) {
			OrderItem item=orderItemList.get(i);
			item.setOrderToppingList(orderToppingRepository.findByOrderItemId(item.getId()));
			orderItemList.set(i, item);
		}
		order.setOrderItemList(orderItemList);
		
		order.setUser(userRepository.findById(userId));
		return order;
	}
	
	/**
	 * 注文をする.
	 * 
	 * @param order 注文情報
	 */
	public void order(Order order) {
		orderRepository.updateOrder(order);
	}

}
