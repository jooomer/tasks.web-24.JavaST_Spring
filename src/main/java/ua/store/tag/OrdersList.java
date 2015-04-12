/**
 *
 */
package ua.store.tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ua.store.controller.order.OrderController;
import ua.store.model.entity.Order;
import ua.store.model.entity.OrderItem;
import ua.store.model.entity.Product;
import ua.store.model.entity.User;
import ua.store.service.OrderService;
import ua.store.service.UserService;

@Transactional
@Service
public class OrdersList {

	private static final Logger logger = LogManager
			.getLogger(OrdersList.class);

	@Autowired
	private OrderService orderService;
	
	private List<Order> ordersList;
	private Iterator<Order> iterator;
	private Order order;
	private List<String> productNamesList;

	public OrdersList() {
//		logger.debug("OrderList() started");
//
//		// get list of all orders
//		this.ordersList = orderService.findAll();
//		this.iterator = this.ordersList.iterator();
	}

	public OrdersList(User user) {
		logger.debug("OrderList(User user) started");

		// get list of user's orders
//		Set<Order> orders = orderService.findAllByUser(user);
		Set<Order> orders = user.getOrders();
		this.ordersList = new ArrayList<>();
		for (Order order : orders) {
			this.ordersList.add(order);
		}

		this.iterator = ordersList.iterator();
	}

	public void resetIterator() {
		this.iterator = ordersList.iterator();
	}

	public int getSize() {
		return this.ordersList.size();
	}

	public String getOrderId() {
		if (iterator.hasNext()) {
			this.order = iterator.next();
			return String.valueOf(order.getId());
		} else {
			return null;
		}
	}

	public Double getAmount() {
		if (order != null) {
			return this.order.getAmount();
		} else {
			return null;
		}
	}

	public String getOrderDate() {
		if (order != null) {
			return this.order.getDate().toString();
		} else {
			return null;
		}
	}

	public String getOrderStatus() {
		if (order != null) {
			return this.order.getOrderStatus().toString();
		} else {
			return null;
		}
	}

	public String getComments() {
		if (order != null) {
			return this.order.getComments();
		} else {
			return null;
		}
	}

	/**
	 * @return the userList
	 */
	public List<Order> getOrdersList() {
		return ordersList;
	}

	public String toString() {
		String str = "";
		for (Order order : this.ordersList) {
			str += order.getId() + " ";
		}
		return str;
	}

	/**
	 * @return the productList
	 */
	public List<String> getProductNamesList() {
		List<String> list = new ArrayList<>();
		for (OrderItem orderItem : order.getOrderItems()) {
			Product product = orderItem.getProduct();
			list.add(product.getName());
		}
		return list;
	}

}
