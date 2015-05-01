package ua.store.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.store.domain.Order;
import ua.store.domain.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	Set<OrderItem> findAllByOrder(Order order);
//	List<OrderItem> findAllByOrder(Order order);
	
}
