package com.example.ecommerce_b.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.OrderTopping;
import com.example.ecommerce_b.domain.Topping;

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
		OrderTopping orderTopping=new OrderTopping();   //orderTopping
		orderTopping.setId(rs.getInt("order_topping_id"));
		orderTopping.setToppingId(rs.getInt("topping_id"));
		orderTopping.setOrderItemId(rs.getInt("order_item_id"));
		Topping topping=new Topping();					//Topping
		topping.setId(rs.getInt("topping_id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));
		orderTopping.setTopping(topping);				//orderToppingを詰める
		return orderTopping;
	};
	
	/**
	 * 注文トッピングのidから注文トッピングを検索する.
	 * 
	 * @param orderItemId 取得したい注文トッピングのid
	 * @return 注文トッピング
	 */
	public OrderTopping load(Integer orderToppingId){
		String sql="select o.id as order_topping_id,o.topping_id,o.order_item_id,t.name,t.price_m,t.price_l from order_toppings o left outer join toppings t on o.topping_id = t.id where o.id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderToppingId);
		OrderTopping topping=template.queryForObject(sql, param, ORDERTOPPING_ROW_MAPPER);
		return topping;
	}

	/**
	 * 注文商品のidから注文商品のトッピングを全件検索する.
	 * 
	 * @param orderItemId 取得したい注文商品のid
	 * @return トッピングのリスト
	 */
	public List<OrderTopping> findByOrderItemId(Integer orderItemId){
		String sql="select o.id as order_topping_id,o.topping_id,o.order_item_id,t.name,t.price_m,t.price_l from order_toppings o left outer join toppings t on o.topping_id = t.id where order_item_id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderItemId);
		List<OrderTopping> orderToppingList=template.query(sql, param, ORDERTOPPING_ROW_MAPPER);
		return orderToppingList;
	}
	
	
	/**
	 * 注文トッピングを追加する.
	 * 
	 * @param topping 追加する注文トッピング
	 */
	public void insertOrderTopping(int orderItemId,List<Integer> toppingIdList) {
		String sql="insert into order_toppings(topping_id,order_item_id) values";
		Map<String,Integer> paramMap=new HashMap<>();
		for(int i=0;i<toppingIdList.size();i++) {
			if(i!=0) {sql+=",";}
			sql+="(:toppingId"+i+",:orderItemId)";
			paramMap.put("toppingId"+i, toppingIdList.get(i));
		}
		paramMap.put("orderItemId", orderItemId);
		SqlParameterSource param=new MapSqlParameterSource().addValues(paramMap);
		template.update(sql, param);		
	}
	
	/**
	 * 削除する注文商品のトッピングを削除する.
	 * 
	 * @param id 削除する注文商品のid
	 */
	public void deleteOrderItem(Integer orderItemId) {
		String sql="delete from order_toppings where order_item_id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderItemId);		
		template.update(sql, param);
		
	}

}
