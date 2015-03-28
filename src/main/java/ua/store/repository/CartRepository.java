package ua.store.repository;

import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.model.entity.User;
import ua.store.model.entity.UserCart;

public interface CartRepository extends JpaRepository<UserCart, Long> {

	Set<UserCart> findByUser(User user, PageRequest pageRequest);

}
