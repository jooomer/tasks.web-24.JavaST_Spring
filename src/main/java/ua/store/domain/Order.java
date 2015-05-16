/**
 * 
 */
package ua.store.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String number;
	
	private Date date;
	
	private Double amount;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	private String comments;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<OrderItem> orderItems = new LinkedHashSet<>();
	
	public static enum Status {
		WAITING_FOR_PAIMENT("Waiting for payment"), 
		PAID("Paid"), 
		DELIVERED("Delivered"), 
		CANCELED("Canceled");
		
		private String name;
		
		Status(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public static Status getStatus(String orderSatusString) {
			for (Status orderStatus : Status.values()) {
				if (orderStatus.toString().equals(orderSatusString)) {
					return orderStatus;
				}
			}
			throw new RuntimeException("Unknown type");
		}
	}

	
	public Order() {
		this.amount = 0.;
		this.date = new Date(Calendar.getInstance().getTimeInMillis());
	}
	
	public void addProduct(Product product, int quantity) {
		
		// check if this product already exists
		for (OrderItem orderItem : orderItems) {
			if (orderItem.getProduct().getId().equals(product.getId())) {
				int newQuantity = orderItem.getProductsQuantity() + quantity;
				orderItem.setProductsQuantity(newQuantity);
				double newItemOrderAmount = orderItem.getAmount() + product.getPrice() * quantity;
				orderItem.setAmount(newItemOrderAmount);
				amount += orderItem.getProductPrice() * quantity;
				return;
			} 
		}
		
		// do following if it's a new product
		OrderItem newOrderItem = new OrderItem();
		newOrderItem.setProduct(product);
		newOrderItem.setOrder(this);
		newOrderItem.setProductPrice(product.getPrice());
		newOrderItem.setProductsQuantity(quantity);
		newOrderItem.setAmount(product.getPrice() * quantity);
		amount += product.getPrice() * quantity;
		orderItems.add(newOrderItem);
	}
		
	/**
	 * @return the mapOfProducts
	 */
	public Map<Product, Integer> getMapOfProducts() {
		Map<Product, Integer> mapOfProducts = new LinkedHashMap<>();
		for (OrderItem orderItem : orderItems) {
			mapOfProducts.put(orderItem.getProduct(), orderItem.getProductsQuantity());
		}
		return mapOfProducts;
	}

	/**
	 * @return the listOfProducts
	 */
	public List<Product> getListOfProducts() {
		List<Product> listOfProducts = new ArrayList<>();
		for (OrderItem orderItem : orderItems) {
			listOfProducts.add(orderItem.getProduct());
		}
		return listOfProducts;
	}
	
	public void deleteProduct(Long id) {
		for (Iterator<OrderItem> i = orderItems.iterator(); i.hasNext();) {
			OrderItem orderItem = i.next();
			if (orderItem.getProduct().getId().equals(id)) {
				amount -= orderItem.getAmount();
				i.remove();
			}
		}
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAmount(Double amount) {
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String orderNumber) {
		this.number = orderNumber;
	}

}
