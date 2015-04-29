package ua.store.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.domain.entity.Role;
import ua.store.domain.entity.RoleType;
import ua.store.domain.entity.User;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(RoleType name);

}
