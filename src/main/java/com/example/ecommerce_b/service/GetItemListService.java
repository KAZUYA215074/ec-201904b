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
	public List<Item> getAll(int category, String status) {
		List<Item> itemList = itemRepository.findAll(category, returnFieldName(status));

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

//	/**
//	 * ページング用メソッド.
//	 * 
//	 * @param page     表示させたいページ数
//	 * @param size     １ページに表示させる商品数
//	 * @param itemList 絞り込み対象リスト
//	 * @return １ページに表示されるサイズ分の商品一覧情報
//	 */
//	public Page<List<Item>> showListPaging(int page, int size, List<Item> itemList) {
//		// 表示させたいページ数を-1しなければうまく動かない
//		page--;
//		// どの商品から表示させるかと言うカウント値
//		int startItemCount = page * size;
//		// 絞り込んだ後の商品リストが入る変数
//		List<List<Item>> parentList = new ArrayList<>();
//
//		if (itemList.size() < startItemCount) {
//			// (ありえないが)もし表示させたい商品カウントがサイズよりも大きい場合は空のリストを返す
//			parentList = Collections.emptyList();
//		} else {
//			// 該当ページに表示させる商品一覧を作成
//			int toIndex = Math.min(startItemCount + size, itemList.size());
//			List<Item> subList = itemList.subList(startItemCount, toIndex);
//			System.out.println("subList:" + subList.size());
//			List<Item> childList = new ArrayList<>();
//			for (int i = 0; i < subList.size(); i++) {
//				childList.add(subList.get(i));
//				if ((i + 1) % 3 == 0) {
//					parentList.add(childList);
//				}
//			}
//			// 商品リストが3で割り切れないときだけ、余りの分を入れる
//			if (subList.size() % 3 != 0) {
//				parentList.add(childList);
//			}
//		}
//
//		System.out.println("parentList:" + parentList.size());
//		// 上記で作成した該当ページに表示させる商品一覧をページングできる形に変換して返す
//		Page<List<Item>> itemPage = new PageImpl<List<Item>>(parentList, PageRequest.of(page, size), itemList.size());
//		System.out.println("itemPage:" + itemPage.getSize());
//		return itemPage;
//	}

	public List<Item> showListPaging2(int page, int size, List<Item> itemList) {
		// 表示させたいページ数を-1しなければうまく動かない
		page--;
		// どの従業員から表示させるかと言うカウント値
		int startItemCount = page * size;
		// 絞り込んだ後の従業員リストが入る変数
		List<Item> list;

		if (itemList.size() < startItemCount) {
			// (ありえないが)もし表示させたい従業員カウントがサイズよりも大きい場合は空のリストを返す
			list = Collections.emptyList();
		} else {
			// 該当ページに表示させる従業員一覧を作成
			int toIndex = Math.min(startItemCount + size, itemList.size());
			list = itemList.subList(startItemCount, toIndex);
		}

		// 上記で作成した該当ページに表示させる従業員一覧をページングできる形に変換して返す
//	    Page<Item> employeePage
//	      = new PageImpl<Item>(list, PageRequest.of(page, size), itemList.size());
		return list;
	}

}
