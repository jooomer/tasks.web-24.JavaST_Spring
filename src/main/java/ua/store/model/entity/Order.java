/**
 * 
 */
package ua.store.model.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Date date;
	
	private double amount;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	private String comments;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//	private Set<OrderItem> orderItems = new LinkedHashSet<>();
	private List<OrderItem> orderItems = new LinkedList<>();
	
	public Order() {
		this.date = new Date(Calendar.getInstance().getTimeInMillis());
	}
	
	public void addProduct(Product product) {
		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(this);
		orderItem.setProduct(product);
		orderItem.setAmount(product.getPrice());
		orderItems.add(orderItem);
		this.amount += product.getPrice();
		
//		if (orderItems.contains(orderItem)) {
//			
//		} else {
//			orderItems.add(orderItem);
//			amount += product.getPrice();
//		}
		
	}
		
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}



}
