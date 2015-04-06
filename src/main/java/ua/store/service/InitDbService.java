package ua.store.service;

import java.util.ArrayList;
import java.util.Date;
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
		Product product1 = new Product();
		product1.setName("Cabinet");
		product1.setDescription("This cabinet has a modern design.");
		product1.setProductCategory(productCategoryCabinets);
		product1.setPrice(600.);
		product1.setPublishedDate(new Date());
		product1.setQuantityInStock(14);
		productService.save(product1);

		Product product2 = new Product();
		product2.setName("Sofa");
		product2.setDescription("This sofa is very soft.");
		product2.setProductCategory(productCategorySofas);
		product2.setPrice(500.);
		product2.setPublishedDate(new Date());
		product2.setQuantityInStock(5);
		productService.save(product2);

		Product product3 = new Product();
		product3.setName("Armchair");
		product3.setDescription("This armchair has a good price.");
		product3.setProductCategory(productCategoryArmchairs);
		product3.setPrice(1000.);
		product3.setPublishedDate(new Date());
		product3.setQuantityInStock(29);
		productService.save(product3);

		Product product4 = new Product();
		product4.setName("Tables");
		product4.setDescription("This table has a circle surface.");
		product4.setProductCategory(productCategoryTables);
		product4.setPrice(3000.);
		product4.setPublishedDate(new Date());
		product4.setQuantityInStock(8);
		productService.save(product4);
		
		initManyProducts();
		
		// init orders
		logger.debug("--- init orders started.");
		Order order1 = new Order();
		order1.setUser(userAdmin);
		order1.addProduct(product1);
		order1.addProduct(product2);
		order1.addProduct(product3);
		order1.setOrderStatus(OrderStatus.DELIVERED);
		order1.setComments("I wanna get it before Friday, 13");
		orderService.save(order1);
		
		
	}

	private void initManyProducts() {
		logger.debug("--- started");
		
		int numberOfProducts = 50;
		List<Product> products = new ArrayList<>();
		List<ProductCategory> productCategories = productCategoryService.findAll();
		
		// create list of products
		for (int i = 0; i < numberOfProducts; i++) {
			Product product = new Product();
			product.setName("Product " + i);
			product.setProductCategory(productCategories.get(0));
			products.add(product);
		}
		
		productService.save(products);
	}

}
