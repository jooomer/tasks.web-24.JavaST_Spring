/**
 * 
 */
package ua.store.model.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class UserCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "create_date")
	private Date createDate;
	
	private double amount;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToMany
	@JoinTable
	private Set<Product> products = new LinkedHashSet<>();
	
//	public void addProduct(Product product, Integer quantity) {
//		if (productMap.containsKey(product)) { 
//			Integer q = productMap.get(product);
//			q += quantity;
//			productMap.put(product, quantity);
//		} else {
//			productMap.put(product, quantity);
//		}
//		amount += product.getPrice() * quantity;
//	}
	
//	public boolean deleteProduct(Product product, Integer quantity) {
//		if (productMap.containsKey(product)) { 
//			Integer q = productMap.get(product);
//			if (q >= quantity) {
//				q -= quantity;
//				productMap.put(product, q);
//				amount += product.getPrice() * quantity;
//				return true;
//			} 
//		}
//		return false;
//	}
	
//	public void clearCart() {
//		productMap.clear();
//		amount = 0;
//	}
//	
//	public int size() {
//		return productMap.size();
//	}
//
//	/**
//	 * @return the productList
//	 */
//	public Map<Product, Integer> getProductMap() {
//		return productMap;
//	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


}
