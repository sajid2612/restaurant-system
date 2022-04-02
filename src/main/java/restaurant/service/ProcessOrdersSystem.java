package main.java.restaurant.service;

import java.util.LinkedList;
import java.util.List;
import main.java.restaurant.model.ProcessOrder;

public class ProcessOrdersSystem {

	public void processAcceptedOrders(List<ProcessOrder> accetedOrCompleteRequestForSubSystem2, int noOfMicrowaves) {
		List<Integer> menuBeingPrepared = new LinkedList<>();
		List<Integer> menuWaitList = new LinkedList<>();
		int i = 0; int j = 0;
		for (ProcessOrder processOrder : accetedOrCompleteRequestForSubSystem2) {
			if (processOrder.getRequestType() == 0) { //Order accepted information
				if (noOfMicrowaves > 0) {
					System.out.println(processOrder.getMenuId());
					menuBeingPrepared.add(processOrder.getMenuId());
					noOfMicrowaves--;
				} else {
					menuWaitList.add(processOrder.getMenuId());
					System.out.println("wait");
				}
			} else { //Complete information
				if (!menuBeingPrepared.isEmpty() && menuBeingPrepared.get(j).equals(processOrder.getMenuId())) {//valid request
					noOfMicrowaves++;
					//menuBeingPrepared.remove(0);
					if (!menuWaitList.isEmpty()) {
						System.out.println("ok "+menuWaitList.get(i));
						menuBeingPrepared.add(menuWaitList.get(i));
						i++;
						//menuWaitList.remove(0);
					} else {
						System.out.println("unexpected input");
					}
					j++;
				}
			}
		}
	}

}
