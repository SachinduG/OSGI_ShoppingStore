package com.mtit.service.model;
//Item model class
public class Item {
	private int itemId;
	private String itemName;
	private double itemPrice;
	private int itemQuantity;
	private double discount;
	private double finalPrice;
	//private double total;
	
	public Item(int itemId, String itemName, double itemPrice, int itemQuantity, double discount) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.discount = discount;
		calculateFinalPrice();
		
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
		calculateFinalPrice();
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
		
	}
	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
		calculateFinalPrice();
	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void calculateFinalPrice() {
		this.finalPrice = itemPrice * ((100.00- discount)/100.00) * itemQuantity ;
	}

	
}