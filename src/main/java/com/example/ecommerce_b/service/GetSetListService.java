package com.example.ecommerce_b.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_b.domain.Set;
import com.example.ecommerce_b.repository.SetRepository;

/**
 * setの情報一覧を取得するサービス.
 * 
 * @author momoyo kanie
 * 
 */
@Service
public class GetSetListService {

	@Autowired
	private SetRepository setRepository;

	/**
	 * セット商品一覧を取得する.<br>
	 * statusのパラメータで並び替えをする。
	 * 
	 * @param status 並び替えをするパラメータ
	 * @return 取得した商品情報一覧
	 */
	public List<Set> getAll(String status) {

		List<Set> setList = setRepository.findAll(returnFieldName(status));

		return sortByStatus(status, setList);
	}

	/**
	 * 名前のあいまい検索を行う.<br>
	 * statusのパラメータで並び替えをする。
	 * 
	 * @param name   検索を行う文字列
	 * @param status 並び替えをするパラメータ
	 * @return 取得した商品情報一覧
	 */
	public List<Set> searchLikeName(String name, String status) {
		List<Set> setList = setRepository.findLikeName(name, returnFieldName(status));

		return sortByStatus(status, setList);
	}

	/**
	 * ステータスから、並べ替える値の名前を返す. <br>
	 * "id"なら"id", "安い"または"高い" なら "price_m"
	 * 
	 * @param status ステータス
	 * @return 並び替える値のフィールド名
	 */
	public String returnFieldName(String status) {
		if ("id".equals(status)) {
			return "id";
		} else {
			return "price";
		}
	}

	/**
	 * 与えられたパラメータで並び替えをする.<br>
	 * ステータスが"高い"なら並び替えを行う.
	 * 
	 * @param status
	 * @return 並び替えをした商品情報一覧
	 */
	public List<Set> sortByStatus(String status, List<Set> setList) {

		if ("高い".equals(status)) {
			Collections.reverse(setList);
		}

		return setList;
	}

	/**
	 * ページング用メソッド.
	 * 
	 * @param page     表示させたいページ数
	 * @param size     １ページに表示させる商品数
	 * @param itemList 絞り込み対象リスト
	 * @return １ページに表示されるサイズ分の商品一覧情報
	 */
	public List<Set> showListPaging(int page, int size, List<Set> setList) {
		// 表示させたいページ数を-1しなければうまく動かない
		page--;
		// どの従業員から表示させるかと言うカウント値
		int startItemCount = page * size;
		// 絞り込んだ後の従業員リストが入る変数
		List<Set> list;

		if (setList.size() < startItemCount) {
			// (ありえないが)もし表示させたい従業員カウントがサイズよりも大きい場合は空のリストを返す
			list = Collections.emptyList();
		} else {
			// 該当ページに表示させる従業員一覧を作成
			int toIndex = Math.min(startItemCount + size, setList.size());
			list = setList.subList(startItemCount, toIndex);
		}

		return list;
	}

}
