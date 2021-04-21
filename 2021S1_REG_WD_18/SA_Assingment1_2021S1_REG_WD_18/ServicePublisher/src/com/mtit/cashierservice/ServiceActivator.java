package com.mtit.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.mtit.customerservice.CustomerServiceImpl;
import com.mtit.customerservice.CustomerServicePublish;
import com.mtit.deliveryservice.DeliveryServiceImpl;
import com.mtit.deliveryservice.DeliveryServicePublish;
import com.mtit.manageservice.ManageServiceImpl;
import com.mtit.manageservice.ManageServicePublish;




public class ServiceActivator implements BundleActivator {
//
//	private static BundleContext context;
//
//	static BundleContext getContext() {
//		return context;
//	}

	ServiceRegistration publishPaymentsServiceRegistration;
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		//ServiceActivator.context = bundleContext;
		System.out.println("Publisher Start");
		
		ManageServicePublish managerSer = new ManageServiceImpl();
		publishPaymentsServiceRegistration = context.registerService(ManageServicePublish.class.getName(), managerSer, null); //registering the managerService
		
		CashierServicePublish paymentPublisherService = new CashierServiceImpl();
		publishPaymentsServiceRegistration = context.registerService(CashierServicePublish.class.getName(), paymentPublisherService, null);
		
		CustomerServicePublish produceService = new CustomerServiceImpl();
		publishPaymentsServiceRegistration = context.registerService(CustomerServicePublish.class.getName(), produceService, null);
		
		DeliveryServicePublish delproduceService = new DeliveryServiceImpl();
		publishPaymentsServiceRegistration = context.registerService(DeliveryServicePublish.class.getName(), delproduceService, null);
	}
		

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		//ServiceActivator.context = null;
		System.out.println("Publisher Stop");
		publishPaymentsServiceRegistration.unregister();
	}

}
