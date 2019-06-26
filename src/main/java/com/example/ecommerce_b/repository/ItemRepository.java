package com.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.Item;

/**
 * 商品テーブル（items）を操作するリポジトリ.
 * 
 * @author momoyo kanie
 */
@Repository
public class ItemRepository {

	/**
	 * ItemドメインのRowMapper.
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		item.setItemCategory(rs.getInt("item_category"));

		return item;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 全件検索を行う.<br>
	 * 
	 * 引数のパラメータで並び替えを行う。
	 * 
	 * @param status 並び替えをするパラメータ
	 * @return 取得した商品情報一覧
	 */
	public List<Item> findAll(String status) {
		String sql = "SELECT id,name,description , price_m , price_l , image_path , deleted"
				+ " FROM items"
				+ " ORDER BY " + status;
		List<Item> itemList = template.query(sql,  ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 主キー検索を行う.
	 * 
	 * @param id 検索するID
	 * @return 取得した商品情報
	 */
	public Item load(int id) {
		String sql = "SELECT id,name,description , price_m , price_l , image_path , deleted"
				+ " FROM items"
				+ " WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);

		return item;
	}

	/**
	 * 商品名の曖昧検索を行う.<br>
	 * statusのパラメータで並び替えを行う。
	 * 
	 * @param name   検索を行う文字列
	 * @param status 並び替えのパラメータ
	 * @return 取得した商品情報一覧
	 */
	public List<Item> findLikeName(String name, String status) {
		String sql = "SELECT id,name,description , price_m , price_l , image_path , deleted"
				+ " FROM items"
				+ " WHERE name ILIKE :name" + " ORDER BY " + status;
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);

		return itemList;
	}

}
