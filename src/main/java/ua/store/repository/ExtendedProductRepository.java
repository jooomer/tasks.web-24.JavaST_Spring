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

import com.oracle.webservices.internal.api.databinding.Databinding.Builder;

import ua.store.controller.product.ProductsController;
import ua.store.model.entity.Product;
import ua.store.model.entity.ProductCategory;

@Repository
public class ExtendedProductRepository {

	private static final Logger logger = LogManager
			.getLogger(ExtendedProductRepository.class);

	@PersistenceContext
	private EntityManager em;

	public List<Product> findByProductCategoryByPage(
			ProductCategory productCategory, int page, int items,
			Direction direction, String field) {
		logger.debug("--- start");
		
		// Criteria
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
		
		Root<Product> rProduct = query.from(Product.class);
		
		ParameterExpression<ProductCategory> pCategory = criteriaBuilder.parameter(ProductCategory.class);
//		ParameterExpression<Integer> pPage = criteriaBuilder.parameter(Integer.class);
//		ParameterExpression<Integer> pItems = criteriaBuilder.parameter(Integer.class);
//		ParameterExpression<Direction> pDirection = criteriaBuilder.parameter(Direction.class);
//		ParameterExpression<String> pField = criteriaBuilder.parameter(String.class);
		
		query.select(rProduct).where(
				criteriaBuilder.equal(rProduct.get("productCategory"), pCategory)
				);
		query.orderBy(criteriaBuilder.asc(rProduct.get(field)));
//		query.orderBy(criteriaBuilder.asc(rProduct.get(field)));
		
		TypedQuery<Product> typedQuery = em.createQuery(query);
		
		typedQuery.setParameter(pCategory, productCategory);
		
//		page = 1;
//		items = 50;
		
		int resultListSize = typedQuery.getResultList().size();
		int fromIndex = page * items;
		int toIndex = fromIndex + items;
		if (fromIndex >= resultListSize) {
			return null;
		} 
		if (toIndex > resultListSize) {
			toIndex = resultListSize;
		}
		
		return  typedQuery.getResultList().subList(fromIndex, toIndex);
	}

	public int getTotalPagesByCategory(ProductCategory productCategory, int page,
			int items) {
		logger.debug("--- start");
		
		// Criteria
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
		
		Root<Product> rProduct = query.from(Product.class);
		ParameterExpression<ProductCategory> pCategory = criteriaBuilder.parameter(ProductCategory.class);

		query.select(rProduct).where(
				criteriaBuilder.equal(rProduct.get("productCategory"), pCategory)
				);
		
		TypedQuery<Product> typedQuery = em.createQuery(query);
		typedQuery.setParameter(pCategory, productCategory);
		
		return typedQuery.getResultList().size() / items + 1;
	}

}
