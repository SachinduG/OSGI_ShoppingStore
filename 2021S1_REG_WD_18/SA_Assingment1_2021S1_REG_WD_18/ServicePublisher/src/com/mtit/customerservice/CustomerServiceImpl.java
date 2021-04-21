package com.mtit.customerservice;



public class CustomerServiceImpl implements CustomerServicePublish {

	@Override
	public void CalculateTotal(int item_type, int quantity) {
		// TODO Auto-generated method stub
		if(item_type == 1){
			System.out.println("\nTotal Amount For the Order On Hand Frees is Rs."+(800*quantity)+"\n");
		}
		else if(item_type == 2){
			System.out.println("\nTotal Amount For the Order On Back Covers is Rs."+(600*quantity)+"\n");
		}
		else if(item_type == 3){
			System.out.println("\nTotal Amount For the Order On Pen Drives is Rs."+(1200*quantity)+"\n");
		}
		else if(item_type == 4){
			System.out.println("\nTotal Amount For the Order On Power Banks is Rs."+(2000*quantity)+"\n");
		}
		else if(item_type == 5){
			System.out.println("\nTotal Amount For the Order On Mobile Chargers is Rs."+(1400*quantity)+"\n");
		}
		else{
			System.out.println("Incorrect Input!!! \n Try Again!!!");
		}
	}
}
