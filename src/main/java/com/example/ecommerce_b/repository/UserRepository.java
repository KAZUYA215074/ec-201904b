package com.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.User;

/**
 * ユーザドメインのリポジトリ.
 * 
 * @author knmrmst
 *
 */
@Repository
public class UserRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * ユーザドメインのRowMapper.
	 */
	private static final RowMapper<User> USER_ROW_MAPPER=(rs,i)->{
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setMailAddress(rs.getString("email"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		user.setZipcode(rs.getString("zipcode"));
		user.setBirthday(rs.getDate("birthday"));
		return user;
	};
	
	
	/**
	 * ユーザ情報をDBに登録する.
	 * 
	 * @param user ユーザ情報
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "insert into users(name, email, password, zipcode, address, telephone,birthday) values(:name,:mailAddress,:password,:zipcode,:address,:telephone,:birthday);";
		template.update(sql,param);
	}
	
	/**
	 * ユーザ情報をメールアドレスで検索する.
	 * 
	 * @param email メールアドレス
	 * @return　user ユーザ情報
	 */
	public User findByMailAddress(String email) {
		String sql = "select id,name, email, password, zipcode, address, telephone ,birthday from users where email=:email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
	
	/**
	 * ユーザIDがユーザ情報を呼び出す.
	 * 
	 * @param id ユーザid
	 * @return ユーザ情報
	 */
	public User findById(int id) {
		String sql = "select id,name, email, password, zipcode, address, telephone,birthday from users where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
	
}
