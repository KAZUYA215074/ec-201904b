package com.example.ecommerce_b.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.repository.ItemRepository;

/**
 * 商品一覧の表示・検索を行うサービス.
 * 
 * @author momoyo kanie
 */
@Service
@Transactional
public class GetItemListService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品一覧を取得する.<br>
	 * statusのパラメータで並び替えをする。
	 * 
	 * @param status 並び替えをするパラメータ
	 * @return 取得した商品情報一覧
	 */
	public List<Item> getAll(String status) {
		List<Item> itemList = itemRepository.findAll(status);

		return sortByStatus(status, itemList);
	}

	/**
	 * 名前のあいまい検索を行う.<br>
	 * statusのパラメータで並び替えをする。
	 * 
	 * @param name   検索を行う文字列
	 * @param status 並び替えをするパラメータ
	 * @return 取得した商品情報一覧
	 */
	public List<Item> searchLikeName(String name, String status) {
		List<Item> itemList = itemRepository.findLikeName(name, status);

		return sortByStatus(status, itemList);
	}

	/**
	 * 与えられたパラメータで並び替えをする. ステータスが"高い"なら並び替えを行う.
	 * 
	 * @param status
	 * @return 並び替えをした商品情報一覧
	 */
	public List<Item> sortByStatus(String status, List<Item> itemList) {

		if ("高い".equals(status)) {
			Collections.reverse(itemList);
		}

		return itemList;
	}

}
