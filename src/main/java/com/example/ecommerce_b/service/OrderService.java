package com.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.repository.OrderRepository;

/**
 * 注文情報を操作するサービス.
 * 
 * @author ryuheisugita
 */
@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	/**
	 * ユーザーIDから注文前の注文情報を取得する.
	 * 
	 * @param userId ユーザーID
	 * @return 指定したユーザーIDの注文前の注文情報
	 */
	public Order serchByUserIdNotOrdered(int userId) {
		int status = 0;
		Order order = repository.findByUserIdAndStatus(userId, status);
		return order;
	}
	
	/**
	 * 注文をする.
	 * 
	 * @param order 注文情報
	 */
	public void order(Order order) {
		repository.updateOrder(order);
	}

}
