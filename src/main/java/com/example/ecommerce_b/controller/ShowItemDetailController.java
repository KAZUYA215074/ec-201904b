package com.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.service.GetItemDetailService;

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

	/**
	 * 商品の詳細ページを表示する.
	 * 
	 * @param id 表示する商品のID
	 * @return 商品詳細ページ
	 */
	@RequestMapping("")
	public String showDetali(int id, Model model) {
		System.out.println("showDetali******");

		model.addAttribute("item", getItemDetailService.getDetail(id));

		return "item_detail";

	}

}
