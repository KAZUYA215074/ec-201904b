package com.example.ecommerce_b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Comment;
import com.example.ecommerce_b.repository.CommentRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private CommentRepository commentRepository;
	
	@RequestMapping("")
	public String adminIndex(Model model) {
		List<Comment> commentList = commentRepository.findAll();
		System.out.println(commentList);
		model.addAttribute("commentList",commentList);
		return "admin";
	}
}
