package deliveryserviceconsumer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.mtit.deliveryservice.DeliveryServicePublish;
import com.mtit.service.model.Delivery;
import com.mtit.service.model.Item;

public class ServiceActivator implements BundleActivator {

	ServiceReference serviceReference;
	Scanner input = new Scanner(System.in);
	
	boolean exit = false;
	
	int distance_type;
	int contact;
	int option;
	String addresss;
	String ans;
	

	public void start(BundleContext context) throws Exception {
		serviceReference = context.getServiceReference(DeliveryServicePublish.class.getName());
		DeliveryServicePublish deliveryServiceProduce = (DeliveryServicePublish) context.getService(serviceReference);
		
		System.out.println("======Shopping Store Deliver Consumer Started!=======");
		System.out.print("\nDo you want to deliver the purchased item? If Yes, Press Y/y ");
		Scanner scan = new Scanner(System.in);
		ans = scan.next();
		
		
			try{
				
				System.out.println("\n\n\n----- SHOPPING STORE DELIVERY SERVICE -----\n\n");
				
				System.out.println("Delivery is Free !!! for the deliveries within the Galle district");
				
				System.out.println("1. Deliveries within Galle District");
				System.out.println("2. Deliveries within 50 to 100 km range");
				System.out.println("3. Deliveries within 100 to 200 km range");
				System.out.println("4. Deliveries within 200 to 300 km range");
				
				
				System.out.print("\nEnter the Distance Type : ");
				int distance_type = scan.nextInt();
				
				/*System.out.print("\nEnter Delivery Address : ");
				String address = scan.toString();
				
				System.out.print("\nEnter a contact number : ");
				int contact = scan.nextInt();*/
				
				deliveryServiceProduce.DeliveryPrice(distance_type);
				
			}catch(InputMismatchException e){
				System.out.println("Error! Enter a valid input " + e.getMessage());
			
			}catch(Exception e){
				System.out.println("Error: "+ e.getMessage());
						
			}
			
			do {
				int selection = 4;
				do {
			System.out.println("Please Select an option to continue.....");
			System.out.println("Options");
			System.out.println("1.Add delivery details ");
			System.out.println("2.Update delivery details");
			System.out.println("3.View delivery details");
			System.out.println("4.Exit");
			
			System.out.println("Enter your option...");
			 selection = input.nextInt();
			 
			 input.nextLine();
			 if(selection == 3) {
				 exit = true;
			 }
			 
			 if(selection !=1 && selection !=2 && selection !=3) {
				 System.out.println("Please enter a valid option!!");
			 }
				}while(selection !=1 && selection !=2 && selection !=3);
				
				
				String  backToHome = null;
				if (selection == 1) {
					do {
					System.out.println("Delivery Address");
					String deliveryAddress = input.nextLine();
					

					System.out.println("Contact");
					String contact = input.nextLine();
					if (contact.matches("\\d{10}")) {
						System.out.println("valid contact number");
					}
					else {
						System.out.println("invalid format");
					}
					int result =deliveryServiceProduce.addDelivery(deliveryAddress, contact);
					 String msg = (result ==1) ? "Successfully added delivery!" :"please enter a valid address";
					   System.out.println(msg);
					System.out.println("Press 0 to navigate back to home or press any other key to continue....");
					
					backToHome=input.nextLine();
					
					}
					
					while(!(backToHome.equals("0")));
					
				}else if (selection == 2) {
					do {
				System.out.println("Delivery Address");
				String updatedDeliveryAddress = input.nextLine();

				System.out.println("Contact");
				String updatedContact = input.nextLine();

				int result =deliveryServiceProduce.updateDelivery(updatedDeliveryAddress,updatedContact);//Consumes the ManagerService updateItems()
				 String msg = (result ==1) ? "Successfully updated delivery details!" :"please enter a valid Address";
				 System.out.println(msg);
			     System.out.println("Press 0 to navigate back to home or press any other key to continue....");
						
					backToHome=input.nextLine();
						
					}while(!(backToHome.equals("0")));
						
					}
				
				else if(selection == 3) {
					do {
					List<Delivery> deliveryList =deliveryServiceProduce.listDelivery();
					System.out.println("-----------------------------------Your Delivery Details--------------------------------------------");
					System.out.println("Delivery ID:"+"\t" +"Delivery Address:"+"\t" + "Contact Number:"+"\t" );
						
					for(Delivery tempDelivery: deliveryList ) {
						System.out.println(tempDelivery.getDeliveryId()+"\t         "+tempDelivery.getDeliveryAddress()+"\t         "+tempDelivery.getContact()+"\t"+"\n");
										
					}
					System.out.println("-----------------------------------------------------------------------------------------");
		            System.out.println("Press 0 to navigate back to home or press any other key to continue....");
					
					backToHome=input.nextLine();
					
					}
					
					while(!(backToHome.equals("0")));

				}
				
				else if(selection == 4) {
					return;
				}
			}while(!exit);
			}
			
			
			

public void stop(BundleContext context) throws Exception {
	System.out.println("Good Bye!!!");
	context.ungetService(serviceReference);
}
	}


