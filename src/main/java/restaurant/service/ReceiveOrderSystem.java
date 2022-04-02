package main.java.restaurant.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import main.java.restaurant.model.Menu;
import main.java.restaurant.model.OrderInfo;

public class ReceiveOrderSystem {

	public LinkedHashMap<Integer, OrderInfo> getAcceptedOrders(Map<Integer, Menu> menuMap, List<OrderInfo> orderInfos) {
		LinkedHashMap<Integer, OrderInfo> acceptedOrderInfoMap = new LinkedHashMap<>();
		for (OrderInfo info : orderInfos) {
			if (menuMap.containsKey(info.getMenuId())) {
				Menu menuOrdered = menuMap.get(info.getMenuId());
				if (info.getOrderedQuantity() < menuOrdered.getQuantity()) {
					System.out.println("received order "+ info.getTableId() + " " + info.getMenuId());
					menuOrdered.setQuantity(menuOrdered.getQuantity() - info.getOrderedQuantity());
					acceptedOrderInfoMap.put(info.tableId, info);
				} else {
					System.out.println("sold out "+ info.getTableId() + " " + info.getMenuId());
				}
			} else {
				System.out.println("Wrong Menu, it does not exist");
			}
		}
		return acceptedOrderInfoMap;
	}
}
