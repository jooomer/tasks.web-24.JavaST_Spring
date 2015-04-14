package ua.store.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import ua.store.model.entity.Product;
import ua.store.model.entity.ProductCategory;

@Repository
public class ExtendedProductRepository {

	private static final Logger logger = LogManager
			.getLogger(ExtendedProductRepository.class);

	@PersistenceContext
	private EntityManager em;

	public List<Product> findByProductCategoryByPage(
			ProductCategory productCategory, int page, int itemsOnPage,
			Direction direction, String field) {
		logger.debug("--- start");

		// Criteria
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> query = criteriaBuilder
				.createQuery(Product.class);

		Root<Product> rProduct = query.from(Product.class);

		ParameterExpression<ProductCategory> pCategory = criteriaBuilder
				.parameter(ProductCategory.class);

		query.select(rProduct).where(
				criteriaBuilder.equal(rProduct.get("productCategory"),
						pCategory));
		
		if (direction == Direction.ASC) {
			query.orderBy(criteriaBuilder.asc(rProduct.get(field)));
		} else {
			query.orderBy(criteriaBuilder.desc(rProduct.get(field)));
		}

		TypedQuery<Product> typedQuery = em.createQuery(query);

		typedQuery.setParameter(pCategory, productCategory);

		// page = 1;
		// items = 50;

		int resultListSize = typedQuery.getResultList().size();
		int fromIndex = page * itemsOnPage;
		int toIndex = fromIndex + itemsOnPage;
		if (fromIndex >= resultListSize) {
			return null;
		}
		if (toIndex > resultListSize) {
			toIndex = resultListSize;
		}

		return typedQuery.getResultList().subList(fromIndex, toIndex);
	}

	public int getTotalPagesByCategory(ProductCategory productCategory,
			int page, int itemsOnPage) {
		logger.debug("--- start");

		// Criteria
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> query = criteriaBuilder
				.createQuery(Product.class);

		Root<Product> rProduct = query.from(Product.class);
		ParameterExpression<ProductCategory> pCategory = criteriaBuilder
				.parameter(ProductCategory.class);

		query.select(rProduct).where(
				criteriaBuilder.equal(rProduct.get("productCategory"),
						pCategory));

		TypedQuery<Product> typedQuery = em.createQuery(query);
		typedQuery.setParameter(pCategory, productCategory);

		return typedQuery.getResultList().size() / itemsOnPage + 1;
	}

}
