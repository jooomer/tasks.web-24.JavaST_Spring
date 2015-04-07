package ua.store.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ua.store.model.entity.Product;
import ua.store.model.entity.ProductCategory;
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
	private ProductCategoryService productCategoryService;
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Product> findAllByPage(int page) {
		return productRepository.findAll(new PageRequest(page, 10, Direction.ASC, "id")).getContent();
	}

	public int getTotalPages() {
		return productRepository.findAll(new PageRequest(0, 10)).getTotalPages();
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

	public List<Product> findAllByProductCategory(ProductCategory productCategory) {
		return productRepository.findByProductCategory(productCategory);
	}

	public void save(Product product) {
		ProductCategory productCategory = productCategoryService.findByName(product.getProductCategory().getName());
		product.setProductCategory(productCategory);
		productRepository.save(product);
	}

	public void delete(int id) {
		productRepository.delete(id);
	}

	public void save(List<Product> products) {
		for (Product product : products) {
			productRepository.save(product);
		}
		
	}


}
