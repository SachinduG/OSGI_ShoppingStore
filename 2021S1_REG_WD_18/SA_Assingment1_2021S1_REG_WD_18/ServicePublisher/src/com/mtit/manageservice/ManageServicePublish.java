package com.mtit.manageservice;

import java.util.List;

import com.mtit.service.model.Item;

public interface ManageServicePublish {

	public  int  addItems(String itemName,double itemPrice,double itemDiscount);
	public   int  updateItems(String updatedItemName,double updatedItemPrice,double updatedItemDiscount);
	public   int removeItems(String itemName);
	public   int searchitems(String itemName);
	public   List<Item> listItems();
}
