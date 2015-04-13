package ua.store.model.entity;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import ua.store.service.ProductCategoryService;

@Entity
@Table(name = "products")
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
	@JoinColumn(name = "product_category_id")
	@Enumerated(EnumType.STRING)
	private ProductCategory productCategory;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<OrderItem> orderItems = new LinkedHashSet<>();
	
	@Override
	public String toString() {
		return "Product --------------------- \n"
				+ "id:              " + id + "\n"
				+ "name:            " + name + "\n"
				+ "price:           " + price + "\n"
				+ "quantityInStock: " + quantityInStock + "\n"
				+ "publishedDate:   " + publishedDate + "\n"
				+ "productCategory: " + productCategory.getName() + "\n"
				+ "description:     " + description + "\n";
//				+ "orderItems size: " + orderItems.size() + "\n";
	}
	
	@Override
	public int compareTo(Product product) {
		return this.id - product.getId();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!obj.getClass().equals(this.getClass())) return false;
		if (!((Product) obj).getId().equals(this.id)) return false;
		return true;
	}
	
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

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
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


	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}




}
