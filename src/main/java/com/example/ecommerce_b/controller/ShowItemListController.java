package com.example.ecommerce_b.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	private static final Integer VIEW_SIZE = 9;

	/**
	 * 商品一覧を表示する. 曖昧検索もここで行う.<br>
	 * statusがnullの時はid順に並べる。 検索条件がnullだった場合は全件検索を行う。
	 * 
	 * @param status 並び替えるパラメータ
	 * @param model  モデル
	 * @return 商品一覧表示画面
	 */
	@RequestMapping("")
	public String showList(String status, Integer page, Model model, String code) {

		// ページング機能追加
		if (page == null) {
			// ページ数の指定が無い場合は1ページ目を表示させる
			page = 1;
		}

		// statusが空の場合はid順に並べて表示する
		if (status == null) {
			status = "id";
		}

		// 商品検索
		List<Item> itemList = null;
		if (code == null) {
			itemList = getItemListService.getAll(status);
		} else {
			itemList = getItemListService.searchLikeName(code, status);
			model.addAttribute("code", code);
		}

		// リストが空の場合はエラーメッセージ
		if (itemList.size() == 0) {
			model.addAttribute("notMatchMessage", "該当する商品がありません");
		}

		// １ページに表示させる商品リストを絞り込み
		Page<Item> itemPage = getItemListService.showListPaging(page, VIEW_SIZE, itemList);
		model.addAttribute("itemPage", itemPage);

		// ページングのリンクに使うページ数をスコープに格納
		List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("itemList", itemList);
		model.addAttribute("status", status);

		return "item_list";
	}

	/**
	 * ページングのリンクに使うページ数をスコープに格納する.<br>
	 * (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
	 * 
	 * @param model        モデル
	 * @param employeePage ページング情報
	 */
	private List<Integer> calcPageNumbers(Model model, Page<Item> itemPage) {
		int totalPages = itemPage.getTotalPages();
		List<Integer> pageNumbers = null;
		if (totalPages > 0) {
			pageNumbers = new ArrayList<Integer>();
			for (int i = 1; i <= totalPages; i++) {
				pageNumbers.add(i);
			}
		}
		return pageNumbers;
	}

	/**
	 * 曖昧検索のオートコンプリート用の名前リストを取得する.<br>
	 * id順に取得する。
	 * 
	 * @return 全商品の名前リスト
	 */
	@ResponseBody
	@RequestMapping("/getAutoComplete")
	public List<String> getAutoComplete() {

		List<String> nameList = new ArrayList<String>();
		String status = "id";
		List<Item> itemList = getItemListService.getAll(status);

		for (Item item : itemList) {
			nameList.add(item.getName());
		}

		return nameList;
	}

}
