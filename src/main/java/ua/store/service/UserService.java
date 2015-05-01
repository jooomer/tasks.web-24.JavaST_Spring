package ua.store.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.store.domain.Order;
import ua.store.domain.OrderItem;
import ua.store.domain.Role;
import ua.store.domain.User;
import ua.store.dto.UserRegisterDto;
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
	private RoleService roleService;
	
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
		Set<Order> orders = orderService.findAllByUser(user);
		user.setOrders(orders);
		return user;
	}

	public void save(User user) {
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

	public User createAndSaveNewUser(UserRegisterDto userRegisterDto) {
		User user = new User();
		user.setName(userRegisterDto.getName());
		user.setFirstName(userRegisterDto.getFirstName());
		user.setLastName(userRegisterDto.getLastName());
		user.setEmail(userRegisterDto.getEmail());
		user.setPhone(userRegisterDto.getPhone());
		user.setAddress(userRegisterDto.getAddress());
		user.setPassword(userRegisterDto.getPassword());
		Role role = roleService.findByName(Role.Name.ROLE_USER);
		user.addRole(role);
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return user;
	}

}
