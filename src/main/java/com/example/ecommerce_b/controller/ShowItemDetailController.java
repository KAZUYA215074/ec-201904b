package com.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.domain.Set;
import com.example.ecommerce_b.service.GetItemDetailService;
import com.example.ecommerce_b.service.GetItemListService;
import com.example.ecommerce_b.service.GetSetDetailService;

/**
 * 商品の詳細表示をするコントローラ.
 * 
 * @author momoyo kanie
 */
@Controller
@RequestMapping("/show-detail")
public class ShowItemDetailController {

	@Autowired
	private GetItemDetailService getItemDetailService;
	@Autowired
	private GetSetDetailService getSetDetailService;
	@Autowired
	private GetItemListService getItemListService;

	/**
	 * 商品の詳細ページを表示する.<br>
	 * セット商品の場合は、セットに含まれるピザ、サイドメニューとドリンク一覧の情報も取得する。
	 * 
	 * @param id 表示する商品のID
	 * @return 商品詳細ページ
	 */
	@RequestMapping("")
	public String showDetali(int id, int category, Model model) {

		model.addAttribute("category", category);

		if (category == 4) {
			// セット商品情報と含まれるピザ一覧の取得
			Set set = getSetDetailService.getDetail(id);
			List<Item> setPizzaList = getSetDetailService.getSetPizzaList(set.getId(), set.getPizzaLPrice());
			model.addAttribute("set", set);
			model.addAttribute("setPizzaList", setPizzaList);

			// 選択できるピザ、サイドメニュー、ドリンクの数
			int maxPizasize;
			int maxSidesize;
			int maxDrinksize;
			
			if (set.getId() == 4) {
				maxPizasize = 3;
				maxSidesize = 3;
				maxDrinksize = 5;
			} else {
				maxPizasize = 1;
				maxSidesize = 1;
				maxDrinksize = 2;
			}
			model.addAttribute("maxPizasize", maxPizasize);
			model.addAttribute("maxSidesize", maxSidesize);
			model.addAttribute("maxDrinksize", maxDrinksize);

			// トッピング一覧の取得
			model.addAttribute("setToppingList", getSetDetailService.getToppingList());

			// サイドメニュー一覧の取得
			List<Item> setSideList = getItemListService.getAll(2, "id");
			model.addAttribute("setSideList", setSideList);
			// ドリンクメニュー一覧の取得
			List<Item> setDrinkList = getItemListService.getAll(3, "id");
			model.addAttribute("setDrinkList", setDrinkList);

			return "set_detail";
		} else {
			model.addAttribute("item", getItemDetailService.getDetail(id));
			return "item_detail";
		}
	}

}
