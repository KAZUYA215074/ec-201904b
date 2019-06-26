package com.example.ecommerce_b.domain;

/**
 * セットメニューのドメイン.
 * 
 * @author ayane.tanaka
 *
 */
public class Set {
	/** id*/
	private Integer id;
	/** 名前*/
	private String name;
	/** 説明*/
	private String description;
	/** 価格*/
	private Integer price;
	/** 画像パス*/
	private String imagePath;
	/** 削除フラグ*/
	private Boolean deleted;
	
	/** 選択できるピザの価格*/
	private Integer pizzaLPrice;
	


	/** コンストラクタ */
	public Set() {
	}
	
	
	
	/** コンストラクタ */
	public Set(Integer id, String name, String description, Integer price, String imagePath, Boolean deleted,Integer pizzaLPrice) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imagePath = imagePath;
		this.deleted = deleted;
		this.pizzaLPrice=pizzaLPrice;
	}



	@Override
	public String toString() {
		return "Set [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", imagePath="
				+ imagePath + ", deleted=" + deleted + "]";
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public Integer getPizzaLPrice() {
		return pizzaLPrice;
	}



	public void setPizzaLPrice(Integer pizzaLPrice) {
		this.pizzaLPrice = pizzaLPrice;
	}

	
}
