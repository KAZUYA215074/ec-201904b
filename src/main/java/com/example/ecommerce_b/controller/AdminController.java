package com.example.ecommerce_b.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Comment;
import com.example.ecommerce_b.domain.Item;
import com.example.ecommerce_b.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("")
	public String adminIndex(Model model) {
		List<Comment> commentList = adminService.findAllComment();
		model.addAttribute("commentList",commentList);
		model.addAttribute("salesMap", adminService.getSales());
		Map<Item,Integer> rankMap = adminService.getRank();
		Map<Item,Integer> pizzaRankMap = new LinkedHashMap<>();
		Map<Item,Integer> sideRankMap = new LinkedHashMap<>();
		Map<Item,Integer> drinkRankMap = new LinkedHashMap<>();
		
		for(Map.Entry<Item,Integer> entry :rankMap.entrySet()) {
			if(entry.getKey().getItemCategory()==1) {
				pizzaRankMap.put(entry.getKey(), entry.getValue());
			}else if(entry.getKey().getItemCategory()==2) {
				sideRankMap.put(entry.getKey(), entry.getValue());
			}else if(entry.getKey().getItemCategory()==3) {
				drinkRankMap.put(entry.getKey(), entry.getValue());
			}
		}
		model.addAttribute("pizzaRankMap",pizzaRankMap);
		model.addAttribute("sideRankMap",sideRankMap);
		model.addAttribute("drinkRankMap",drinkRankMap);
		return "admin";
	}
}
