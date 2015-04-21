package ua.store.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.domain.model.entity.Product;
import ua.store.domain.model.entity.ProductCategory;
import ua.store.domain.model.entity.User;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

	ProductCategory findOneByName(String name);

}
