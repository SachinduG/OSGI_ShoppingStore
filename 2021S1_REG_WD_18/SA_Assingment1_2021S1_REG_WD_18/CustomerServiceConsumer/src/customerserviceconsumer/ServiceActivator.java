package customerserviceconsumer;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.mtit.customerservice.CustomerServicePublish;

public class ServiceActivator implements BundleActivator {

ServiceReference serviceReference;
	
	Scanner scan = new Scanner(System.in);
	
	int item_type;
	int quantity;
	String ans;
	
	public void start(BundleContext context) throws Exception {
		serviceReference = context.getServiceReference(CustomerServicePublish.class.getName());
		CustomerServicePublish customerServiceProduce = (CustomerServicePublish) context.getService(serviceReference);
		
		System.out.println("Shopping Store Customer Consumer Started!");
		System.out.print("\nDo you want to order a item? If Yes, Press Y/y ");
		Scanner scan = new Scanner(System.in);
		ans = scan.next();
		
		while(ans.equals("y")|| ans.equals("Y")){
			try{
				
				System.out.println("\n\n\n----- SHOPPING STORE SERVICE -----\n\n");
				
				System.out.println("1. Hand Free");
				System.out.println("2. Back Cover");
				System.out.println("3. Pen Drive");
				System.out.println("4. Power Bank");
				System.out.println("5. Mobile Charger");
				
				System.out.print("\nEnter the Item Type : ");
				int item_type = scan.nextInt();
				
				System.out.print("Enter the Quantity of Items : ");
				int quantity = scan.nextInt();
				
				customerServiceProduce.CalculateTotal(item_type, quantity);
			
			}catch(InputMismatchException e){
				System.out.println("Error! Enter an integer. " + e.getMessage());
			
			}catch(Exception e){
				System.out.println("Error: "+ e.getMessage());
			}
			
			System.out.print("\nDo you want to order again? If Yes, Press Y/y ");
			ans = scan.next();
			
		}
		System.out.println("Thank You!");
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!!!");
		context.ungetService(serviceReference);
	}
}
