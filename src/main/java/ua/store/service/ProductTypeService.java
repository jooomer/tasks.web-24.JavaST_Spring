package ua.store.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.store.model.entity.Product;
import ua.store.model.entity.ProductType;
import ua.store.repository.ProductTypeRepository;

@Service
@Transactional
public class ProductTypeService {
	
	@Autowired
	private ProductTypeRepository productTypeRepository; 
	
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

	public List<ProductType> findAll() {
		return productTypeRepository.findAll();
	}

	public ProductType findByName(String name) {
		return productTypeRepository.findOneByName(name);
	}

	public ProductType save(ProductType productType) {
		return productTypeRepository.save(productType);
	}

	public ProductType findOne(int id) {
		return productTypeRepository.findOne(id);
	}

	public void delete(ProductType productType) {
		List<Product> products = productService.findAllByProductType(productType);
		for (Product product : products) {
			product.setProductType(null);
			productService.save(product);
		}
		productTypeRepository.delete(productType);
	}

}
