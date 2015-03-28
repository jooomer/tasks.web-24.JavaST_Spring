package ua.store.repository;

import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.model.entity.User;
import ua.store.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Set<Order> findByUser(User user, PageRequest pageRequest);

}
