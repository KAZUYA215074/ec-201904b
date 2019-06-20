package com.example.ecommerce_b.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.User;

@Repository
public class RegistUserRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "insert into users(name, email, password, zipcode, address, telephone) values(:name,:mailAddress,:password,:zipcode,:address,:telephone);";
		template.update(sql,param);
	}
	
}
