package ua.store.repository;

import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.domain.Order;
import ua.store.domain.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Set<Order> findAllByUser(User user, PageRequest pageRequest);

	Set<Order> findAllByUser(User user);

}
