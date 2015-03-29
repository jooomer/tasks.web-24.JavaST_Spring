package ua.store.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ua.store.model.entity.Product;
import ua.store.model.entity.ProductType;
import ua.store.model.entity.User;
import ua.store.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findOne(int id) {
		return productRepository.findOne(id);
	}

//	public List<Product> findAllByUserName(String name) {
//		List<Product> myProducts = productRepository.findAllByUserName(name);
//		return myProducts;
//	}

//	public void save(Product product, String name) {
//		User user = userService.findOneWithCarts(name);
//		product.setUser(user);
//		String productTypeName = product.getProductType().getName();
//		ProductType productType = productTypeService.findByName(productTypeName);
//		product.setProductType(productType);
//		productRepository.save(product);
//	}

	public Product findOneByName(String name) {
		return productRepository.findOneByName(name);
	}

	@PreAuthorize(value = "#product.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("product") Product product) {
		productRepository.delete(product);
	}

	public List<Product> findAllByProductType(ProductType productType) {
		return productRepository.findByProductType(productType);
	}

	public void save(Product product) {
		ProductType productType = productTypeService.findByName(product.getProductType().getName());
		product.setProductType(productType);
		productRepository.save(product);
	}

	public void delete(int id) {
		productRepository.delete(id);
	}

}
