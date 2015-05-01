package ua.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.domain.Category;
import ua.store.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findOneByName(String name);

	List<Product> findByCategory(Category category);

	Product findOne(Long id);

}
