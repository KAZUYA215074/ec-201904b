package com.example.ecommerce_b.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.OrderSet;

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
		OrderSet ordeSet=new OrderSet();
		ordeSet.setId(rs.getInt("id"));			//名称はデータベースに合わせる
		ordeSet.setSetId(rs.getInt("set_id"));
		ordeSet.setOrderId(rs.getInt("order_id"));
		ordeSet.setQuantity(rs.getInt("quantity"));
		return ordeSet;
	};

	
	/**
	 * 注文idから検索.
	 * 
	 * @param orderId 注文id
	 * @return セットのリスト
	 */
	public List<OrderSet> findByOrderId(Integer orderId){
		String sql="SELECT id, set_id , order_id ,quantity , FROM order_sets where order_id=:id order by order_id desc";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderId);
		List<OrderSet> orderSetList=template.query(sql, param, ORDERSET_ROW_MAPPER);
		System.out.println(orderSetList);
		return orderSetList;
	}


	/**
	 *注文セットを削除する.
	 *
	 * @param id
	 * @param subTotal
	 */
	public void deleteOrderSet(Integer id) {
		String sql="delete from order_sets where id=:id returning price;";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", id);		
		template.update(sql, param);
		
	}
}
