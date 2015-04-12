package ua.store.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.model.entity.Order;
import ua.store.model.entity.OrderItem;
import ua.store.model.entity.Product;
import ua.store.model.entity.ProductCategory;
import ua.store.model.entity.User;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findOneByName(String name);

	List<Product> findByProductCategory(ProductCategory productCategory);

}
