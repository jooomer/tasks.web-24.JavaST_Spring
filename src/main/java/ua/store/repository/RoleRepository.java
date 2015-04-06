package ua.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.model.entity.Role;
import ua.store.model.entity.RoleType;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(RoleType name);

}
