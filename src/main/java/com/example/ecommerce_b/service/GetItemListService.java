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
		List<Item> itemList = itemRepository.findAll(returnFieldName(status));

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
		List<Item> itemList = itemRepository.findLikeName(name, returnFieldName(status));

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

	/**
	 * ステータスから、並べ替える値の名前を返す. "id"なら"id", "安い"または"高い" なら "price_m"
	 * 
	 * @param status ステータス
	 * @return 並び替える値のフィールド名
	 */
	public String returnFieldName(String status) {
		if ("id".equals(status)) {
			return "id";
		} else {
			return "price_m";
		}
	}
}
