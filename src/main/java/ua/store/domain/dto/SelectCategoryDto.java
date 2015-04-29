package ua.store.domain.dto;

import ua.store.domain.entity.Category;

public class SelectCategoryDto {

	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
