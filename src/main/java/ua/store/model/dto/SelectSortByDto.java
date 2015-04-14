package ua.store.model.dto;

public class SelectSortByDto {
	
	private String sortDirection;
	private int itemsOnPage;
	private String sortField;
	
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
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
}
