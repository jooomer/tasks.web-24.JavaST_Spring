package ua.store.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.store.model.entity.Product;
import ua.store.model.entity.Role;
import ua.store.model.entity.User;
import ua.store.repository.ProductRepository;
import ua.store.repository.RoleRepository;
import ua.store.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public User findOneWithProducts(int id) {
		User user = findOne(id);
		List<Product> products = productRepository.findByUser(user, new PageRequest(0, 10, Direction.ASC, "publishedDate"));
		user.setProducts(products);
		return user;
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		Role role = roleRepository.findByName("ROLE_USER");
		user.addRole(role);
		
		userRepository.save(user);
	}

	public User findOneWithProducts(String name) {
		User user = userRepository.findByName(name);
		return findOneWithProducts(user.getId());
	}

	public void update(User user) {
		userRepository.save(user);
		
	}


}
