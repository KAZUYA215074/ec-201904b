package com.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.domain.Set;
import com.example.ecommerce_b.domain.Topping;
import com.example.ecommerce_b.repository.ItemRepository;
import com.example.ecommerce_b.repository.SetRepository;
import com.example.ecommerce_b.repository.ToppingRepository;

@Service
@Transactional
public class GetSetDetailService {

	@Autowired
	private SetRepository setRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * セット商品詳細を取得する.
	 * 
	 * @param id 取得する商品のID
	 * @return 取得した商品情報
	 */
	public Set getDetail(int id) {
		Set item = setRepository.load(id);

		return item;
	}

	/**
	 * セット商品に含まれるピザ一覧を取得する
	 * 
	 * @param setId       セット商品のID
	 * @param pizzaLPrice 検索するピザの値段
	 * @return 取得したピザ情報の一覧
	 */
	public List<Item> getSetPizzaList(int setId, int pizzaLPrice) {

		// ピザの検索
		List<Item> setItemList;
		if (setId == 4) {
			setItemList = itemRepository.findLessThanLPrice(pizzaLPrice);
		} else {
			setItemList = itemRepository.findByLPrice(pizzaLPrice);
		}

		return setItemList;
	}
	

	/**
	 * トッピング一覧を取得する.
	 * 
	 * @return トッピング一覧
	 */
	public List<Topping> getToppingList() {
		return toppingRepository.findAll();
	}

}
