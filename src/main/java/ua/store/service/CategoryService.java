package ua.store.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.store.domain.model.entity.Product;
import ua.store.domain.model.entity.Category;
import ua.store.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Autowired
	private ProductService productService;
	
//	public List<String> findAll() {
//		List<ProductType> productTypes = productTypeRepository.findAll();
//		List<String> types = new ArrayList<>();
//		for (ProductType productType : productTypes) {
//			types.add(productType.getName());
//		}
//		return types;
//	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findByName(String name) {
		return categoryRepository.findOneByName(name);
	}

	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	public Category findOne(Long id) {
		return categoryRepository.findOne(id);
	}


	public void delete(Long id) {
		Category category = categoryRepository.findOne(id);
		List<Product> products = productService.findAllByCategory(category);
		for (Product product : products) {
			product.setCategory(null);
		}
		productService.save(products);
		categoryRepository.delete(id);
		
	}

}
