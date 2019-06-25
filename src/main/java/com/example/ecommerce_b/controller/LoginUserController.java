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
import com.example.ecommerce_b.service.CartService;

/**
 * ユーザのログイン処理をさせるコントローラ.
 * 
 * @author knmrmst
 *
 */
@Controller
public class LoginUserController {

	@Autowired
	private HttpSession session;
	@Autowired
	private CartService cartService;

	/**
	 * ログイン画面を表示する.
	 * 
	 * @return
	 */
	@RequestMapping("/to-login")
	public String toLogin(Model model, @RequestParam(required = false) String error, HttpServletRequest request) {
		System.out.println("to-login*********");
		if (error != null) {
			System.err.println("login failed");
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
		}
		//ログイン画面を更新した際にセッションを更新しない
		String url = getPath(request)+"/to-login";
		if (!url.equals(request.getHeader("REFERER"))
				|| "http://localhost:8080/to-login?error=true".equals(request.getHeader("REFERER"))) {
			String beforePage = request.getHeader("REFERER");
			session.setAttribute("beforePage", beforePage);
		}

		return "login";
	}


	/**
	 * ログイン処理後に画面を表示させる.
	 * 
	 * @param loginUser ログインしたユーザの情報
	 * @return デフォルトでは商品一覧画面
	 */
	@RequestMapping("/success-login")
	public String login(@AuthenticationPrincipal LoginUser loginUser,HttpServletRequest request) {
		/// コンフリクト修正箇所 session.setあたりの記述あってるか確認
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId != null) {
			cartService.userIdUpdate(userId, loginUser.getUser().getId());
			System.out.println("kotti");
		}
		session.setAttribute("userId", loginUser.getUser().getId());
		String refer = (String) session.getAttribute("beforePage");
		String url = getPath(request);
		if (url.equals(refer)) {
			return "redirect:/to-order";
		}

		return "redirect:/";
	}


	private String getPath(HttpServletRequest request) {
		String schema = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        String url = schema+"://"+serverName+":"+serverPort+contextPath+"/show-cart";
		return url;
	}
}
