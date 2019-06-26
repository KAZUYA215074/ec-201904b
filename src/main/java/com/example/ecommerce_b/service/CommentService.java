package com.example.ecommerce_b.service;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_b.domain.Comment;
import com.example.ecommerce_b.form.CommentForm;
import com.example.ecommerce_b.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	public void insert(CommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setErrorDate(new Date());
		commentRepository.insert(comment);
	}
	
}
