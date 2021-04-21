package com.mtit.deliveryservice;

import java.util.List;

import com.mtit.service.model.Delivery;
import com.mtit.service.model.Item;

public interface DeliveryServicePublish {
	public void DeliveryPrice(int distance_type);
	public  int  addDelivery(String deliveryAddress,String contact);//Adds the new items to the item list
	public   int  updateDelivery(String updatedDeliveryAddress,String updatedContact);
	public   List<Delivery> listDelivery();
}
