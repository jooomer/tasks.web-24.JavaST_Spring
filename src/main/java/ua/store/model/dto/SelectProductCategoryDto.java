package ua.store.model.dto;

import ua.store.model.entity.ProductCategory;

public class SelectProductCategoryDto {

	private ProductCategory productCategory;

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
}
