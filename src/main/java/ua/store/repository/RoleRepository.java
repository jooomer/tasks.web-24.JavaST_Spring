package ua.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String string);

}
