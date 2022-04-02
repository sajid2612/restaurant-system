package main.java;

import static main.java.restaurant.constant.RestaurantConstants.CHECKOUT_SYSTEM;
import static main.java.restaurant.constant.RestaurantConstants.PROCESS_ORDER_SYSTEM;
import static main.java.restaurant.constant.RestaurantConstants.RECEIVE_ORDER_SYSTEM;
import static main.java.restaurant.constant.RestaurantConstants.SERVICE_ORDER_SYSTEM;
import static main.java.restaurant.constant.RestaurantConstants.SPLIT_CHAR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import main.java.restaurant.mapper.CommonMapper;
import main.java.restaurant.mapper.MenuMapper;
import main.java.restaurant.mapper.OrderMapper;
import main.java.restaurant.mapper.ProcessOrderMapper;
import main.java.restaurant.model.Menu;
import main.java.restaurant.model.OrderInfo;
import main.java.restaurant.model.ProcessOrder;
import main.java.restaurant.service.CheckoutOrdersSystem;
import main.java.restaurant.service.ProcessOrdersSystem;
import main.java.restaurant.service.ReceiveOrderSystem;
import main.java.restaurant.service.ServiceOrdersSystem;

public class RestaurantSystem {

	public static void main(String[] args) {
		String[] lines = getStdin();
		int subSystem = 0;
		int noOfMenuItems = 0;
		int noOfMicrowaves = 0;
		Map<Integer, Menu> menuMap = new HashMap<>();
		Map<Integer, Integer> menuPriceMap = new HashMap<>();
		List<OrderInfo> orderInfos = new ArrayList<>();
		List<ProcessOrder> acceptedAndCompleteRequestInfo = new ArrayList<>();
		List<ProcessOrder> acceptedInfo = new ArrayList<>();
		List<ProcessOrder> completedInfo = new ArrayList<>();
		List<ProcessOrder> readyAndCheckoutInfo = new ArrayList<>();
		for (int i = 0, l = lines.length; i < l; i++) {
			String output = String.format("line[%s]: %s", i, lines[i]);
			System.out.println(output);
			if (i == 0) {
				subSystem = Integer.parseInt(lines[i]);
			} else if (i == 1) {
				if (subSystem == PROCESS_ORDER_SYSTEM) { //Instruct Cooking and receives cooking status
					String[] input = lines[i].split(SPLIT_CHAR);
					noOfMenuItems = Integer.parseInt(input[0]);
					noOfMicrowaves = Integer.parseInt(input[1]);
				} else {
					noOfMenuItems = Integer.parseInt(lines[i]);
				}
			} else {
				if (subSystem == RECEIVE_ORDER_SYSTEM) { //Prepare Menu Items and Order Info
					populateRecieveOrderInfo(i, lines, noOfMenuItems, menuMap, orderInfos);
				} else if (subSystem == PROCESS_ORDER_SYSTEM) { // Instruct Kitchen Staff
					populateProcessOrderInfo(i, lines, noOfMenuItems, menuMap, acceptedAndCompleteRequestInfo);
				} else if (subSystem == SERVICE_ORDER_SYSTEM) { // Instruct Kitchen Staff
					populateServiceOrderInfo(i, lines, noOfMenuItems, menuMap, acceptedInfo, completedInfo);
				} else if (subSystem == CHECKOUT_SYSTEM) { // Checkout
					populateCheckoutInfo(i, lines, noOfMenuItems, menuPriceMap, readyAndCheckoutInfo);
				}
			}
		}

		if (subSystem == RECEIVE_ORDER_SYSTEM) {
			new ReceiveOrderSystem().getAcceptedOrders(menuMap, orderInfos);
		} else if (subSystem == PROCESS_ORDER_SYSTEM) {
			new ProcessOrdersSystem().processAcceptedOrders(acceptedAndCompleteRequestInfo, noOfMicrowaves);
		} else if (subSystem == SERVICE_ORDER_SYSTEM) {
			new ServiceOrdersSystem().serviceProcessedOrders(acceptedInfo, completedInfo);
		} else if (subSystem == CHECKOUT_SYSTEM) {
			new CheckoutOrdersSystem().checkoutOrders(menuPriceMap, readyAndCheckoutInfo);
		}
	}

