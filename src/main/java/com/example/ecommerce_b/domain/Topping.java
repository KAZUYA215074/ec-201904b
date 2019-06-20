package com.example.ecommerce_b.domain;

public class Topping {
	
	/** ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** Mの価格 */
	private String priceM;
	/** Lの価格 */
	private String priceL;

	@Override
	public String toString() {
		return "Topping [id=" + id + ", name=" + name + ", priceM=" + priceM + ", priceL=" + priceL + "]";
	}
	
	/** 引数なしのコンストラクタ */
	public Topping() {}
	
	/** コンストラクタ */
	public Topping(Integer id, String name, String priceM, String priceL) {
		super();
		this.id = id;
		this.name = name;
		this.priceM = priceM;
		this.priceL = priceL;
	}

     /** getter/setter */
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
	public String getPriceM() {
		return priceM;
	}
	public void setPriceM(String priceM) {
		this.priceM = priceM;
	}
	public String getPriceL() {
		return priceL;
	}
	public void setPriceL(String priceL) {
		this.priceL = priceL;
	}

}
