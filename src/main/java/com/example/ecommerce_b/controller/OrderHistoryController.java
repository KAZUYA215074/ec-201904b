package com.example.ecommerce_b.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.service.OrderHistoryService;

/**
 * 注文をするためのコントローラー
 * 
 * @author ryuheisugita
 */
@Controller
@RequestMapping("/")
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService orderHistoryService;

	@Autowired
	private HttpSession session;

	/**
	 * 注文履歴画面を表示する. test2
	 * 
	 * @param model
	 * @return 注文履歴画面
	 */
	@RequestMapping("/history")
	public String toOrderHistory(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		List<Order> orderList = orderHistoryService.showOrderHistory(userId);
		model.addAttribute("historyStatus", false);
		if (orderList == null) {
			model.addAttribute("historyStatus", true);
			return "order_history";
		}
		model.addAttribute("orderList", orderList);

		System.out.println(orderList);
		return "order_history";
	}

	public enum StatusEnum {
		BEFOREORDER(0, "注文前"), BEFOREDEPOSIT(1, "未入金"), AFTERDEPOSIT(2, "入金済"), AFTERSHOPING(3, "発送済"),
		CANCEL(9, "キャンセル");

		private int num;
		private String status;

		private StatusEnum(int num, String status) {
			this.num = num;
			this.status = status;
		}

		public static StatusEnum Of(int num) {
			for(StatusEnum statusEnum : StatusEnum.values()) {
				if(statusEnum.num == num) {
				return statusEnum;
			}
		}
		throw new IndexOutOfBoundsException(
				"The value of number doesn't exist.");
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
	}

}
