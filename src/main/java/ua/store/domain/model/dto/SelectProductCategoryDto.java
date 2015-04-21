package ua.store.domain.model.dto;

import ua.store.domain.model.entity.ProductCategory;

public class SelectProductCategoryDto {

	private ProductCategory productCategory;

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
}
