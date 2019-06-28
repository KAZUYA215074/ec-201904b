package com.example.ecommerce_b.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_b.domain.Comment;
import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.repository.CommentRepository;
import com.example.ecommerce_b.repository.OrderItemRepository;
import com.example.ecommerce_b.repository.OrderRepository;

@Service
public class AdminService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public List<Comment> findAllComment(){
		return commentRepository.findAll();
	}
	
	public Map<Date,Integer> getSales(){
		return orderRepository.findSaleHistory();
	}
	public Map<Item,Integer> getRank(){
		return orderItemRepository.findSaleRank();
	}
	

}
