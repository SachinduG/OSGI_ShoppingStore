package com.mtit.service;
import java.util.List;
import com.mtit.service.CashierServicePublish;
import com.mtit.service.databade.Database;
import com.mtit.service.model.Item ;

public class CashierServiceImpl implements CashierServicePublish {

//	@Override
//	public String publishPaymentsService() {
//		// TODO Auto-generated method stub
//		return "Execute the publish service of ServicePublisher";
//	}
	
	//private List<Item> itemList = Database.itemsList;//Item list details in the supermarket 
	//private double bill;//to store the bill value
	//private String[][] billdetails = new String[1000][4]; //To store the purchased item's details ,Assumption:only 1000 different items are allowed for an order
	//private int count = -1; //to store the item count [starts from 0]

	@Override
	public List<Item> displayItems() {
		
		Item item1 = new Item(1, "Item1", 200.00, 2, 20.00);
		Database.itemsList.add(item1);
		Item item2 = new Item(2, "Item2", 500.00, 5, 10.00);
		Database.itemsList.add(item2);
		
		return Database.itemsList;
		
	}

	

}
