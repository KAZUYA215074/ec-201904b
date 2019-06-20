package com.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.OrderItem;

/**
 * 注文商品テーブルを管理するリポジトリ.
 * 
 * @author ayane.tanaka
 *
 */
@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<OrderItem> ORDERITEM_ROW_MAPPER=(rs,i) ->{
		OrderItem orderItem=new OrderItem();
		orderItem.setId(rs.getInt("id"));
		orderItem.setId(rs.getInt("item_id"));
		orderItem.setId(rs.getInt("order_id"));
		orderItem.setId(rs.getInt("quantity"));
		orderItem.setId(rs.getInt("size"));
		return orderItem;
	};
	
	/**
	 * 注文商品のidから注文商品を検索する.
	 * 
	 * @param orderItemId 取得したい注文商品のid
	 * @return 注文商品
	 */
	public OrderItem load(Integer orderItemId){
		String sql="select id,item_id,order_id,quantity,size from order_items where id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderItemId);
		OrderItem item=template.queryForObject(sql, param, ORDERITEM_ROW_MAPPER);
		return item;
	}
	
	/**
	 * 注文商品を追加する.
	 * 
	 * @param item 追加する注文商品
	 */
	public void insertOrderItem(OrderItem item) {
		
	}
	
	/**
	 * 注文商品を削除する.
	 * 
	 * @param id 削除する注文商品のid
	 */
	public void deleteOrderItem(Integer id) {
		
	}
}
