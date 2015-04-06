package ua.store.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.model.entity.Product;
import ua.store.model.entity.ProductCategory;
import ua.store.model.entity.User;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

	ProductCategory findOneByName(String name);

}
