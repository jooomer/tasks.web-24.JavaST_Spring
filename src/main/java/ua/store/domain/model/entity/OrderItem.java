package ua.store.domain.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double productPrice;
	
	private Integer productsQuantity;
	
	private Double amount;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass())) return false;
		if (!this.product.equals(((OrderItem) obj).getProduct())) return false;
		return true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductsQuantity() {
		return productsQuantity;
	}

	public void setProductsQuantity(Integer productsQuantity) {
		this.productsQuantity = productsQuantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}


//	@Override
//	public boolean equals(Object o) {
//		if (o == null) return false;
//		if (o instanceof OrderItem) return false;
//		if (this.product.getId() != ((OrderItem) o).getProduct().getId()) return false;
//		return true;
//	}
//
//	@Override
//	public int hashCode(){
//	    return this.product.getId().hashCode();
//	  }

}
