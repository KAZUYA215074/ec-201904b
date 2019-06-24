package com.example.ecommerce_b.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ecommerce_b.domain.LoginUser;

/**
 * ユーザのログイン処理をさせるコントローラ.
 * 
 * @author knmrmst
 *
 */
@Controller
public class LoginUserController {
	
	@Autowired
	HttpSession session;
	
	
	/**
	 * ログイン画面を表示する.
	 * 
	 * @return
	 */
	@RequestMapping("/to-login")
	public String toLogin(Model model,@RequestParam(required = false) String error,HttpServletRequest request) {
		System.out.println("/to-login *****");
		if (error != null) {
			System.err.println("login failed");
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
		}
		if(!"http://localhost:8080/to-login".equals(request.getHeader("REFERER"))||"http://localhost:8080/to-login?error=true".equals(request.getHeader("REFERER"))) {			
			String beforePage = request.getHeader("REFERER");
			System.out.println("/to-login "+beforePage);
			session.setAttribute("beforePage",beforePage);
		}
		
		return "login";
	}
	/**
	 * ログイン処理後に画面を表示させる.
	 * 
	 * @param loginUser ログインしたユーザの情報
	 * @return　デフォルトでは商品一覧画面
	 */
	@RequestMapping("/success-login")
	public String login(@AuthenticationPrincipal LoginUser loginUser) {
		session.setAttribute("userId", loginUser.getUser().getId());
		String beforePage = (String) session.getAttribute("beforePage");
		System.out.println("/login "+beforePage);
		System.out.println("http://localhost:8080/show-cart".equals(beforePage));
		if("http://localhost:8080/show-cart".equals(beforePage)) {
			return "redirect:/to-order";
		}
		
		return "redirect:/";
	}
}
