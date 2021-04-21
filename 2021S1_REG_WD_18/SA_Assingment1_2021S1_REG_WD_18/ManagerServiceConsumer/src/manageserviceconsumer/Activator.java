package manageserviceconsumer;

import com.mtit.manageservice.ManageServicePublish;
import com.mtit.service.model.Item;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	ServiceReference ManagerServiceReference;
	Scanner input = new Scanner(System.in);

	boolean exit = false;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("============Supermarket manager consumer started.============");
		ManagerServiceReference = context.getServiceReference(ManageServicePublish.class.getName());
		@SuppressWarnings("unchecked")
		ManageServicePublish managerService = (ManageServicePublish) context.getService(ManagerServiceReference);		//Instance of managerService
		
		do {
		int selection = 7;
		do {
		System.out.println("----------------------------Welcome to Items Management-------------------------------");
	
		System.out.println("Please Select an option to continue.....");
		System.out.println("Options");
		System.out.println("1.Add a new item  to the store ");
		System.out.println("2.Update an item in the store");
		System.out.println("3.Delete an item from the store");
		System.out.println("4.List All Items");
		System.out.println("5.Search Item by Name");
		System.out.println("6.Exit");
		
		System.out.println("Enter your option...");
		 selection = input.nextInt();
		
		 input.nextLine();
		 if(selection == 6) {
			 exit = true;
		 }
		 
		 if(selection !=1 && selection !=2 && selection !=3 && selection !=4 && selection !=5 && selection!=6) {
			 System.out.println("Please enter a valid option!!");
		 }
		}while(selection !=1 && selection !=2 && selection !=3 && selection !=4 && selection !=5 && selection!=6 );
		
	
        String  backToHome = null;
		if (selection == 1) {
			do {
			System.out.println("Item Name");
			String itemName = input.nextLine();

			System.out.println("Item Price");
			double itemPrice = input.nextDouble();

			System.out.println("Discount");
			double itemDiscount = input.nextDouble();
			input.nextLine();
			int result =managerService.addItems(itemName, itemPrice, itemDiscount);
			 String msg = (result ==1) ? "Successfully added the item!" :"please enter a valid name";
			   System.out.println(msg);
			System.out.println("Press 0 to navigate back to home or press any other key to continue....");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));

		}else if (selection == 2) {
			do {
				System.out.println("Item Name");
				String updatedItemName = input.nextLine();

				System.out.println("Item Price");
				double updatedItemPrice = input.nextDouble();

				System.out.println("Discount");
				double updatedItemDiscount = input.nextDouble();
				input.nextLine();
			
			int result =managerService.updateItems(updatedItemName,updatedItemPrice,updatedItemDiscount);//Consumes the ManagerService updateItems()
			  String msg = (result ==1) ? "Successfully updated the item!" :"please enter a valid name";
			   System.out.println(msg);
           System.out.println("Press 0 to navigate back to home or press any other key to continue....");
			
			backToHome=input.nextLine();
			
			}while(!(backToHome.equals("0")));
			
		}
		else if (selection == 3) {
			do {
			System.out.println("Enter the item name:");

			String itemName = input.nextLine();
			int result =managerService.removeItems(itemName);
			   String msg = (result ==1) ? "Successfully Removed the item!" :"please enter a valid name";
			   System.out.println(msg);
              System.out.println("Press 0 to navigate back to home or press any other key to continue....");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));

		}
		else if(selection == 4) {
			do {
				List<Item> itemsList =managerService.listItems();
				System.out.println("-----------------------------------Item list--------------------------------------------");
				System.out.println("Item ID:"+"\t" +"Item Name:"+"\t" + "Item Price:"+"\t" + "Discount Percentage:"+"\t" + "Item Final Price:"+"\t");
				
			for(Item tempItem: itemsList ) {
				System.out.println(tempItem.getItemId()+"\t         "+tempItem.getItemName()+"\t         "+tempItem.getItemPrice()+"\t         "+tempItem.getDiscount()+"\t                  "+tempItem.getFinalPrice()+"\t"+"\n");
								
			}
			System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("Press 0 to navigate back to home or press any other key to continue....");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));

		}
		else if(selection == 5) {
			do {
			
			System.out.println("Enter the item name");
			

			String itemName = input.nextLine();
			int result =managerService.searchitems(itemName);
			 String msg = (result ==1) ? "Item found!" :"Item not found!";
			   System.out.println(msg);
			
			System.out.println("Press 0 to navigate back to home or press any other key to continue....");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));
		}
		else if(selection == 6) {
			return;
		}
	}while(!exit);
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("============Supermarket manager consumer stopped.============");
		context.ungetService(ManagerServiceReference);
	}

}
