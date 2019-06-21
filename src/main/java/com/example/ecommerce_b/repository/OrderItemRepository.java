package com.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.Item;
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
		orderItem.setId(rs.getInt("order_item_id"));
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		orderItem.setSize(rs.getString("size").charAt(0));
		Item item=new Item();
		item.setId(rs.getInt("item_id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		orderItem.setItem(item);
		return orderItem;
	};
	
	/**
	 * 注文商品のidから注文商品を検索する.
	 * 
	 * @param orderItemId 取得したい注文商品のid
	 * @return 注文商品
	 */
	public OrderItem load(Integer orderItemId){
		String sql="SELECT o.id as order_item_id , o.item_id , o.order_id , o.quantity , o.size,i.name,i.description , i.price_m , i.price_l , image_path , deleted FROM order_items as o left outer join items i on (o.item_id=i.id) where o.id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderItemId);
		OrderItem item=template.queryForObject(sql, param, ORDERITEM_ROW_MAPPER);
		return item;
	}

	/**
	 * ショッピングカートのidからカート内の注文商品を全件検索する.
	 * 
	 * @param orderItemId 取得したいショッピングカートのid
	 * @return 注文商品のリスト
	 */
	public List<OrderItem> findByOrderId(Integer orderId){
		String sql="SELECT o.id as order_item_id, o.item_id , o.order_id , o.quantity , o.size,i.name,i.description , i.price_m , i.price_l , image_path , deleted FROM order_items as o left outer join items i on (o.item_id=i.id) where o.order_id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderId);
		List<OrderItem> orderItemList=template.query(sql, param, ORDERITEM_ROW_MAPPER);
		return orderItemList;
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
