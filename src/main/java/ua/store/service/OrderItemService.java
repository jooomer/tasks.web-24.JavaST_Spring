package ua.store.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.store.domain.Order;
import ua.store.domain.OrderItem;
import ua.store.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public Set<OrderItem> findAllByOrder(Order order) {
		return orderItemRepository.findAllByOrder(order);
	}

	public void update(Set<OrderItem> orderItems) {
		orderItemRepository.save(orderItems);
	}

}
