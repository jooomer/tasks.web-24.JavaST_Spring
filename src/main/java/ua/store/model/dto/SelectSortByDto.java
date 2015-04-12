package ua.store.model.dto;

public class SelectSortByDto {
	
	private String sortDirection;
	private int itemsOnPage;
	
	public String getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	public int getItemsOnPage() {
		return itemsOnPage;
	}
	public void setItemsOnPage(int itemsOnPage) {
		this.itemsOnPage = itemsOnPage;
	}
	
}
