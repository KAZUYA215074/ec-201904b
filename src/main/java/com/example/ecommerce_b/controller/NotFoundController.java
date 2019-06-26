package com.example.ecommerce_b.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.form.CommentForm;

/**
 * 404を処理するコントローラ.
 * 
 * @author momoyo kanie
 *
 */
@Controller
public class NotFoundController implements ErrorController {

	private static final String PATH = "/error";
	
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}

	@Override
	@RequestMapping(PATH)
	public String getErrorPath() {
		System.out.println("404 not found");
		return "404";
	}
}
