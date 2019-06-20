package com.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
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
		order.setPyamentMethod(rs.getInt("payment_method"));
		return order;
	};
	
	/**
	 * ユーザーIDと状態から注文情報を取得.
	 * 
	 * @param userId ユーザーID
	 * @param status 状態
	 * @return　指定したユーザーIDと状態に一致する注文情報
	 */
	public Order findByUserIdAndStatus(int userId,int status) {
		String sql = "SELECT id ,user_id , status , total_price , order_date , destination_name , "
				+ "destination_email , destination_zipcode , destination_address,destination_tel , delivery_time , payment_method " 
				+ "FROM orders WHERE user_id=:userId and status=:status;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		Order order = template.queryForObject(sql, param, ORDER_ROW_MAPPER);
		return order;
	}
	
	/**
	 * 注文情報を更新する.
	 * 
	 * @param order 注文情報
	 */
	public void updateOrder(Order order) {
		String sql = "update orders set status=:status , total_price=totalPrice , order_date=:orderDate , destination_name=:destinationName , " + 
					 "destination_email=:destinationEmail , destination_zipcode=:destinationZipcode , destination_address=:destinationAddress,"
					 + "destination_tel=:destinationTel , delivery_time=:deliveryTime , payment_method=:paymentMethod"
					 + "where id=:id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(sql, param);
	}
}
