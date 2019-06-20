package com.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.repository.ItemRepository;
import com.example.ecommerce_b.repository.ToppingRepository;

/**
 * 商品詳細を行うサービス.
 * 
 * @author momoyo kanie
 */
@Service
@Transactional
public class GetItemDetailService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * 商品詳細を取得する.
	 * 
	 * @param id 取得する商品のID
	 * @return 取得した商品情報
	 */
	public Item getDetail(int id) {
		Item item = itemRepository.load(id);
		item.setToppingList(toppingRepository.findAll());

		return item;
	}

}
