package ua.store.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ua.store.domain.model.entity.Product;
import ua.store.domain.model.entity.ProductCategory;
import ua.store.domain.model.entity.User;
import ua.store.repository.ExtendedProductRepository;
import ua.store.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ExtendedProductRepository extendedProductRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductCategoryService productCategoryService;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Product> findAllByPage(int page, int itemsOnPage, Direction direction, String sortField) {
		return productRepository.findAll(
				new PageRequest(page, itemsOnPage, direction, sortField)).getContent();
	}

	public int getTotalPages() {
		return productRepository.findAll(new PageRequest(0, 10))
				.getTotalPages();
	}

	public Product findOne(int id) {
		return productRepository.findOne(id);
	}

	// public List<Product> findAllByUserName(String name) {
	// List<Product> myProducts = productRepository.findAllByUserName(name);
	// return myProducts;
	// }

	// public void save(Product product, String name) {
	// User user = userService.findOneWithCarts(name);
	// product.setUser(user);
	// String productTypeName = product.getProductType().getName();
	// ProductType productType = productTypeService.findByName(productTypeName);
	// product.setProductType(productType);
	// productRepository.save(product);
	// }

	public Product findOneByName(String name) {
		return productRepository.findOneByName(name);
	}

	@PreAuthorize(value = "#product.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("product") Product product) {
		productRepository.delete(product);
	}

	public List<Product> findAllByCategory(ProductCategory productCategory) {
		return productRepository.findByProductCategory(productCategory);
	}

	public void save(Product product) {
		ProductCategory productCategory = productCategoryService
				.findByName(product.getProductCategory().getName());
		product.setProductCategory(productCategory);
		productRepository.save(product);
	}

	public void delete(int id) {
		productRepository.delete(id);
	}

	public void save(List<Product> products) {
		productRepository.save(products);
	}

	public List<Product> findByCategoryByPage(String categoryName, int page,
			int itemsOnPage, Direction direction, String sortField) {
		ProductCategory productCategory = productCategoryService
				.findByName(categoryName);
		return extendedProductRepository.findByProductCategoryByPage(
				productCategory, page, itemsOnPage,	direction, sortField);
	}

	public int getTotalPagesByCategory(String categoryName, int itemsOnPage) {
		ProductCategory productCategory = productCategoryService
				.findByName(categoryName);
		return extendedProductRepository.getTotalPagesByCategory(
				productCategory, 0, itemsOnPage);
	}

}
