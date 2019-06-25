package com.example.ecommerce_b.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.Order;

/**
 * ordersテーブルを操作するリポジトリ.
 * 
 * @author ryuheisugita
 */
@Repository
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));
		return order;
	};

	/**
	 * ユーザーIDと状態から注文情報を取得.
	 * 
	 * @param userId ユーザーID
	 * @param status 状態
	 * @return　指定したユーザーIDと状態に一致する注文情報、なければnull
	 */
	@SuppressWarnings("finally")
	public Order findByUserIdAndStatus(int userId,int status) {
		String sql = "SELECT id ,user_id , status , total_price , order_date , destination_name , "
				+ "destination_email , destination_zipcode , destination_address,destination_tel , delivery_time , payment_method "
				+ "FROM orders WHERE user_id=:userId and status=:status;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		Order order=null;
		try {
			order = template.queryForObject(sql, param, ORDER_ROW_MAPPER);
		}catch(EmptyResultDataAccessException e) {
			System.out.println(e);
		}finally {
			return order;
		}
	}

	/**
	 * ユーザーIDから注文情報を取得.
	 * 
	 * @param userId ユーザーID
	 * @return 指定したユーザーIDの注文情報
	 */
	public List<Order> findByUserId(int userId) {
		String sql = "SELECT id ,user_id , status , total_price , order_date , destination_name , "
				+ "destination_email , destination_zipcode , destination_address,destination_tel , delivery_time , payment_method "
				+ "FROM orders WHERE user_id=:userId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		List<Order> orderList = new ArrayList<Order>();
		try {
			orderList = template.query(sql, param, ORDER_ROW_MAPPER);
		} catch (Exception e) {
			return null;
		}
		return orderList;
	}
	
	/**
	 * ユーザーIDから注文情報を注文日順で取得.
	 * 
	 * @param userId ユーザーID
	 * @return 指定したユーザーIDの注文情報
	 */
	public List<Order> findByUserIdDesc(int userId) {
		String sql = "SELECT id ,user_id , status , total_price , order_date , destination_name , "
				+ "destination_email , destination_zipcode , destination_address,destination_tel , delivery_time , payment_method "
				+ "FROM orders WHERE user_id=:userId order by order_date desc;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		List<Order> orderList = new ArrayList<Order>();
		try {
			orderList = template.query(sql, param, ORDER_ROW_MAPPER);
		} catch (Exception e) {
			return null;
		}
		return orderList;
	}

	/**
	 * 注文情報を更新する.
	 * 
	 * @param order 注文情報
	 */
	public void updateOrder(Order order) {
		String sql = "update orders set status=:status , total_price=:totalPrice , order_date=:orderDate , destination_name=:destinationName , "
				+ "destination_email=:destinationEmail , destination_zipcode=:destinationZipcode , destination_address=:destinationAddress,"
				+ "destination_tel=:destinationTel , delivery_time=:deliveryTime , payment_method=:paymentMethod"
				+ " where id=:id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(sql, param);
	}
	
	/**
	 * 注文情報(ショッピングカート)	を新規発行する
	 * @param order
	 */
	public int insertOrder(Order order) {
		String sql="insert into orders(user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method) values (:userId,:status,:totalPrice,:orderDate,:destinationName,:destinationEmail,:destinationZipcode,:destinationAddress,:destinationTel,:deliveryTime,:paymentMethod) returning id";
		SqlParameterSource param=new BeanPropertySqlParameterSource(order);
		return template.queryForObject(sql, param,Integer.class);
	}
	
	/**
	 * UserIdを変更する.
	 * 
	 * @param userId 以前のユーザid
	 * @param loginUserId　新しいユーザid
	 * @return
	 */
	public int updateUserId(Integer userId,Integer loginUserId) {
		String sql="update orders set user_id=:loginUserId where user_id=:userId returning id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("loginUserId", loginUserId);
		return template.queryForObject(sql, param,Integer.class);
	}
	
	/**
	 * 小計を更新する.
	 * 
	 * @param orderId カートのid
	 * @param totalPrice 合計にプラスしたい値
	 */
	public void addTotalPrice(Integer orderId,Integer totalPrice) {
		String sql="update orders set total_price=total_price+:totalPrice where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderId).addValue("totalPrice", totalPrice);
		template.update(sql, param);
	}
}
