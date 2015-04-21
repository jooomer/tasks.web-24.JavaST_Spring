package ua.store.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.store.domain.model.entity.Product;
import ua.store.domain.model.entity.ProductCategory;
import ua.store.repository.ProductCategoryRepository;

@Service
@Transactional
public class ProductCategoryService {
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository; 
	
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

	public List<ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}

	public ProductCategory findByName(String name) {
		return productCategoryRepository.findOneByName(name);
	}

	public ProductCategory save(ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}

	public ProductCategory findOne(int id) {
		return productCategoryRepository.findOne(id);
	}

	public void delete(ProductCategory productCategory) {
		List<Product> products = productService.findAllByCategory(productCategory);
		for (Product product : products) {
			product.setProductCategory(null);
			productService.save(product);
		}
		productCategoryRepository.delete(productCategory);
	}

}
