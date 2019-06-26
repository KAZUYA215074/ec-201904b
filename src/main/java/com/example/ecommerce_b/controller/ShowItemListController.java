package com.example.ecommerce_b.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.domain.Set;
import com.example.ecommerce_b.service.GetItemListService;
import com.example.ecommerce_b.service.GetSetListService;

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
	@Autowired
	private GetSetListService getSetListService;

	// 一ページに表示する商品の数
	private static final Integer VIEW_SIZE = 9;
	// 横に並べる数
	private static final Integer VIEW_SIDE_SIZE = 3;

	/**
	 * 商品一覧を表示する. 曖昧検索もここで行う.<br>
	 * statusがnullの時はid順に並べる。 検索条件がnullだった場合は全件検索を行う。
	 * 
	 * @param status 並び替えるパラメータ
	 * @param model  モデル
	 * @return 商品一覧表示画面
	 */
	@RequestMapping("")
	public String showList(String status, Integer page, Integer category, String code, Model model) {

		// ページング機能追加
		if (page == null) {
			// ページ数の指定が無い場合は1ページ目を表示させる
			page = 1;
		}

		// statusが空の場合はid順に並べて表示する
		if (status == null) {
			status = "id";
		}

		// categoryが空の場合はピザ一覧を表示する
		if (category == null) {
			category = 1;
		}

		// 商品検索
		int pageTotalNumbers; // トータルのページ数
		// categoryが4の場合はsetを検索
		if (category == 4) {
			List<Set> setList = getShowSetList(status, code, model);
			pageTotalNumbers = createSetPageList(page, setList, model);
			model.addAttribute("itemList", setList);
		} else {
			List<Item> itemList = getShowItemList(status, category, code, model);
			pageTotalNumbers = createItemPageList(page, itemList, model);
			model.addAttribute("itemList", itemList);
		}

		List<Integer> pageNuberList = calcPageNumbers(model, pageTotalNumbers);

		model.addAttribute("pageTotalNumbers", pageTotalNumbers);
		model.addAttribute("pageNuberList", pageNuberList);
		model.addAttribute("status", status);
		model.addAttribute("category", category);

		return "item_list";
	}

	/**
	 * 1ページに表示させる商品数を格納し、最大ページ数を返す.<br>
	 * 横に並べる用のリストも作る。
	 * 
	 * @param page     表示ページ番号
	 * @param itemList 商品一覧
	 * @param model    モデル
	 * @return 最大ページ数
	 */
	private int createItemPageList(Integer page, List<Item> itemList, Model model) {
		// １ページに表示させる商品リストを絞り込み
		List<Item> itemPageList = getItemListService.showListPaging(page, VIEW_SIZE, itemList);

		// 横に並べる用のリストを作る
		List<List<Item>> TabeitemParentList = new ArrayList<>();
		List<Item> TabeitemChildList = new ArrayList<>();
		for (int i = 0; i < itemPageList.size(); i++) {
			TabeitemChildList.add(itemPageList.get(i));
			if ((i + 1) % VIEW_SIDE_SIZE == 0) {
				TabeitemParentList.add(TabeitemChildList);
				TabeitemChildList = new ArrayList<>();
			}
		}
		// 商品リストがVIEW_SIDE_SIZEで割り切れないときだけ、余りの分を入れる
		if (itemPageList.size() % VIEW_SIDE_SIZE != 0) {
			TabeitemParentList.add(TabeitemChildList);
		}

		model.addAttribute("TabeParentList", TabeitemParentList);
		// ページングのリンクに使うページ数をスコープに格納
		// List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
		int pageTotalNumbers = itemList.size() / VIEW_SIZE;
		if (itemList.size() % VIEW_SIZE != 0) {
			pageTotalNumbers++;
		}

		return pageTotalNumbers;
	}

	/**
	 * 1ページに表示させるセット商品数を格納し、最大ページ数を返す.<br>
	 * 横に並べる用のリストも作る。
	 * 
	 * @param page     表示ページ番号
	 * @param itemList セット商品一覧
	 * @param model    モデル
	 * @return 最大ページ数
	 */
	private int createSetPageList(Integer page, List<Set> setList, Model model) {
		// １ページに表示させる商品リストを絞り込み
		List<Set> setPageList = getSetListService.showListPaging(page, VIEW_SIZE, setList);

		// 横に並べる用のリストを作る
		List<List<Set>> TabeParentList = new ArrayList<>();
		List<Set> TabeChildList = new ArrayList<>();
		for (int i = 0; i < setPageList.size(); i++) {
			TabeChildList.add(setPageList.get(i));
			if ((i + 1) % VIEW_SIDE_SIZE == 0) {
				TabeParentList.add(TabeChildList);
				TabeChildList = new ArrayList<>();
			}
		}
		// 商品リストがVIEW_SIDE_SIZEで割り切れないときだけ、余りの分を入れる
		if (setPageList.size() % VIEW_SIDE_SIZE != 0) {
			TabeParentList.add(TabeChildList);
		}

		model.addAttribute("TabeParentList", TabeParentList);
		// ページングのリンクに使うページ数をスコープに格納
		// List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
		int pageTotalNumbers = setList.size() / VIEW_SIZE;
		if (setList.size() % VIEW_SIZE != 0) {
			pageTotalNumbers++;
		}

		return pageTotalNumbers;
	}

	/**
	 * セット商品以外の商品一覧を取得する.
	 * 
	 * @param status   並び替えを行う値
	 * @param category カテゴリ(ピザ=1,サイドメニュー=2,ドリンク=3)
	 * @param code     検索ワード
	 * @param model    モデル
	 * @return 表示する商品一覧
	 */
	private List<Item> getShowItemList(String status, Integer category, String code, Model model) {
		List<Item> itemList = null;
		if (code == null || "".equals(code)) {
			itemList = getItemListService.getAll(category, status);
		} else {
			itemList = getItemListService.searchLikeName(category, code, status);
			model.addAttribute("code", code);
		}

		// リストが空の場合はエラーメッセージ
		int totalSize = itemList.size();
		if (totalSize == 0) {
			model.addAttribute("notMatchMessage", "該当する商品がありません");
		}
		return itemList;
	}

	/**
	 * セット商品一覧を取得する.
	 * 
	 * @param status   並び替えを行う値
	 * @param category カテゴリ(ピザ=1,サイドメニュー=2,ドリンク=3)
	 * @param code     検索ワード
	 * @param model    モデル
	 * @return 表示する商品一覧
	 */
	private List<Set> getShowSetList(String status, String code, Model model) {
		List<Set> setList = null;
		if (code == null || "".equals(code)) {
			setList = getSetListService.getAll(status);
		} else {
			// setList = getSetListService.searchLikeName(code, status);
			// model.addAttribute("code", code);
		}

		// リストが空の場合はエラーメッセージ
		int totalSize = setList.size();
		if (totalSize == 0) {
			model.addAttribute("notMatchMessage", "該当する商品がありません");
		}
		return setList;
	}

	/**
	 * ページングのリンクに使うページ数をスコープに格納する.<br>
	 * (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
	 * 
	 * @param model            モデル
	 * @param pageTotalNumbers 最大ページ数
	 * @return ページ番号のリスト
	 */
	private List<Integer> calcPageNumbers(Model model, int pageTotalNumbers) {
		List<Integer> pageNumbers = null;
		if (pageTotalNumbers > 0) {
			pageNumbers = new ArrayList<Integer>();
			for (int i = 1; i <= pageTotalNumbers; i++) {
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
		/*
		 * List<Item> itemList = getItemListService.getAll(status);
		 * 
		 * for (Item item : itemList) { nameList.add(item.getName()); }
		 */

		return nameList;
	}

}
