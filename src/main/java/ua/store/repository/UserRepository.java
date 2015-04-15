package ua.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.model.entity.Role;
import ua.store.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);

}
