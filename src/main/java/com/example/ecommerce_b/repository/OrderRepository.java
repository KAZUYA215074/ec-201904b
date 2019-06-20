package com.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.Order;
import com.example.ecommerce_b.form.OderForm;

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
		String sql = "";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
//		Order order = template.
		return null;
	}
}
