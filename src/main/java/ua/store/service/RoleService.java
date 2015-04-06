package ua.store.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.store.model.entity.Role;
import ua.store.model.entity.RoleType;
import ua.store.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role findByName(RoleType name) {
		return roleRepository.findByName(name);
	}

	public void save(Role role) {
		roleRepository.save(role);
	}
	
	
	
}