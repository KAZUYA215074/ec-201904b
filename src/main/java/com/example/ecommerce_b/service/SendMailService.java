package com.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.domain.OrderItem;
import com.example.ecommerce_b.domain.OrderTopping;

@Service
public class SendMailService {
	
	@Autowired
	private MailSender sender;

	@Async
	public void sendMail(Order order) {

		SimpleMailMessage msg = new SimpleMailMessage();

		String text= order.getUser().getName() + "様\n"
				+ "この度はラクラクピザをご注文ありがとうございます。\n"
				+ "以下の内容でご注文を承りました\n\n";
		for(OrderItem orderItem : order.getOrderItemList()) {
			text += "商品名：" + orderItem.getItem().getName() + "\n"
					+ "サイズ：" + orderItem.getSize() + "      個数：" + orderItem.getQuantity() + "\n";
			for(OrderTopping orderTopping : orderItem.getOrderToppingList()) {
				text += "トッピング：" + orderTopping.getTopping().getName() + "\n";
			}
			text += "\n";
		}
	   
		text += "合計金額：" + order.getCalcTotalPrice() + "円\n\n"
				+ (order.getDeliveryTime().getYear() + 1900) + "年" 
				+ (order.getDeliveryTime().getMonth() + 1) + "月" 
				+ order.getDeliveryTime().getDate() + "日" 
				+ order.getDeliveryTime().getHours() + "時" 
				+ "に以下の住所に配達予定です\n"
				+ order.getDestinationAddress() + "\n\n"
				+ "何か不明な点等ございましたらご連絡ください。\n"
				+ "今後ともラクラクピザをよろしくお願いいたします。";
		
		msg.setFrom("ryu.risa0322@gmail.com");
		msg.setTo(order.getUser().getMailAddress());
		msg.setSubject("ピザ注文決済のお知らせ");// タイトルの設定
		msg.setText(text); // 本文の設定
		sender.send(msg);
	}

}
