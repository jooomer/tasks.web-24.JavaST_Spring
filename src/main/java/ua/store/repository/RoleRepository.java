package ua.store.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.model.entity.Role;
import ua.store.model.entity.RoleType;
import ua.store.model.entity.User;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(RoleType name);

}
