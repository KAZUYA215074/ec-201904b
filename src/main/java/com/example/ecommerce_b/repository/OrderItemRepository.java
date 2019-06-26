package com.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
		orderItem.setSetId(rs.getInt("set_id"));
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
		item.setItemCategory(rs.getInt("item_category"));
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
		String sql="SELECT o.id as order_item_id , o.item_id , o.order_id ,o.set_id, o.quantity , o.size, i.name,i.description , i.price_m , i.price_l , i.image_path , i.deleted,i.item_category FROM order_items as o left outer join items i on (o.item_id=i.id) where o.id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderItemId);
		OrderItem item=template.queryForObject(sql, param, ORDERITEM_ROW_MAPPER);
		return item;
	}

	/**
	 * ショッピングカートのidからカート内の注文商品を全件検索する.
	 * 
	 * @param orderItemId 取得したいショッピングカートのid
	 * @param setOrder アイテムを検索するときはfalse,セット内容を検索するときはtrue
	 * @return 注文商品のリスト
	 */
	public List<OrderItem> findByOrderId(Integer orderId,Boolean setOrder){
		String searchWord;
		if(setOrder) {
			searchWord="o.set_id";
		}else {
			searchWord="o.order_id";
		}
		String sql="SELECT o.id as order_item_id, o.item_id , o.order_id ,o.set_id, o.quantity , o.size, i.name,i.description , i.price_m , i.price_l , i.image_path , i.deleted,i.item_category FROM order_items as o left outer join items i on (o.item_id=i.id) where "+searchWord+"=:id order by o.id desc";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderId);
		List<OrderItem> orderItemList=template.query(sql, param, ORDERITEM_ROW_MAPPER);
		return orderItemList;
	}
	
	
	/**
	 * 注文商品を追加する.
	 * 
	 * @param item 追加する注文商品
	 * @return 注文商品のid
	 */
	public int insertOrderItem(OrderItem item) {
		String sql="insert into order_items (item_id,order_id,set_id,quantity,size) values(:itemId,:orderId,:setId,:quantity,:size) returning id";
		SqlParameterSource param=new BeanPropertySqlParameterSource(item);
		int orderItemId=template.queryForObject(sql, param,Integer.class);
		return orderItemId;
	}
	
	/**
	 * 注文商品とそのトッピングorderIdを書き換える
	 * @param userId　もとのorderのUserId
	 * @param loginOrderId　書き換えたいorderId
	 * @return
	 */
	public void updateOrderId(Integer userId,Integer loginOrderId) {
		String sql="update order_items set order_id=:loginOrderId where order_id=(select id from orders where user_id=:userId); ";
		SqlParameterSource param=new MapSqlParameterSource().addValue("loginOrderId", loginOrderId).addValue("userId", userId);
		template.update(sql, param);
	}
	
	/**
	 * 注文商品を削除する.
	 * 
	 * @param id 削除する注文商品またはセットのid
	 */
	public void deleteOrderItem(Integer id,Integer subTotal,Boolean setOrder) {
		String searchWord;
		if(setOrder){
			searchWord="set_id";
		}else {
			searchWord="id";
		}
		String sql="delete from order_items where "+searchWord+"=:id returning order_id;";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", id).addValue("subTotal", subTotal);		
		int orderId=template.queryForObject(sql, param,Integer.class);
		String sql2="update orders set total_price= (total_price-:subTotal) where id=:orderId";
		SqlParameterSource param2=new MapSqlParameterSource().addValue("orderId", orderId).addValue("subTotal", subTotal);		
		template.update(sql2, param2);
	}
}
