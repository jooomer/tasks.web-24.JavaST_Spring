package ua.store.service.view;

import org.springframework.data.domain.Sort.Direction;

public class CatalogSelectFormHandler {

	public static final int INIT_ITEMS_ON_PAGE = 10;
	public static final Direction INIT_SORT_DIRECTION = Direction.ASC;
	public static final String INIT_SORT_FIELD = "id";

	private int itemsOnPage = INIT_ITEMS_ON_PAGE;
	private Direction sortDirection = INIT_SORT_DIRECTION;
	private String sortField = INIT_SORT_FIELD;
	
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
	public Direction getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(Direction sortDirection) {
		this.sortDirection = sortDirection;
	}


	
}
