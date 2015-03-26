package ua.store.service;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.store.model.entity.Product;
import ua.store.model.entity.ProductType;
import ua.store.model.entity.Role;
import ua.store.model.entity.User;
import ua.store.repository.ProductRepository;
import ua.store.repository.ProductTypeRepository;
import ua.store.repository.RoleRepository;
import ua.store.repository.UserRepository;

@Transactional
@Service
public class InitDbService {
	
	private static final Logger logger = LogManager.getLogger(InitDbService.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductTypeRepository productTypeRepository;

	@PostConstruct
	public void init() {
		
		logger.debug("init() started.");
		
		if (roleRepository.findByName("ROLE_ADMIN") != null) {
			return;
		}
		
		doInit(); 
	
		logger.debug("init() successfully finished.");
	}

	public void doInit() {
		
		roleRepository.deleteAll();
		userRepository.deleteAll();
		productRepository.deleteAll();
		productTypeRepository.deleteAll();
		
		// init roles
		logger.debug("init roles started.");
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		// init first user "admin"
		logger.debug("init first user 'admin' started.");
		User userAdmin = new User();
		userAdmin.setName("admin");
		userAdmin.setEmail("freddy@krugger.com");
		userAdmin.setFirstName("Freddy");
		userAdmin.setLastName("Krugger");
		userAdmin.setPhone("(103) 501-23-92");
		userAdmin.setAddress("45, First str, New York, USA");
		userAdmin.setComments("This user is an administrator");
		userAdmin.setInBlackList(false);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		userAdmin.setEnabled(true);
		userAdmin.addRole(roleUser);
		userAdmin.addRole(roleAdmin);
		userRepository.save(userAdmin);

		// init second user "user"
		logger.debug("init first user 'user' started.");
		User userUser = new User();
		userUser.setName("user");
		userUser.setEmail("mike@tyson.com");
		userUser.setFirstName("Mike");
		userUser.setLastName("Tyson");
		userUser.setPhone("(103) 694-03-85");
		userUser.setAddress("196, Second str, San Francisco, USA");
		userUser.setComments("This user is a bad boy");
		userUser.setInBlackList(true);
		encoder = new BCryptPasswordEncoder();
		userUser.setPassword(encoder.encode("user"));
		userUser.setEnabled(true);
		userUser.addRole(roleUser);
		userRepository.save(userUser);

		// init product types
		logger.debug("init product types started.");
		ProductType productTypeCabinets = new ProductType();
		productTypeCabinets.setName("Cabinets");
		productTypeRepository.save(productTypeCabinets);

		ProductType productTypeSofas = new ProductType();
		productTypeSofas.setName("Sofas");
		productTypeRepository.save(productTypeSofas);

		ProductType productTypeArmchairs = new ProductType();
		productTypeArmchairs.setName("Armchairs");
		productTypeRepository.save(productTypeArmchairs);

		ProductType productTypeTables = new ProductType();
		productTypeTables.setName("Tables");
		productTypeRepository.save(productTypeTables);

		// init products
		logger.debug("init products started.");
		Product product1 = new Product();
		product1.setName("Cabinet");
		product1.setDescription("This cabinet has a modern design.");
		product1.setUser(userAdmin);
		product1.setProductType(productTypeCabinets);
		product1.setPrice(600.);
		product1.setPublishedDate(new Date());
		productRepository.save(product1);

		Product product2 = new Product();
		product2.setName("Sofa");
		product2.setDescription("This sofa is very soft.");
		product2.setUser(userAdmin);
		product2.setProductType(productTypeSofas);
		product2.setPrice(500.);
		product2.setPublishedDate(new Date());
		productRepository.save(product2);

		Product product3 = new Product();
		product3.setName("Armchair");
		product3.setDescription("This armchair has a good price.");
		product3.setUser(userUser);
		product3.setProductType(productTypeArmchairs);
		product3.setPrice(1000.);
		product3.setPublishedDate(new Date());
		productRepository.save(product3);

		Product product4 = new Product();
		product4.setName("Tables");
		product4.setDescription("This table has a circle surface.");
		product4.setUser(userUser);
		product4.setProductType(productTypeTables);
		product4.setPrice(3000.);
		product4.setPublishedDate(new Date());
		productRepository.save(product4);
		
	}

}
