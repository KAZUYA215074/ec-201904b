package com.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.Order;

/**
 * odersテーブルを操作するリポジトリ.
 * 
 * @author ryuheisugita
 */
@Repository
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * ユーザーIDと状態から注文情報を取得.
	 * 
	 * @param userId ユーザーID
	 * @param status 状態
	 * @return　指定したユーザーIDと状態に一致する注文情報
	 */
	public Order findByUserIdAndStatus(int userId,int status) {
		
		return null;
	}
}
