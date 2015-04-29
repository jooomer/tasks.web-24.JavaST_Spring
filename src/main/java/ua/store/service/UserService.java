package ua.store.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.store.domain.entity.Order;
import ua.store.domain.entity.OrderItem;
import ua.store.domain.entity.Product;
import ua.store.domain.entity.Role;
import ua.store.domain.entity.User;
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
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(long id) {
		return userRepository.findOne(id);
	}

	public User findOneWithOrders(long id) {
		User user = findOne(id);
		if (user == null) {
			return null;
		}
//		Set<Order> orders = orderRepository.findByUser(user, new PageRequest(0, 10, Direction.ASC, "date"));
		Set<Order> orders = orderRepository.findAllByUser(user);
		user.setOrders(orders);
		return user;
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
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

	public User findOneWithOrders(String name) {
		User user = userRepository.findByName(name);
		Set<Order> orders = orderService.findAllByUser(user);
		for (Order order : orders) {
			Set<OrderItem> orderItems = orderItemService.findAllByOrder(order);
//			List<OrderItem> orderItems = orderItemService.findAllByOrder(order);
			order.setOrderItems(orderItems);
		}
		user.setOrders(orders);
		return user;
	}

	public void update(List<User> users) {
		userRepository.save(users);
		
	}

	public void delete(Long id) {
		User user = userRepository.findOne(id);
		Set<Order> orders = user.getOrders();
		for (Order order : orders) {
			order.setUser(null);
		}
		orderService.update(orders);
		userRepository.delete(id);
	}

//	public Object findAllWithRoles() {
//		List<User> users = userRepository.findAll();
//		for (User user : users) {
//			Set<Role> roles = roleService.findAllByUser(user);
//			user.setRoles(roles);
//		}
//		return users;
//	}


}
