package ua.store.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.store.model.entity.Order;
import ua.store.model.entity.OrderStatus;
import ua.store.model.entity.Product;
import ua.store.model.entity.ProductCategory;
import ua.store.model.entity.Role;
import ua.store.model.entity.RoleType;
import ua.store.model.entity.User;

@Transactional
@Service
public class InitDbService {
	
	private static final Logger logger = LogManager.getLogger(InitDbService.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;

	@PostConstruct
	public void init() {
		
		logger.debug("--- init() started.");
		
		if (roleService.findByName(RoleType.ROLE_ADMIN) != null) {
			return;
		}
		
		doInit(); 
	
		logger.debug("--- init() successfully finished.");
	}

	public void doInit() {
		
//		roleRepository.deleteAll();
//		userRepository.deleteAll();
//		productRepository.deleteAll();
//		productTypeRepository.deleteAll();
		
		// init roles
		logger.debug("--- init roles started.");
		Role roleUser = new Role();
		roleUser.setName(RoleType.ROLE_USER);
		roleService.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName(RoleType.ROLE_ADMIN);
		roleService.save(roleAdmin);

		// init first user "admin"
		logger.debug("--- init first user 'admin' started.");
		User userAdmin = new User();
		userAdmin.setName("admin");
		userAdmin.setEmail("freddy@krugger.com");
		userAdmin.setFirstName("Freddy");
		userAdmin.setLastName("Krugger");
		userAdmin.setPhone("(103)501-23-92");
		userAdmin.setAddress("45, First str, New York, USA");
		userAdmin.setComments("This user is an administrator");
		userAdmin.setInBlackList(false);
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		userAdmin.setPassword(encoder.encode("admin"));
		userAdmin.setPassword("admin");
		userAdmin.setEnabled(true);
		userAdmin.addRole(roleUser);
		userAdmin.addRole(roleAdmin);
		userService.save(userAdmin);

		// init second user "user"
		logger.debug("--- init first user 'user' started.");
		User userUser = new User();
		userUser.setName("user");
		userUser.setEmail("mike@tyson.com");
		userUser.setFirstName("Mike");
		userUser.setLastName("Tyson");
		userUser.setPhone("(103)694-03-85");
		userUser.setAddress("196, Second str, San Francisco, USA");
		userUser.setComments("This user is a bad boy");
		userUser.setInBlackList(true);
//		encoder = new BCryptPasswordEncoder();
//		userUser.setPassword(encoder.encode("user"));
		userUser.setPassword("user");
		userUser.setEnabled(true);
		userUser.addRole(roleUser);
		userService.save(userUser);

		// init product types
		logger.debug("--- init product types started.");
		ProductCategory productCategoryCabinets = new ProductCategory();
		productCategoryCabinets.setName("Cabinets");
		productCategoryService.save(productCategoryCabinets);

		ProductCategory productCategorySofas = new ProductCategory();
		productCategorySofas.setName("Sofas");
		productCategoryService.save(productCategorySofas);

		ProductCategory productCategoryArmchairs = new ProductCategory();
		productCategoryArmchairs.setName("Armchairs");
		productCategoryService.save(productCategoryArmchairs);

		ProductCategory productCategoryTables = new ProductCategory();
		productCategoryTables.setName("Tables");
		productCategoryService.save(productCategoryTables);

		// init products
		logger.debug("--- init products started.");
//		Product product1 = new Product();
//		product1.setName("Cabinet");
//		product1.setDescription("This cabinet has a modern design.");
//		product1.setProductCategory(productCategoryCabinets);
//		product1.setPrice(600.);
//		product1.setPublishedDate(new Date());
//		product1.setQuantityInStock(14);
//		productService.save(product1);
//
//		Product product2 = new Product();
//		product2.setName("Sofa");
//		product2.setDescription("This sofa is very soft.");
//		product2.setProductCategory(productCategorySofas);
//		product2.setPrice(500.);
//		product2.setPublishedDate(new Date());
//		product2.setQuantityInStock(5);
//		productService.save(product2);
//
//		Product product3 = new Product();
//		product3.setName("Armchair");
//		product3.setDescription("This armchair has a good price.");
//		product3.setProductCategory(productCategoryArmchairs);
//		product3.setPrice(1000.);
//		product3.setPublishedDate(new Date());
//		product3.setQuantityInStock(29);
//		productService.save(product3);
//
//		Product product4 = new Product();
//		product4.setName("Tables");
//		product4.setDescription("This table has a circle surface.");
//		product4.setProductCategory(productCategoryTables);
//		product4.setPrice(3000.);
//		product4.setPublishedDate(new Date());
//		product4.setQuantityInStock(8);
//		productService.save(product4);
		
		initManyProducts();
		
		// init orders
		logger.debug("--- init orders started.");
//		Order order1 = new Order();
//		order1.setUser(userAdmin);
//		order1.addProduct(product1);
//		order1.addProduct(product2);
//		order1.addProduct(product3);
//		order1.setOrderStatus(OrderStatus.DELIVERED);
//		order1.setComments("I wanna get it before Friday, 13");
//		orderService.save(order1);
		
		initManyOrders();
		
		
	}

	private void initManyOrders() {
		logger.debug("--- started");
		
		String comments = "Completely synergize resource sucking relationships via premier niche markets. Professionally cultivate one-to-one customer service with robust ideas. ";

		int numberOfOrders = 100;
		for (int i = 0; i < numberOfOrders; i++) {
			Order order1 = new Order();
			order1.setUser(userService.findOne(1));
			order1.setName("Order A/" + i);
			order1.addProduct(productService.findOne(1));
			order1.addProduct(productService.findOne(3));
			order1.addProduct(productService.findOne(4));
			order1.addProduct(productService.findOne(6));
			order1.setOrderStatus(OrderStatus.DELIVERED);
			order1.setComments(comments);
			orderService.save(order1);

			Order order2 = new Order();
			order2.setUser(userService.findOne(2));
			order2.setName("Order B/" + i);
			order2.addProduct(productService.findOne(2));
			order2.addProduct(productService.findOne(5));
			order2.addProduct(productService.findOne(10));
			order2.setOrderStatus(OrderStatus.DELIVERED);
			order2.setComments(comments);
			orderService.save(order2);

			Order order3 = new Order();
			order3.setUser(userService.findOne(1));
			order3.setName("Order C/" + i);
			order3.addProduct(productService.findOne(3));
			order3.addProduct(productService.findOne(6));
			order3.addProduct(productService.findOne(11));
			order3.addProduct(productService.findOne(12));
			order3.addProduct(productService.findOne(15));
			order3.setOrderStatus(OrderStatus.CANCELED);
			order3.setComments(comments);
			orderService.save(order3);

			Order order4 = new Order();
			order4.setUser(userService.findOne(2));
			order4.setName("Order D/" + i);
			order4.addProduct(productService.findOne(2));
			order4.setOrderStatus(OrderStatus.PAID);
			order4.setComments(comments);
			orderService.save(order4);
		}
		
	}

	private void initManyProducts() {
		logger.debug("--- started");
		
		String description = "Interactively procrastinate high-payoff content without backward-compatible data. Quickly cultivate optimal processes and tactical architectures. Completely iterate covalent strategic theme areas via accurate e-markets.";
		
		int numberOfProducts = 55;
		List<Product> products = new ArrayList<>();
		
		// create list of products
		for (int i = 0; i < numberOfProducts; i++) {
			Product cabinet = new Product();
			cabinet.setName("Cabinet " + i);
			cabinet.setProductCategory(productCategoryService.findByName("Cabinets"));
			cabinet.setDescription(description);
			cabinet.setPrice(600.);
			cabinet.setPublishedDate(new Date(new GregorianCalendar(2014, 2, 25).getTimeInMillis()));
			cabinet.setQuantityInStock(14);
			products.add(cabinet);

			Product sofa = new Product();
			sofa.setName("Sofa " + i);
			sofa.setProductCategory(productCategoryService.findByName("Sofas"));
			sofa.setDescription(description);
			sofa.setPrice(400.);
			sofa.setPublishedDate(new Date(new GregorianCalendar(2013, 5, 22).getTimeInMillis()));
			sofa.setQuantityInStock(10);
			products.add(sofa);

			Product armchair = new Product();
			armchair.setName("Armchair " + i);
			armchair.setProductCategory(productCategoryService.findByName("Armchairs"));
			armchair.setDescription(description);
			armchair.setPrice(200.);
			armchair.setPublishedDate(new Date(new GregorianCalendar(2012, 8, 5).getTimeInMillis()));
			armchair.setQuantityInStock(20);
			products.add(armchair);

			Product table = new Product();
			table.setName("Table " + i);
			table.setProductCategory(productCategoryService.findByName("Tables"));
			table.setDescription(description);
			table.setPrice(300.);
			table.setPublishedDate(new Date(new GregorianCalendar(2011, 10, 15).getTimeInMillis()));
			table.setQuantityInStock(5);
			products.add(table);
		}
		
		// save products to DB
		productService.save(products);
	}

}
