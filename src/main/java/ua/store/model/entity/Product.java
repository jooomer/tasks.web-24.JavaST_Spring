package ua.store.model.entity;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import ua.store.service.ProductTypeService;

@Entity
public class Product implements Comparable<Product> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;
	
	@Size(min = 3, message = "The product name must be at least 3 characters!")
	@Column(length = 100)
	private String name;
	
	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(length = Integer.MAX_VALUE)
	private String description;

	private Double price;
	
	@Column(name = "quantity_in_stock")
	private int quantityInStock;
	
	@Column(name = "published_date")
	private Date publishedDate;
	
	@ManyToOne
	@JoinColumn(name = "product_type_id")
	private ProductType productType;
	
	@ManyToMany(mappedBy = "products")
	private Set<UserCart> carts = new LinkedHashSet<>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
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

	@Override
	public int compareTo(Product product) {
		return this.id - product.getId();
	}

	public Set<UserCart> getCarts() {
		return carts;
	}

	public void setCarts(Set<UserCart> carts) {
		this.carts = carts;
	}




}
