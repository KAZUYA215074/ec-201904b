package com.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.service.GetItemListService;

/**
 * 商品一覧の表示・検索を行うコントローラ.
 * 
 * @author momoyo kanie
 */
@Controller
@RequestMapping("/")
public class ShowItemListController {

	@Autowired
	private GetItemListService getItemListService;

	/**
	 * 商品一覧を表示する. statusがnullの時はid順に並べる。
	 * 
	 * @param status 並び替えるパラメータ
	 * @param model  モデル
	 * @return 商品一覧表示画面
	 */
	@RequestMapping("")
	public String showList(String status, Model model) {

		if (status == null) {
			status = "id";
		}
		List<Item> itemList = getItemListService.getAll(status);
		model.addAttribute("itemList", itemList);
		model.addAttribute("status", status);

		return "item_list";
	}

	/**
	 * 商品名のあいまい検索を行う. <br>
	 * 入力が空だった場合は全件検索を行う。
	 * 一致するものがなければメッセージを表示する。
	 * 
	 * @param name 検索する名前
	 * @param status 並び替えをするステータス
	 * @return 商品一覧ページ
	 */
	@RequestMapping("/search-like-name")
	public String searchLikeName(String code, String status, Model model) {
		List<Item> itemList;
		if ("".equals(code)) {
			itemList = getItemListService.getAll(status);
		} else {
			itemList = getItemListService.searchLikeName(code, status);
		}
		
		// 一致するものがなかった場合は商品一覧とメッセージを表示する
		if(itemList.size() == 0) {
			itemList = getItemListService.getAll(status);
			model.addAttribute("notMatchMessage", "該当する商品がありません");
		}
		model.addAttribute("itemList", itemList);
		model.addAttribute("status", status);
		model.addAttribute("code", code);

		return "item_list";
	}

}
