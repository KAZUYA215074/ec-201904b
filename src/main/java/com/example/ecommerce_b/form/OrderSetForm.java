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
	/** トッピングのidのリスト */
	private List<List<Integer>> orderToppingIdListList;
	
	
	@Override
	public String toString() {
		return "OrderSetForm [setId=" + setId + ", quantity=" + quantity  + ", orderToppingIdListList="
				+ orderToppingIdListList + "]";
	}
	
	//get&set
	public Integer getItemId() {
		return setId;
	}
	public void setItemId(Integer itemId) {
		this.setId = itemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public List<List<Integer>> getOrderToppingIdListList() {
		return orderToppingIdListList;
	}
	public void setOrderToppingIdList(List<List<Integer>> orderToppingIdListList) {
		this.orderToppingIdListList = orderToppingIdListList;
	}
	
	
}
