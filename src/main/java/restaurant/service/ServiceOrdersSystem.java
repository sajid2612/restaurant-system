package main.java.restaurant.service;

import java.util.ArrayList;
import java.util.List;
import main.java.restaurant.model.ProcessOrder;

public class ServiceOrdersSystem {

	public void serviceProcessedOrders(List<ProcessOrder> acceptedInfo, List<ProcessOrder> completedInfo) {
		int i = 0;
		for (ProcessOrder ignored : completedInfo) {
			System.out.println("ready " +acceptedInfo.get(i).getTableId() +" "+acceptedInfo.get(i).getMenuId());
			i++;
		}
	}
}
