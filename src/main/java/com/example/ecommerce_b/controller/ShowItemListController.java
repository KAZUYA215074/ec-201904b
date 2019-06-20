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
	 * 商品一覧を表示する.
	 * 
	 * @param status 並び替えるパラメータ
	 * @param model  モデル
	 * @return 商品一覧表示画面
	 */
	@RequestMapping("")
	public String showList(String status, Model model) {
		
		System.out.println("/開始");
		System.out.println(status);
		if(status == null) {
			status = "id";
		}
		List<Item> itemList = getItemListService.getAll(status);
		System.out.println(itemList.size());
		model.addAttribute("itemList", itemList);

		return "item_list";
	}

}
