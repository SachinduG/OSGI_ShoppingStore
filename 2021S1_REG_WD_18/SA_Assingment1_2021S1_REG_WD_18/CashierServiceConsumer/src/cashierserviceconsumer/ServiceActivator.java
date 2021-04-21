package cashierserviceconsumer;
//package com.mtit.service;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.mtit.service.CashierServicePublish;
import com.mtit.service.model.Item;

public class ServiceActivator implements BundleActivator {



	ServiceReference cashierServiceReference;
	private boolean exit =false;
	Scanner input = new Scanner(System.in);
	
	@Override
	public void start(BundleContext context) throws Exception {    //Life cycle method-start
		//ServiceActivator.context = bundleContext;
		System.out.println("============Start Online Shopping Store Cashier Service Consumer============");
		cashierServiceReference = context.getServiceReference(CashierServicePublish.class.getName());
		CashierServicePublish cashierService = (CashierServicePublish)context.getService(cashierServiceReference);   //Instance of CashierService
		do {
			int selection = 4;
			do {
				System.out.println("----------------------------Welcome to Billing-------------------------------");
				
				//main menu for cashier service
				System.out.println("Please Select an option to continue.....");
				System.out.println("Options");
			    System.out.println("1.View Items List");
			    System.out.println("2.Generate Customer Bills and Display Receipt");
			    System.out.println("3.Exit");
			
			    System.out.println("Enter your selection..."); //get the selection as customer input
			    selection = input.nextInt();
			    input.nextLine();
			    
			    
			    String  backToHome = null;
				String calculateFinalBill = null;
				String lCustomer = null;
			    int itemCount = -1;
			    
			    if(selection == 3) {//Exits from the cashier consumer program
					 exit = true;
				}
			    
			    else if (selection == 1) {//Handles the viewing process of item list
			    	do {
			    		
						List<Item> itemsList =cashierService.displayItems();//Consumes the CashierService displayItems()
						
						System.out.println("-----------------------------------Items List--------------------------------------------");
						System.out.println("Item ID:"+"\t" +"Item Name:"+"\t" + "Item Price:"+"\t" + "Item Quantity:"+"\t" + "Discount Percentage:"+"\t");
						
						for(Item tempItem: itemsList ) {
							System.out.println(tempItem.getItemId()+"\t         "+tempItem.getItemName()+"\t         "+tempItem.getItemPrice()+"\t         "+tempItem.getItemQuantity()+"\t                  "+tempItem.getDiscount()+"\t"+"\n");
							
						}
									
								
					    System.out.println("-----------------------------------------------------------------------------------------");
					    
						System.out.println("Press 0 to navigate back to home or press any other key to continue....");
						backToHome=input.nextLine();
						// Clear the list
					    itemsList.clear();
					}while(!(backToHome.equals("0")));
			    }
			    else if(selection == 2) {//Handles the billing process
			    	do {
			    		List<Item> finalList = cashierService.displayItems();//Consumes the CashierService displayItems()
						  
						  System.out.println("---------------------Welcome to Customer's Billing ----------------------------------------------------------------" + "\n");
						  System.out.println("Item ID:"+"\t" +"Item Name:"+"\t" + "Item Price:"+"\t" + "Discount Percentage:"+"\t" + "Item Quantity:"+"\t" + "Item Final Price:"+"\t");
						  double total = 0;
						
						  for(Item tempItem: finalList ) {
								System.out.println(tempItem.getItemId()+"\t         "+tempItem.getItemName()+"\t         "+tempItem.getItemPrice()+"\t         "+tempItem.getDiscount()+"\t                  "+tempItem.getItemQuantity()+"\t         "+tempItem.getFinalPrice()+"\t"+"\n");
								total += tempItem.getFinalPrice(); //calculate total price

						  }
						  
						  System.out.println("\nTotal Price : " +"\t\t\t\t\t\t\t\t\t         " + total);  //print Total Price
						  System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
						  
						  System.out.println("Press Y to continue the billing and display the receipt...");
						  calculateFinalBill = input.nextLine();
						  finalList.clear();
					  } while(!(calculateFinalBill.equals("y")));
			    	
			    	do {  //Handle the print receipt process
						System.out.println("Are you a loyalty customer?(Yes/No) Press Y(Yes) or N(No)");  //check whether the customer is loyalty or not
						lCustomer = input.nextLine();
						
						if(!(lCustomer.equals("y")) && !(lCustomer.equals("n"))) {
							System.out.println("Please enter a valid response");
						}
						
						System.out.println("------------------------------------------Receipt--------------------------------------------------------------");
						List<Item> billingList = cashierService.displayItems();//Consumes the CashierService displayItems()
						
						System.out.println("Item ID:"+"\t" +"Item Name:"+"\t" + "Item Price:"+"\t" + "Discount Percentage:"+"\t" + "Item Quantity:"+"\t" + "Item Final Price:"+"\t");
						double billTotal = 0;
						double billDiscount = 0;
						for(Item tempItem: billingList ) {
							System.out.println(tempItem.getItemId()+"\t         "+tempItem.getItemName()+"\t         "+tempItem.getItemPrice()+"\t         "+tempItem.getDiscount()+"\t                  "+tempItem.getItemQuantity()+"\t         "+tempItem.getFinalPrice()+"\t"+"\n");
							billTotal += tempItem.getFinalPrice();
						}	
						
						System.out.println("\nSub Total : " +"\t\t\t\t\t\t\t\t\t         " + billTotal); //display total
						if((lCustomer.equals("y")) && billTotal >= 10000.00 ) {  //add billDiscount
							billDiscount = billTotal *10/100;
						}
						System.out.println("\nBill Discount : " +"\t\t\t\t\t\t\t\t         " + billDiscount);  //display billDiscount
						System.out.println("\nNet Total : " +"\t\t\t\t\t\t\t\t\t         " + (billTotal-billDiscount));  //display net total
						System.out.println("-----------------------------------------------------------------------------------------------------------------");
						System.out.println("Thank you! Come Again.\n");	
						
				   }while(!(lCustomer.equals("y")) && !(lCustomer.equals("n")));
			    	
			       System.out.println("Press 0 to navigate back to home or press any other key to continue....");
			       
			       backToHome=input.nextLine();
				
			    }
			 		    
			    
			    else if(selection !=1 && selection !=2 && selection !=3 ) {
					 System.out.println("Please enter a valid selection");
				}
			}while(selection !=1 && selection !=2 && selection !=3);

		}while(!exit);
		
	}
	
	@Override
	public void stop(BundleContext context) throws Exception { //Life cycle method -stop
		//ServiceActivator.context = null;
		System.out.println("Press 4 to Delivery Service");
		System.out.println("============Online Shopping Cashier Service Consumer Stopped.============");
		context.ungetService(cashierServiceReference);
	}
	


}
