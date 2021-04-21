package com.mtit.deliveryservice;

import java.util.Scanner;
import java.util.List;

import com.mtit.service.databade.Database;
import com.mtit.service.model.Delivery;
import com.mtit.service.model.Item;




public class DeliveryServiceImpl implements DeliveryServicePublish {
	boolean exit = false;
	Scanner input = new Scanner(System.in);
	@Override
	public void DeliveryPrice(int distance_type) {
		// TODO Auto-generated method stub
		if(distance_type == 1){
			System.out.println("\nDelivery is Free\n");
		}
		else if(distance_type == 2){
			System.out.println("\nTotal Amount For the Delivery is Rs 200\n");
		}
		else if(distance_type == 3){
			System.out.println("\nTotal Amount For the Delivery is Rs 400\n");
		}
		else if(distance_type == 4){
			System.out.println("\nTotal Amount For the Delivery is Rs 600\n");
		}
		
		else{
			System.out.println("Incorrect Input!!! \n Try Again!!!");
		}
	}
	@Override
	public synchronized int addDelivery(String deliveryAddress, String contact) {
		// TODO Auto-generated method stub
		Delivery newDelivery = new Delivery(Database.deliveryList.size() + 1, deliveryAddress, contact);
		Database.deliveryList.add(newDelivery);

	return 1;		
	}
	@Override
	public synchronized int updateDelivery(String updatedDeliveryAddress, String updatedContact) {
		// TODO Auto-generated method stub
		Delivery deliveryToBeUpdated = null;
		boolean invalid = true;
		int count = -1;
		for (Delivery tempDelivery : Database.deliveryList) {
			count++;
			if (tempDelivery.getDeliveryAddress().equalsIgnoreCase(updatedDeliveryAddress)) {

				deliveryToBeUpdated = tempDelivery;
				invalid = false;
				break;
			}
		
		}
		if (!invalid) {

			deliveryToBeUpdated.setDeliveryAddress(updatedDeliveryAddress);
			deliveryToBeUpdated.setContact(updatedContact);
			
			Database.deliveryList.set(count, deliveryToBeUpdated);
			return 1;

		} else {
			return -1;
		}
	}
				
		@Override
		public List<Delivery> listDelivery() {

			return Database.deliveryList;
		}
	}

