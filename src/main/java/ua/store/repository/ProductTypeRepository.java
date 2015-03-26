package ua.store.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.model.entity.Product;
import ua.store.model.entity.ProductType;
import ua.store.model.entity.User;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {

	ProductType findOneByName(String name);

}
