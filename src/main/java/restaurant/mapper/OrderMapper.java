package main.java.restaurant.mapper;


import main.java.restaurant.model.OrderInfo;

public class OrderMapper {
	public static OrderInfo buildOrder(String[] s) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setTableId(CommonMapper.getIntValueOf(s[1]));
		orderInfo.setMenuId(CommonMapper.getIntValueOf(s[2]));
		orderInfo.setOrderedQuantity(CommonMapper.getIntValueOf(s[3]));
		return orderInfo;
	}

	public static OrderInfo buildOrder(String tableId, String menuId) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setTableId(CommonMapper.getIntValueOf(tableId));
		orderInfo.setMenuId(CommonMapper.getIntValueOf(menuId));
		return orderInfo;
	}
}
