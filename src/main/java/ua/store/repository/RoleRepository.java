package ua.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(Role.Name name);

}
