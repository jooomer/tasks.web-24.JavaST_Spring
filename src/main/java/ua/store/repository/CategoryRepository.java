package ua.store.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.domain.model.entity.Product;
import ua.store.domain.model.entity.Category;
import ua.store.domain.model.entity.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findOneByName(String name);

}
