package com.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.OrderTopping;

/**
 * 注文トッピングテーブルを管理するリポジトリ.
 * 
 * @author ayane.tanaka
 *
 */
@Repository
public class OrderToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<OrderTopping> ORDERTOPPING_ROW_MAPPER=(rs,i) ->{
		OrderTopping orderTopping=new OrderTopping();
		orderTopping.setId(rs.getInt("id"));
		orderTopping.setId(rs.getInt("topping_id"));
		orderTopping.setId(rs.getInt("order_item_id"));
		return orderTopping;
	};
	
	/**
	 * 注文トッピングのidから注文トッピングを検索する.
	 * 
	 * @param orderItemId 取得したい注文トッピングのid
	 * @return 注文トッピング
	 */
	public OrderTopping load(Integer orderToppingId){
		String sql="select id,topping_id,order_item_id from order_toppings where id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderToppingId);
		OrderTopping topping=template.queryForObject(sql, param, ORDERTOPPING_ROW_MAPPER);
		return topping;
	}
	
	/**
	 * 注文トッピングを追加する.
	 * 
	 * @param topping 追加する注文トッピング
	 */
	public void insertOrderItem(OrderTopping topping) {
		
	}
	
	/**
	 * 削除する注文商品のトッピングを削除する.
	 * 
	 * @param id 削除する注文商品のid
	 */
	public void deleteOrderItem(Integer id) {
		
	}

}
