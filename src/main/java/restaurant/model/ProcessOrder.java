package main.java.restaurant.model;

public class ProcessOrder {
	public Integer requestType; //0 recieved, 1 complete
	public Integer menuId;
	public Integer tableId;

	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
	public Integer getRequestType() {
		return requestType;
	}

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

}
