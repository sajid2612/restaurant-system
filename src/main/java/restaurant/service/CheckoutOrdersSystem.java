package main.java.restaurant.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.restaurant.model.ProcessOrder;

public class CheckoutOrdersSystem {

	public void checkoutOrders(Map<Integer, Integer> menuPriceMap, List<ProcessOrder> readyAndCheckoutInfo) {
		Map<Integer, Integer> tableMenuMap = new HashMap<>();
		for (ProcessOrder processOrder : readyAndCheckoutInfo) {
			if (processOrder.getRequestType() == 2) { //Ready order
				tableMenuMap.put(processOrder.getTableId(), processOrder.getMenuId());
			} else { //Checkout Order
				if (tableMenuMap.containsKey(processOrder.getTableId())) {
					System.out.println(menuPriceMap.get(tableMenuMap.get(processOrder.getTableId())));
					tableMenuMap.remove(processOrder.getTableId());
				} else {
					System.out.println("please wait");
				}

			}
		}
	}
}
