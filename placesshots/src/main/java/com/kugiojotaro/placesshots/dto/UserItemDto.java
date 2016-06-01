package com.kugiojotaro.placesshots.dto;

import java.io.Serializable;

public class UserItemDto implements Serializable {
	
	private static final long serialVersionUID = 4709463601376556555L;
	
	//private String username;
	private String itemTitle;
	private Short quantity;
	
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public Short getQuantity() {
		return quantity;
	}
	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}
	
}