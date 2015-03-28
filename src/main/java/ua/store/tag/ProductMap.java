/**
 *
 */
package ua.store.tag;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.store.controller.admin.UsersController;
import ua.store.model.entity.Order;
import ua.store.model.entity.OrderItem;
import ua.store.model.entity.Product;
import ua.store.model.entity.ProductType;

/**
 * @author Sergey
 *
 */
public class ProductMap {

	private static final Logger logger = LogManager.getLogger(ProductMap.class);

	private Map<Product, Integer> productMap;
	private Iterator<Entry<Product, Integer>> iterator;
	private Product product;

	public ProductMap() {
		
		logger.debug("Constructor ProducMap() created");

		// get list of all products
//		ProductDAO productDAO = DAOFactory.getProductDAO();
//
//		this.productMap = productDAO.getAllProductsMap();
//		this.iterator = this.productMap.entrySet().iterator();
	}

	public ProductMap(ProductType productType) {

		logger.debug("Constructor ProductMap(ProductType productType) created");

		// get list of products by type
//		ProductDAO productDAO = DAOFactory.getProductDAO();
//		TreeMap<Product, Integer> productMap = productDAO.getProductsMapByType(productType);
//
//		this.productMap = productMap;
//		this.iterator = productMap.entrySet().iterator();
	}

	public ProductMap(Order order) {

		logger.debug("Constructor ProductMap(Order order) started");

		// get list of products from order
		
		Set<OrderItem> orderItems = order.getOrderItems();
		this.productMap = new TreeMap<Product, Integer>();
		 
		for (OrderItem orderItem : orderItems) {
			Product product = orderItem.getProduct();
			this.productMap.put(product, product.getQuantityInStock());
		}
		this.iterator = productMap.entrySet().iterator();
	}

	public ProductMap(List<Product> products) {

		logger.debug("Constructor ProductMap(List<Product> products) started");
		
		productMap = new TreeMap<Product, Integer>();
		 
		for (Product product : products) {
			this.productMap.put(product, product.getQuantityInStock());
		}
		this.iterator = productMap.entrySet().iterator();
	}

	public void createProductList() {

		logger.debug("createProductList() started");

		// get list of all products
//		ProductDAO productDAO = DAOFactory.getProductDAO();
//
//		this.productMap = productDAO.getAllProductsMap();
//		this.iterator = this.productMap.entrySet().iterator();
	}
	
	public void createProductMap(ProductType productType) {

		logger.debug("createProductMap(ProductType productType) started");

		// get list of all products
//		ProductDAO productDAO = DAOFactory.getProductDAO();
//		TreeMap<Product, Integer> productMap = productDAO.getProductsMapByType(productType);
//
//		if (productMap == null) {
//			productMap = new TreeMap<>();
//		}
//		this.productMap = productMap;
//		this.iterator = productMap.entrySet().iterator();
	}

	public void resetIterator() {
		this.iterator = productMap.entrySet().iterator();
	}

	public int getSize() {
		return this.productMap.size();
	}

	public String getProductId() {
		if (iterator.hasNext()) {
			Entry<Product, Integer> entry = iterator.next();
			this.product = entry.getKey();
			return String.valueOf(product.getId());
		} else {
			return null;
		}
	}

	public String getProductType() {
		if (product != null) {
			return this.product.getProductType().getName();
		} else {
			return null;
		}
	}

	public String getProductName() {
		if (product != null) {
			return this.product.getName();
		} else {
			return null;
		}
	}

	public String getPrice() {
		if (product != null) {
			return String.valueOf(this.product.getPrice());
		} else {
			return null;
		}
	}

	public String getQuantityInStock() {
		if (product != null) {
			return String.valueOf(this.product.getQuantityInStock());
		} else {
			return null;
		}
	}

	public String getDescription() {
		if (product != null) {
			return this.product.getDescription();
		} else {
			return null;
		}
	}

	/**
	 * @return the userList
	 */
	public Map<Product, Integer> getProductMap() {
		return productMap;
	}

	public String toString() {
		String str = "";
		for (Entry<Product, Integer> entry : this.productMap.entrySet()) {
			Product product = entry.getKey();
			str += product.getName() + " ";
		}

		return str;
	}

}
