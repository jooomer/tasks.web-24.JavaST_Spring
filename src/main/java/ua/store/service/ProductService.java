package ua.store.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ua.store.domain.Category;
import ua.store.domain.OrderItem;
import ua.store.domain.Product;
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
	private CategoryService categoryService;
	
	@Autowired
	private OrderItemService orderItemService;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Product> findAllByPage(int page, int itemsOnPage, Direction direction, String sortField) {
		return productRepository.findAll(
				new PageRequest(page, itemsOnPage, direction, sortField)).getContent();
	}

	public int getTotalPages(int itemsOnPage) {
		return productRepository.findAll(new PageRequest(0, itemsOnPage))
				.getTotalPages();
	}

	public Product findOne(long id) {
		return productRepository.findOne(id);
	}

	public Product findOneByName(String name) {
		return productRepository.findOneByName(name);
	}

//	@PreAuthorize(value = "#product.user.name == authentication.name or hasRole('ROLE_ADMIN')")
//	public void delete(@P("product") Product product) {
//		productRepository.delete(product);
//	}

	public void delete(Product product) {
		product = productRepository.findOne(product.getId());
		Set<OrderItem> orderItems = product.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			orderItem.setProduct(null);
		}
		orderItemService.update(orderItems);
		productRepository.delete(product);
	}

	public List<Product> findAllByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	public void save(Product product) {
		Category category = categoryService
				.findByName(product.getCategory().getName());
		product.setCategory(category);
		productRepository.save(product);
	}

	public void delete(long id) {
		productRepository.delete(id);
	}

	public void save(List<Product> products) {
		productRepository.save(products);
	}

	public List<Product> findByCategoryByPage(String categoryName, int page,
			int itemsOnPage, Direction direction, String sortField) {
		Category category = categoryService
				.findByName(categoryName);
		return extendedProductRepository.findByProductCategoryByPage(
				category, page, itemsOnPage,	direction, sortField);
	}

	public int getTotalPagesByCategory(String categoryName, int itemsOnPage) {
		Category category = categoryService
				.findByName(categoryName);
		return extendedProductRepository.getTotalPagesByCategory(
				category, 0, itemsOnPage);
	}

}
