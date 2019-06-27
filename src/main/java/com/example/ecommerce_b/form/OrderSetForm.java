package com.example.ecommerce_b.form;

import java.util.List;

/**
 * 注文セットを追加するフォーム.
 * 
 * @author ayane.tanaka
 *
 */
public class OrderSetForm {

	/** 商品id */
	private Integer setId;
	/** 数量 */
	private Integer quantity;

	/** ピザ1枚目のid */
	private Integer itemId1;
	/** ピザ2枚目のid (パーティのみ)*/
	private Integer itemId2;
	/** ピザ3枚目のid (パーティのみ)*/
	private Integer itemId3;
	
	/** ピザ1枚目のトッピングidリスト */
	private List<Integer> toppingIdList1;
	/** ピザ1枚目のトッピングidリスト(パーティのみ) */
	private List<Integer> toppingIdList2;
	/** ピザ1枚目のトッピングidリスト(パーティのみ) */
	private List<Integer> toppingIdList3;
	
	/** サイドメニューのidリスト */
	private List<Integer> sideMenuIdList;
	/** ドリンクのidリスト */
	private List<Integer> drinkIdList;
	
	
	
	
	
	@Override
	public String toString() {
		return "OrderSetForm [setId=" + setId + ", quantity=" + quantity + ", itemId1=" + itemId1 + ", itemId2="
				+ itemId2 + ", itemId3=" + itemId3 + ", toppingIdList1=" + toppingIdList1 + ", toppingIdList2="
				+ toppingIdList2 + ", toppingIdList3=" + toppingIdList3 + ", sideMenuId=" + sideMenuIdList + ", drinkId="
				+ drinkIdList + "]";
	}
	public Integer getSetId() {
		return setId;
	}
	public void setSetId(Integer setId) {
		this.setId = setId;
	}
	public Integer getItemId1() {
		return itemId1;
	}
	public void setItemId1(Integer itemId1) {
		this.itemId1 = itemId1;
	}
	public Integer getItemId2() {
		return itemId2;
	}
	public void setItemId2(Integer itemId2) {
		this.itemId2 = itemId2;
	}
	public Integer getItemId3() {
		return itemId3;
	}
	public void setItemId3(Integer itemId3) {
		this.itemId3 = itemId3;
	}
	public List<Integer> getToppingIdList1() {
		return toppingIdList1;
	}
	public void setToppingIdList1(List<Integer> toppingIdList1) {
		this.toppingIdList1 = toppingIdList1;
	}
	public List<Integer> getToppingIdList2() {
		return toppingIdList2;
	}
	public void setToppingIdList2(List<Integer> toppingIdList2) {
		this.toppingIdList2 = toppingIdList2;
	}
	public List<Integer> getToppingIdList3() {
		return toppingIdList3;
	}
	public void setToppingIdList3(List<Integer> toppingIdList3) {
		this.toppingIdList3 = toppingIdList3;
	}
	public List<Integer> getSideMenuIdList() {
		return sideMenuIdList;
	}
	public void setSideMenuIdList(List<Integer> sideMenuId) {
		this.sideMenuIdList = sideMenuId;
	}
	public List<Integer> getDrinkIdList() {
		return drinkIdList;
	}
	public void setDrinkIdList(List<Integer> drinkId) {
		this.drinkIdList = drinkId;
	}
	//get&set
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
