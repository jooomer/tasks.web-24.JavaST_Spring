package ua.store.domain.model.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import ua.store.domain.model.entity.Product;
import ua.store.domain.model.entity.ProductCategory;

public class AddProductDto {
	
	@Size(min = 3, max = 50, message = "The product name must be at least 3 and no more 50 characters!")
	private String name;
	
	@Size(max = 200, message = "The description must be at least 3 and no more 200 characters!")
	private String description;

//	@Size(min = 1, max = 200, message = "The price must be at least 3 and no more 200 characters!")
	@NotNull(message = "Price is required.")
	@Digits(fraction = 2, integer = 100, message = "The price must look like '1234' or '1234.56'")
	private Double price;
	
	private int quantityInStock;
	
	private Date publishedDate;
	
	private ProductCategory productCategory;
	
	public Product getAllFields(Product product) {
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setQuantityInStock(quantityInStock);
		product.setPublishedDate(publishedDate);
		product.setProductCategory(productCategory);
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

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	


}
