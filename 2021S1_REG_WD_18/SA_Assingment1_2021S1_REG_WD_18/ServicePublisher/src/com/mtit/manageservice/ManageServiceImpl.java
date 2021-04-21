package com.mtit.manageservice;

import java.util.List;

import com.mtit.service.databade.Database;
import com.mtit.service.model.Item;

public class ManageServiceImpl implements ManageServicePublish {

	@Override
	public synchronized int  addItems(String itemName,double itemPrice,double itemDiscount) {

		Item newItem = new Item(Database.itemsList.size() + 1, itemName, itemPrice, 0, itemDiscount);
		Database.itemsList.add(newItem);

	return 1;		
	}

	@Override
	public  synchronized int updateItems(String updatedItemName,double updatedItemPrice,double updatedItemDiscount) {
		Item itemToBeUpdated = null;
		boolean invalid = true;
		int count = -1;
		for (Item tempItem : Database.itemsList) {
			count++;
			if (tempItem.getItemName().equalsIgnoreCase(updatedItemName)) {

				itemToBeUpdated = tempItem;
				invalid = false;
				break;
			}
		
		}
		if (!invalid) {

			itemToBeUpdated.setItemName(updatedItemName);
			itemToBeUpdated.setItemPrice(updatedItemPrice);
			itemToBeUpdated.setDiscount(updatedItemDiscount);
			itemToBeUpdated.calculateFinalPrice();

			Database.itemsList.set(count, itemToBeUpdated);
			return 1;

		} else {
			return -1;
		}
				
	}

	@Override
	public  synchronized int removeItems(String itemName) {

		boolean invalid = true;
		int count = -1;
		for (Item tempItem :Database.itemsList) {
			count++;
			if (tempItem.getItemName().equalsIgnoreCase(itemName)) {
			
				invalid = false;
				break;
			}
		}
		if (!invalid) {

			Database.itemsList.remove(count);
			return 1;

		} else {
			return -1;
		}
	}

	@Override
	public int searchitems(String itemName) {
		boolean valid = false;
	
		for (Item tempItem : Database.itemsList) {
		
			if (tempItem.getItemName().equalsIgnoreCase(itemName)) {

				valid = true;
				break;

			}			

		}
		if(valid) {
			return 1;
			
		}
		else {
			return -1;
		}
		
	}

	@Override
	public List<Item> listItems() {

		return Database.itemsList;
	}
}
