package com.example.ecommerce_b.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.OrderSet;
import com.example.ecommerce_b.domain.Set;

/**
 * 注文されたセットテーブルを管理するリポジトリ.
 * 
 * @author ayane.tanaka
 *
 */
@Repository
public class OrderSetRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
		
	private static final RowMapper<OrderSet> ORDERSET_ROW_MAPPER=(rs,i) ->{
		OrderSet orderSet=new OrderSet();
		orderSet.setId(rs.getInt("order_set_id"));			//名称はデータベースに合わせる
		orderSet.setSetId(rs.getInt("set_id"));
		orderSet.setOrderId(rs.getInt("order_id"));
		orderSet.setQuantity(rs.getInt("quantity"));
		Set set=new Set();
		set.setId(rs.getInt("order_set_id"));
		set.setName(rs.getString("name"));
		set.setDescription(rs.getString("description"));
		set.setPrice(rs.getInt("price"));
		set.setImagePath(rs.getString("image_path"));
		set.setDeleted(rs.getBoolean("deleted"));
		set.setPizzaLPrice(rs.getInt("pizza_l_price"));
		orderSet.setSet(set);
		return orderSet;
	};

	private static final RowMapper<Integer> LIST_ROW_MAPPER=(rs,i) ->{
		return rs.getInt("id");
	};
	
	/**
	 * 注文idから検索.
	 * 
	 * @param orderId 注文id
	 * @return セットのリスト
	 */
	public List<OrderSet> findByOrderId(Integer orderId){
		String sql="SELECT o.id as order_set_id , o.set_id , o.order_id , o.quantity , s.name,s.description , s.price , s.image_path , s.deleted,s.pizza_l_price FROM order_sets as o left outer join sets s on (o.set_id=s.id) where o.order_id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderId);
		List<OrderSet> orderSetList=template.query(sql, param, ORDERSET_ROW_MAPPER);
		return orderSetList;
	}


	/**
	 *注文セットを削除する.
	 *
	 * @param id
	 * @param subTotal
	 */
	public void deleteOrderSet(Integer id) {
		String sql="delete from order_sets where id=:id;";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", id);		
		template.update(sql, param);
		
	}


	/**
	 * 注文セットを挿入する.
	 * 
	 * @param orderSet 挿入したい注文セット
	 * @return 挿入した注文セットのid
	 */
	public int insertOrderItem(OrderSet orderSet) {
		String sql="insert into order_sets (set_id,order_id,quantity) values(:setId,:orderId,:quantity) returning id";
		SqlParameterSource param=new BeanPropertySqlParameterSource(orderSet);
		int orderItemId=template.queryForObject(sql, param,Integer.class);
		return orderItemId;
	}


	/**
	 * idから注文セットを検索する.
	 * 
	 * @param id 検索したい注文id
	 * @return 検索した注文セット
	 */
	public OrderSet load(Integer id) {
		String sql="SELECT o.id as order_set_id , o.set_id , o.order_id , o.quantity , s.name,s.description , s.price , s.image_path , s.deleted,s.pizza_l_price FROM order_sets as o left outer join sets s on (o.set_id=s.id) where o.id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", id);
		OrderSet set=template.queryForObject(sql, param, ORDERSET_ROW_MAPPER);
		return set;
	}
	
	
	/**
	 * 注文セットとそのトッピングorderIdを書き換える
	 * @param userId　もとのorderのUserId
	 * @param loginOrderId　書き換えたいorderId
	 * @return
	 */
	public List<Integer> updateOrderId(Integer userId,Integer loginOrderId) {
		String sql="update order_sets set order_id=:loginOrderId where order_id=(select id from orders where user_id=:userId) returning id; ";
		SqlParameterSource param=new MapSqlParameterSource().addValue("loginOrderId", loginOrderId).addValue("userId", userId);
		List<Integer> orderSetIdList= template.query(sql, param, LIST_ROW_MAPPER);
		String sql2="select s.price from sets s left outer join order_sets o on (s.id=o.set_id) where o.id=:id;";
		List<Integer> priceList=new ArrayList<Integer>();
		for(int i=0;i<orderSetIdList.size();i++) {
			SqlParameterSource param2=new MapSqlParameterSource().addValue("id", orderSetIdList.get(i));
			priceList.add(template.queryForObject(sql2, param2, Integer.class));
		}
		return priceList;
		
	}

}

