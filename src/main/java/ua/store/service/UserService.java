package ua.store.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.store.model.entity.Product;
import ua.store.model.entity.Role;
import ua.store.model.entity.User;
import ua.store.model.entity.Order;
import ua.store.repository.OrderRepository;
import ua.store.repository.ProductRepository;
import ua.store.repository.RoleRepository;
import ua.store.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public User findOneWithOrders(int id) {
		User user = findOne(id);
		Set<Order> orders = orderRepository.findByUser(user, new PageRequest(0, 10, Direction.ASC, "date"));
		user.setOrders(orders);
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

	public User findOneWithCarts(String name) {
		User user = userRepository.findByName(name);
		return findOneWithOrders(user.getId());
	}

	public void update(User user) {
		userRepository.save(user);
		
	}

	public User findByName(String name) {
		return userRepository.findByName(name);
	}


}
