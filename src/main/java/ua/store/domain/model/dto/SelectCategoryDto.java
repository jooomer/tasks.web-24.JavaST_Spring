package ua.store.domain.model.dto;

import ua.store.domain.model.entity.Category;

public class SelectCategoryDto {

	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
