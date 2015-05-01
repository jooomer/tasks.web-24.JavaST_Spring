package ua.store.service;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.store.domain.Order;
import ua.store.domain.OrderItem;
import ua.store.domain.User;
import ua.store.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemService orderItemService;
	
	public void saveNewOrder(Order order) {
		order.setStatus(Order.Status.WAITING_FOR_PAIMENT);
		orderRepository.save(order);
		Calendar cal = Calendar.getInstance();
		cal.setTime(order.getDate());
		int year = cal.get(Calendar.YEAR);
		String number = "#" + year + "-" + order.getId();
		order.setNumber(number);
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
