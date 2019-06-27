package com.example.ecommerce_b.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentForm {
	@NotBlank(message = "コメントを入力してください")
	@Size(min=1,max=250,message="コメントは250文字以内で入力してください")
	private String comment;
	@NotBlank
	private String errorPage;

	@Override
	public String toString() {
		return "CommentForm [comment=" + comment + ", errorPage=" + errorPage + "]";
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
}
