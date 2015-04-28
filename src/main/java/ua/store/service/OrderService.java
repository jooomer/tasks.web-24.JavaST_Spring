package ua.store.service;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.store.domain.model.entity.Order;
import ua.store.domain.model.entity.OrderItem;
import ua.store.domain.model.entity.Product;
import ua.store.domain.model.entity.User;
import ua.store.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemService orderItemService;
	
	public void saveNewOrder(Order order) {
		orderRepository.save(order);
		Calendar cal = Calendar.getInstance();
		cal.setTime(order.getDate());
		int year = cal.get(Calendar.YEAR);
		String orderNumber = "#" + year + "-" + order.getId();
		order.setOrderNumber(orderNumber);
		orderRepository.save(order);
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Set<Order> findAllByUser(User user) {
		return orderRepository.findAllByUser(user);
	}

	public Order findOneByIdWithProducts(long id) {
		Order order = orderRepository.findOne(id);
		Set<OrderItem> orderItems = orderItemService.findAllByOrder(order);
		order.setOrderItems(orderItems);
		return order;
	}

	public Order findOneById(Long id) {
		return orderRepository.findOne(id);
	}

	public void update(Order order) {
		orderRepository.save(order);
		
	}

	public void update(Set<Order> orders) {
		orderRepository.save(orders);
		
	}

}
