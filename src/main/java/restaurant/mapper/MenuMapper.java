package main.java.restaurant.mapper;


import main.java.restaurant.model.Menu;

public class MenuMapper {
	public static Menu buildMenu(String[] s) {
		Menu menu = new Menu();
		menu.setMenuId(CommonMapper.getIntValueOf(s[0]));
		menu.setQuantity(CommonMapper.getIntValueOf(s[1]));
		menu.setPrice(CommonMapper.getIntValueOf(s[2]));
		return menu;
	}
}
