package ua.store.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.store.domain.Category;
import ua.store.domain.Order;
import ua.store.domain.Product;
import ua.store.domain.Role;
import ua.store.domain.User;

@Transactional
@Service
public class InitDbService {
	
	private static final Logger logger = LogManager.getLogger(InitDbService.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;

	@PostConstruct
	public void init() {
		
		logger.debug("--- init() started.");
		logger.debug("Tomcat path: " + System.getProperty("catalina.base"));
		
		if (roleService.findByName(Role.Name.ROLE_ADMIN) != null) {
			return;
		}
		
		doInit(); 
	
		logger.debug("--- init() successfully finished.");
	}

	public void doInit() {
		
		// init roles
		logger.debug("--- init roles started.");
		Role roleUser = new Role();
		roleUser.setName(Role.Name.ROLE_USER);
		roleService.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName(Role.Name.ROLE_ADMIN);
		roleService.save(roleAdmin);

		Role roleManager = new Role();
		roleManager.setName(Role.Name.ROLE_MANAGER);
		roleService.save(roleManager);

		// init first user "admin"
		logger.debug("--- init first user 'admin' started.");
		User userAdmin = new User();
		userAdmin.setName("admin");
		userAdmin.setEmail("freddie@mercury.com");
		userAdmin.setFirstName("Freddie");
		userAdmin.setLastName("Mercury");
		userAdmin.setPhone("(103)501-23-92");
		userAdmin.setAddress("Kensington, London, United Kingdom");
		userAdmin.setComments("Interactively procrastinate high-payoff ...");
		userAdmin.setInBlackList(false);
		userAdmin.setPassword("admin");
		userAdmin.setEnabled(true);
		userAdmin.addRole(roleUser);
		userAdmin.addRole(roleAdmin);
		userAdmin.setEnabled(true);
		userService.save(userAdmin);

		// init second user "user"
		logger.debug("--- init first user 'user' started.");
		User userUser = new User();
		userUser.setName("user");
		userUser.setEmail("michael@jackson.com");
		userUser.setFirstName("Michael");
		userUser.setLastName("Jackson");
		userUser.setPhone("(103)694-03-85");
		userUser.setAddress("Holmby Hills, Los Angeles, California, United ");
		userUser.setComments("Interactively procrastinate high-payoff ...");
		userUser.setInBlackList(true);
		userUser.setPassword("user");
		userUser.setEnabled(true);
		userUser.addRole(roleUser);
		userUser.setEnabled(true);
		userService.save(userUser);

		// init second user "manager"
		logger.debug("--- init first user 'user' started.");
		User userManager = new User();
		userManager.setName("manager");
		userManager.setEmail("paul@mccartney.com");
		userManager.setFirstName("Paul");
		userManager.setLastName("McCartney");
		userManager.setPhone("(103)694-03-85");
		userManager.setAddress("Walton, Liverpool, United Kingdom");
		userManager.setComments("Interactively procrastinate high-payoff ...");
		userManager.setInBlackList(true);
		userManager.setPassword("manager");
		userManager.setEnabled(true);
		userManager.addRole(roleUser);
		userManager.addRole(roleManager);
		userManager.setEnabled(true);
		userService.save(userManager);

		// init product types
		logger.debug("--- init product types started.");
		Category categoryCabinets = new Category();
		categoryCabinets.setName("Cabinets");
		categoryService.save(categoryCabinets);

		Category categorySofas = new Category();
		categorySofas.setName("Sofas");
		categoryService.save(categorySofas);

		Category categoryArmchairs = new Category();
		categoryArmchairs.setName("Armchairs");
		categoryService.save(categoryArmchairs);

		Category categoryTables = new Category();
		categoryTables.setName("Tables");
		categoryService.save(categoryTables);

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

		
		int numberOfOrders = 10;
		for (int i = 1; i < numberOfOrders + 1; i++) {
			Order order1 = new Order();
			order1.setUser(userService.findOne(1));
			order1.addProduct(productService.findOne(1), 2);
			order1.addProduct(productService.findOne(3), 1);
			order1.addProduct(productService.findOne(4), 3);
			order1.addProduct(productService.findOne(6), 5);
			order1.setStatus(Order.Status.DELIVERED);
			order1.setComments(comments);
			orderService.saveNewOrder(order1);
			

			Order order2 = new Order();
			order2.setUser(userService.findOne(2));
			order2.addProduct(productService.findOne(2), 1);
			order2.addProduct(productService.findOne(5), 4);
			order2.addProduct(productService.findOne(10), 6);
			order2.setStatus(Order.Status.WAITING_FOR_PAIMENT);
			order2.setComments(comments);
			orderService.saveNewOrder(order2);

			Order order3 = new Order();
			order3.setUser(userService.findOne(1));
			order3.addProduct(productService.findOne(3),2);
			order3.addProduct(productService.findOne(6), 5);
			order3.addProduct(productService.findOne(11), 2);
			order3.addProduct(productService.findOne(12), 3);
			order3.addProduct(productService.findOne(15), 10);
			order3.setStatus(Order.Status.WAITING_FOR_PAIMENT);
			order3.setComments(comments);
			orderService.saveNewOrder(order3);

			Order order4 = new Order();
			order4.setUser(userService.findOne(2));
			order4.addProduct(productService.findOne(2), 15);
			order4.setStatus(Order.Status.PAID);
			order4.setComments(comments);
			orderService.saveNewOrder(order4);
		}
		
	}

	private void initManyProducts() {
		logger.debug("--- started");
		
		String description = "Interactively procrastinate high-payoff content without backward-compatible data. Quickly cultivate optimal processes and tactical architectures. Completely iterate covalent strategic theme areas via accurate e-markets.";
		
		int numberOfProducts = 1000;
		List<Product> products = new ArrayList<>();
		Random random = new Random();
		
		// create list of products
		for (int i = 1; i < numberOfProducts + 1; i++) {
			Product cabinet = new Product();
			cabinet.setName("Cabinet " + i);
			cabinet.setCategory(categoryService.findByName("Cabinets"));
			cabinet.setDescription(description);
			cabinet.setPrice((double)(random.nextInt(100)) * 10);
			cabinet.setPublishedDate(new Date(new GregorianCalendar(2014, 2, 25).getTimeInMillis()));
			cabinet.setQuantityInStock(14);
			products.add(cabinet);

			Product sofa = new Product();
			sofa.setName("Sofa " + i);
			sofa.setCategory(categoryService.findByName("Sofas"));
			sofa.setDescription(description);
			sofa.setPrice((double)(random.nextInt(100)) * 10);
			sofa.setPublishedDate(new Date(new GregorianCalendar(2013, 5, 22).getTimeInMillis()));
			sofa.setQuantityInStock(10);
			products.add(sofa);

			Product armchair = new Product();
			armchair.setName("Armchair " + i);
			armchair.setCategory(categoryService.findByName("Armchairs"));
			armchair.setDescription(description);
			armchair.setPrice((double)(random.nextInt(100)) * 10);
			armchair.setPublishedDate(new Date(new GregorianCalendar(2012, 8, 5).getTimeInMillis()));
			armchair.setQuantityInStock(20);
			products.add(armchair);

			Product table = new Product();
			table.setName("Table " + i);
			table.setCategory(categoryService.findByName("Tables"));
			table.setDescription(description);
			table.setPrice((double)(random.nextInt(100)) * 10);
			table.setPublishedDate(new Date(new GregorianCalendar(2011, 10, 15).getTimeInMillis()));
			table.setQuantityInStock(5);
			products.add(table);
		}
		
		// save products to DB
		logger.debug("List of products is ready to save");
		productService.save(products);
	}

}
