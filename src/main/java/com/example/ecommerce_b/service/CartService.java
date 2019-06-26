package com.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.domain.OrderSet;
import com.example.ecommerce_b.repository.OrderItemRepository;
import com.example.ecommerce_b.repository.OrderRepository;
import com.example.ecommerce_b.repository.OrderToppingRepository;

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
	@Autowired 
	private OrderSetRepository orderSetRepository; 
//	@Autowired 
//		private UserRepository userRepository; 

	
	
	/**
	 * 現在のショッピングカート内容をユーザidから取得する.
	 * 
	 * ここではUserを詰め込んでいないので、必要なら編集するか、
	 * orderRepository.findByUserIdAndStatusにて詰め込んでください.
	 * 
	 * @param userId ログインしている(または一時的に発行されている)ユーザid
	 * @return ショッピングカート内容
	 */
	public Order loadOrder(Integer userId) {
		Order order=orderRepository.findByUserIdAndStatus(userId, 0);
		if(order==null) {
			return null;
		}
		int orderId=order.getId();
		System.out.println(orderId);
		List<OrderItem> orderItemList=orderItemRepository.findByOrderId(orderId,false);
		for(int i=0;i<orderItemList.size();i++) {
			OrderItem item=orderItemList.get(i);
			item.setOrderToppingList(orderToppingRepository.findByOrderItemId(item.getId()));
			orderItemList.set(i, item);
		}
		List<OrderSet> orderSetList=orderSetRepository.findByOrderId(orderId);

		order.setOrderItemList(orderItemList);
		return order;		//ここではUserを詰め込んでいない
	}
	
	/**
	 * 注文商品をショッピングカートに追加する.
	 * 
	 * @param userId ログインしている(または一時的に発行されている)ユーザid
	 * @param orderItemId 追加する注文商品のid
	 * @param toppingIdList 注文商品につけるトッピングidのリスト
	 */
	public void addOrderItem(Integer userId,OrderItem orderItem, List<Integer> toppingIdList) {
		Order order=orderRepository.findByUserIdAndStatus(userId, 0);
		if(order==null) {
			order=new Order(null, userId, 0,0, null, null, null, null, null, null, null, null, null, null,null);
			order.setId(orderRepository.insertOrder(order));
		}
		orderItem.setOrderId(order.getId());
		int orderItemId=orderItemRepository.insertOrderItem(orderItem);
		if(toppingIdList!=null) {
			orderToppingRepository.insertOrderTopping(orderItemId, toppingIdList);
		}
		orderItem=orderItemRepository.load(orderItemId);
		orderItem.setOrderToppingList(orderToppingRepository.findByOrderItemId(orderItem.getId()));
		orderRepository.addTotalPrice(order.getId(), orderItem.getSubTotal());;
	}
	
	/**
	 * 注文商品をショッピングカートから削除する.
	 * 
	 * @param orderItemId 削除する注文商品のid
	 */
	public void deleteOrderItem(Integer orderItemId,Integer subTotal) {
		orderItemRepository.deleteOrderItem(orderItemId,subTotal);
		orderToppingRepository.deleteOrderItem(orderItemId);
	}
	
	/**
	 * ログインしたユーザIDで仮のユーザIDを上書きする.
	 * 
	 * @param userId 元のユーザid
	 * @param loginUserId ログインしたユーザid
	 */
	public void userIdUpdate(Integer userId,Integer loginUserId) {
		Order order=orderRepository.findByUserIdAndStatus(loginUserId, 0);
		if(order==null) {
			orderRepository.updateUserId(userId, loginUserId);
		}else {
			orderItemRepository.updateOrderId(userId,order.getId());
			Order newOrder=loadOrder(loginUserId);
			int newTotalPrice=0;
			for(OrderItem item :newOrder.getOrderItemList()) {
				newTotalPrice+=item.getSubTotal();
			}
			newOrder.setTotalPrice(newTotalPrice);
			orderRepository.updateOrder(newOrder);
			//orderRepository.delete(orderId);
		}
		
	}
	
}