	private static void populateRecieveOrderInfo(int i, String[] lines, int noOfMenuItems, Map<Integer, Menu> menuMap, List<OrderInfo> orderInfos) {
		if (i < noOfMenuItems + 2) {
			String[] menuAttributes = lines[i].split(SPLIT_CHAR);
			menuMap.put(CommonMapper.getIntValueOf(menuAttributes[0]), MenuMapper.buildMenu(menuAttributes));
		} else {
			String[] orderInfoAttributes = lines[i].split(SPLIT_CHAR);
			orderInfos.add(OrderMapper.buildOrder(orderInfoAttributes));
		}
	}

	private static void populateProcessOrderInfo(int lineNo, String[] lines, int noOfMenuItems, Map<Integer, Menu> menuMap, List<ProcessOrder> acceptedAndCompleteRequestInfo) {
		if (lineNo < noOfMenuItems + 2) { //After that it will be order info
			String[] menuAttributes = lines[lineNo].split(SPLIT_CHAR);
			menuMap.put(CommonMapper.getIntValueOf(menuAttributes[0]), MenuMapper.buildMenu(menuAttributes));
		} else {
			String[] orderProcessingAttribs = lines[lineNo].split(SPLIT_CHAR);
			if (orderProcessingAttribs[0].equals("received")) {
				acceptedAndCompleteRequestInfo.add(ProcessOrderMapper.buildProcessOrderForReceive(orderProcessingAttribs));
			} else { //Complete
				acceptedAndCompleteRequestInfo.add(ProcessOrderMapper.buildProcessOrderForComplete(orderProcessingAttribs));
			}

		}
	}

	private static void populateServiceOrderInfo(int lineNo, String[] lines, int noOfMenuItems, Map<Integer, Menu> menuMap, List<ProcessOrder> acceptedInfo, List<ProcessOrder> completedInfo) {
		if ( lineNo < noOfMenuItems + 2) { //After that it will be complete info
			String[] menuAttributes = lines[lineNo].split(SPLIT_CHAR);
			menuMap.put(CommonMapper.getIntValueOf(menuAttributes[0]), MenuMapper.buildMenu(menuAttributes));
		} else {
			String[] serviceOrderAttribs = lines[lineNo].split(SPLIT_CHAR);
			if (serviceOrderAttribs[0].equals("received")) {
				acceptedInfo.add(ProcessOrderMapper.buildProcessOrderForReceive(serviceOrderAttribs));
			} else { //Complete
				completedInfo.add(ProcessOrderMapper.buildProcessOrderForComplete(serviceOrderAttribs));
			}

		}
	}

	private static void populateCheckoutInfo(int lineNo, String[] lines, int noOfMenuItems, Map<Integer, Integer> menuPriceMap, List<ProcessOrder> readyAndCheckoutInfo) {
		if (lineNo < noOfMenuItems + 2) {
			String[] menuAttributes = lines[lineNo].split(SPLIT_CHAR);
			menuPriceMap.put(CommonMapper.getIntValueOf(menuAttributes[0]), CommonMapper.getIntValueOf(menuAttributes[2]));
		} else {
			String[] checkoutAttribs = lines[lineNo].split(SPLIT_CHAR);
			switch (checkoutAttribs[0]) {
				case "ready":  //Complete
					readyAndCheckoutInfo.add(ProcessOrderMapper.buildProcessOrderForReady(checkoutAttribs));
					break;
				case "check":  //Checkout
					readyAndCheckoutInfo.add(ProcessOrderMapper.buildProcessOrderForCheckout(checkoutAttribs));
					break;
			}
		}
	}


	private static String[] getStdin() {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> lines = new ArrayList<>();
		while (scanner.hasNext()) {
			lines.add(scanner.nextLine());
		}
		return lines.toArray(new String[lines.size()]);
	}
}
