package com.mtit.service.model;

public class Delivery {
	private int deliveryId;
	private String deliveryAddress;
	private String contact;

	public Delivery(int deliveryId, String deliveryAddress, String contact) {
		super();
		this.deliveryId = deliveryId;
		this.deliveryAddress = deliveryAddress;
		this.contact = contact;
}
	public int getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
			}
}