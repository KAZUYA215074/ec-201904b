package com.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.Set;

/**
 * setsを操作するリポジトリ.
 * 
 * @author momoyo kanie
 *
 */
@Repository
public class SetRepository {
	
	
	
	/**
	 * SetドメインのRowMapper.
	 */
	private  static final RowMapper<Set>  SET_ROW_MAPPER = (rs, i) -> {
		Set set = new Set();
		set.setId(rs.getInt("id"));
		set.setName(rs.getString("name"));
		set.setDescription(rs.getString("description"));
		set.setPrice(rs.getInt("price"));
		set.setImagePath(rs.getString("image_path"));
		set.setDeleted(rs.getBoolean("deleted"));
		set.setPizzaLPrice(rs.getInt("pizza_l_price"));
		
		return set;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	
	
	
	/**
	 * 全件検索を行う.<br>
	 * statusで渡された値で並べ替えを行う。
	 * 
	 * @param status 並べ替えを行うパラメータ
	 * @return 取得したセット情報一覧
	 */
	public List<Set> findAll(String status){
		String sql = "SELECT id,name,description , price , image_path , deleted, pizza_l_price"
				+ " FROM sets"
				+ " ORDER BY " + status;
		List<Set> setList = template.query(sql, SET_ROW_MAPPER);
		return setList;
	}
	
	/**
	 * 主キー検索を行う.
	 * 
	 * @param id 検索するID
	 * @return 取得した商品情報
	 */
	public Set load(int id) {
		String sql = "SELECT id,name,description , price , image_path , deleted, pizza_l_price" 
				+ " FROM sets"
				+ " WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Set set= template.queryForObject(sql, param, SET_ROW_MAPPER);

		return set;
	}
}
