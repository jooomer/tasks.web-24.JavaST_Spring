package ua.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.store.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findOneByName(String name);

}
