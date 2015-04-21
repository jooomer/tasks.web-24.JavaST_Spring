package ua.store.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.domain.model.entity.Role;
import ua.store.domain.model.entity.RoleType;
import ua.store.domain.model.entity.User;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(RoleType name);

}
