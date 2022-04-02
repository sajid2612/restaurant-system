package main.java.restaurant.mapper;

import main.java.restaurant.model.ProcessOrder;

public class ProcessOrderMapper {
	public static ProcessOrder buildProcessOrderForReceive(String[] s) {
		ProcessOrder processOrder = new ProcessOrder();
		processOrder.setRequestType(0);
		processOrder.setTableId(CommonMapper.getIntValueOf(s[2]));
		processOrder.setMenuId(CommonMapper.getIntValueOf(s[3]));
		return processOrder;
	}

	public static ProcessOrder buildProcessOrderForComplete(String[] s) {
		ProcessOrder processOrder = new ProcessOrder();
		processOrder.setRequestType(1);
		processOrder.setMenuId(CommonMapper.getIntValueOf(s[1]));
		return processOrder;
	}

	public static ProcessOrder buildProcessOrderForReady(String[] checkoutAttribs) {
		ProcessOrder processOrder = new ProcessOrder();
		processOrder.setRequestType(2);
		processOrder.setTableId(CommonMapper.getIntValueOf(checkoutAttribs[1]));
		processOrder.setMenuId(CommonMapper.getIntValueOf(checkoutAttribs[2]));
		return processOrder;
	}

	public static ProcessOrder buildProcessOrderForCheckout(String[] s) {
		ProcessOrder processOrder = new ProcessOrder();
		processOrder.setRequestType(3);
		processOrder.setTableId(CommonMapper.getIntValueOf(s[1]));
		return processOrder;
	}


}
