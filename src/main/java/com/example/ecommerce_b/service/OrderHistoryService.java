package com.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.Order.StatusEnum;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.repository.OrderItemRepository;
import com.example.ecommerce_b.repository.OrderRepository;
import com.example.ecommerce_b.repository.OrderToppingRepository;

/**
 * 注文履歴を操作するサービス.
 * 
 * @author ryuheisugita
 */
@Service
@Transactional
public class OrderHistoryService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	/**
	 * ユーザーIDから注文情報を取得する.
	 * 
	 * @param userId ユーザーID
	 * @return 指定したユーザーIDの注文情報
	 */
	public List<Order> showOrderHistory(int userId) {
		List<Order> orderList = repository.findByUserIdDesc(userId);
		if (orderList.size() == 0) {
			return null;
		}
		for (Order order : orderList) {
			int orderId = order.getId();
			System.out.println(orderId);
			List<OrderItem> orderItemList = orderItemRepository.findByOrderId(orderId,false);
			for (int i = 0; i < orderItemList.size(); i++) {
				OrderItem item = orderItemList.get(i);
				item.setOrderToppingList(orderToppingRepository.findByOrderItemId(item.getId()));
				orderItemList.set(i, item);
			}
			StatusEnum statusEnum = StatusEnum.Of(order.getStatus());
			String strStatus = statusEnum.getStatus();
			order.setStrStatus(strStatus);
			order.setOrderItemList(orderItemList);
		}
		return orderList;
	}

}
