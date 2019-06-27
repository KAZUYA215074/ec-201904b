package com.example.ecommerce_b.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_b.domain.Comment;

@Repository
public class CommentRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
		
	private static final RowMapper<Comment> ORDERITEM_ROW_MAPPER=(rs,i) ->{
		Comment comment = new Comment();
		comment.setComment(rs.getString("comment"));
		comment.setErrorDate(rs.getDate("error_date"));
		comment.setErrorPage(rs.getString("error_page"));
		comment.setId(rs.getInt("id"));
		return comment;
	};
	
	public void insert(Comment comment) {
		SqlParameterSource param=new BeanPropertySqlParameterSource(comment);
		String sql = "insert into comments(error_page,error_date,comment)values(:errorPage,:errorDate,:comment)";
		template.update(sql, param);
	}
	public List<Comment> findAll(){
		String sql = "select id,error_date,error_page,comment from comments";
		List<Comment> commentList = template.query(sql, ORDERITEM_ROW_MAPPER);
		return commentList;
	}
}
