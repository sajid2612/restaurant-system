package main.java.restaurant.model;

public class OrderInfo {
	public Integer tableId;
	public Integer menuId;
	public Integer orderedQuantity;

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getMenuId() {
		return menuId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}
	public Integer getTableId() {
		return tableId;
	}
	public void setOrderedQuantity(Integer orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}
	public Integer getOrderedQuantity() {
		return orderedQuantity;
	}
}
