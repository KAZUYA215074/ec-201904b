package com.example.ecommerce_b.domain;

import java.util.Date;

public class Comment {
	private String comment;
	private Date errorDate;
	private String errorPage;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getErrorDate() {
		return errorDate;
	}

	public void setErrorDate(Date date) {
		this.errorDate = date;
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	@Override
	public String toString() {
		return "Comment [comment=" + comment + ", errorDate=" + errorDate + ", errorPage=" + errorPage + ", id=" + id
				+ "]";
	}

}
