package com.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.Topping;

/**
 * トッピングテーブル(toppings)を操作するリポジトリ.
 * 
 * @author momoyo kanie
 */
@Repository
public class ToppingRepository {

	/**
	 * ToppingドメインのRowMapper.
	 */
	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = (rs, i) -> {

		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));

		return topping;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 全件検索を行う.<br>
	 * 並び順を一定にするためにidで並べ替えを行う。
	 * 
	 * @return 取得したトッピング情報一覧
	 */
	public List<Topping> findAll() {
		String sql = "SELECT id,name,price_m,price_l" 
					+ " FROM toppings"
					+ " ORDER BY id;";
		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);

		return toppingList;
	}

}
