package com.mtit.service;
import com.mtit.service.model.Item;
import java.util.List;


//public String publishPaymentsService();

//Service methods which will provide to the cashier consumers
public interface CashierServicePublish {
	public List<Item> displayItems();//return the item list with item details
	//public int generateBill(int id,int qty);//calculates the on going bill continuously
	//public double displayFinalBillAmount();//displays the final bill amount
	//public String[][] dispalybillDetails();//returns the purchased item list wit details
	
}