package ua.store.domain.model.dto;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ua.store.domain.annotation.CategoryRequired;
import ua.store.domain.model.entity.Product;
import ua.store.domain.model.entity.Category;

public class ProductDto {
	
	@Size(min = 3, max = 50, message = "The product name should be at least 3 and no more 50 characters!")
	private String name;
	
	@Size(max = 2000, message = "The description should be at least 3 and no more 2000 characters!")
	private String description;

	@NotNull(message = "Price is required.")
	@Digits(fraction = 2, integer = 10, message = "The price should look like '1234' or '1234.56'")
	@DecimalMin(value = "0.00", message = "The price can not be less than 0.00")
	private Double price;
	
	@NotNull(message = "Quantity in stock is required.")
	@Digits(fraction = 0, integer = 10, message = "The quantity in stock should look like '1234'")
	@Min(value = 0, message = "The quantity in stock can not be less than 0")
	private Integer quantityInStock;
	
	private Date publishedDate;
	
	@CategoryRequired(message = "Category is required.")
	private Category category;
	
	public Product getAllFields(Product product) {
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setQuantityInStock(quantityInStock);
		product.setPublishedDate(publishedDate);
		product.setCategory(category);
		return product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	


}
