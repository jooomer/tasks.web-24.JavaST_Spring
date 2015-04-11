/*
 * 
 */

package ua.store.controller.product;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.model.dto.ProductCategoryCatalogDto;
import ua.store.model.entity.Order;
import ua.store.model.entity.Product;
import ua.store.model.entity.ProductCategory;
import ua.store.service.ProductService;
import ua.store.service.ProductCategoryService;
import ua.store.service.UserService;
import ua.store.tag.ProductMap;

@Controller
public class ProductsController {

	private static final Logger logger = LogManager
			.getLogger(ProductsController.class);

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private ProductCategoryService productCategoryService;
	
	@RequestMapping("/catalog")
	public String showCatalog(Model model) {
		logger.debug("--- started");
		
//		return "catalog";
		return "redirect:category/0/products/page/1";
	}

	@RequestMapping("/category/{catIdStr}/products/page/{pageStr}")
	public String showProductsByCategory(
			Model model, 
			RedirectAttributes redirectAttributes,
			@PathVariable String catIdStr, 
			@PathVariable String pageStr) {
		logger.debug("--- started");
		logger.debug("URL: /category/" + catIdStr + "/products/page/" + pageStr);
		
		// get all categories from DB
		List<ProductCategory> listOfProductCategories = productCategoryService.findAll();
		
		// get category Id and name from URL and check Id
		int catId = parsePathVariable(catIdStr);
		String categoryName = null;
		if (catId < 1 || catId > listOfProductCategories.size()) {
			logger.debug("catId < 1 || catId > category number");
			// just show products for all categories
			catId = 0;
			categoryName = "All categories";
		} else {
			categoryName = listOfProductCategories.get(catId - 1).getName();
		}

		// get page number from URL and total of pages from DB
		int page = parsePathVariable(pageStr);
		int totalPages = productService.getTotalPages();

		// check min/max page request
		if (page < 1) {
			logger.debug("page < 1; catId=" + catId);
			return "redirect:/category/" + catId + "/products/page/1";
		} else if (page > totalPages) {
			logger.debug("page > totalPages");
			return "redirect:/category/" + catId + "/products/page/" + (totalPages);
		}

		// prepare DTO to get particular category from a form
		model.addAttribute("ProductCategory", new ProductCategoryCatalogDto());
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("catId", catId);
		
		// prepare list of categories for a view
		model.addAttribute("listOfProductCategories", listOfProductCategories);

		// prepare list of products for view 
		List<Product> listOfProducts = productService.findAllByPage(page - 1);
		model.addAttribute("listOfProducts", listOfProducts);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", totalPages);
		
		return "products-by-category";
	}
	
//	@RequestMapping("/categories/{type}")
//	public String showProductsByType(Model model, HttpServletRequest request,
//			@PathVariable String type) {
//		logger.debug("showProductsByType() started. Product type is \"" + type
//				+ "\"");
//		
//		// get products list by product type
//		ProductCategory productCategory = productCategoryService.findByName(type);
//		List<Product> products = productService
//				.findAllByProductCategory(productCategory);
//		
//		// prepare products for view
//		ProductMap productMap = new ProductMap(products);
//		request.getSession().setAttribute("productMap", productMap);
//		
//		// show list of products
//		model.addAttribute("jspPage",
//				"/WEB-INF/view/administrator/products.jsp");
//		return "template";
//	}

//	@RequestMapping("/products")
//	public String showProducts() {
//		logger.debug("showProducts() started.");
//		return "redirect:products/page-0";
//	}
//
//	@RequestMapping("/products/page-{pageStr}")
//	public String showProducts(Model model, HttpServletRequest request, @PathVariable String pageStr) {
//		logger.debug("showProducts() started.");
//		
//		// parse PathVariable
//		int page = parsePathVariable(pageStr);
//		
//		int totalPages = productService.getTotalPages();
//		
//		// check min/max page request
//		if (page < 1) {
//			return "redirect:/products/page-1";
//		} else if (page > totalPages) {
//			return "redirect:/products/page-" + (totalPages);
//		}
//		
//		// get list of all products from DB
//		List<Product> listOfProducts = productService.findAllByPage(page - 1);
//		
//		// prepare list of products for view 
//		model.addAttribute("listOfProducts", listOfProducts);
//		model.addAttribute("page", page);
//		model.addAttribute("totalPages", totalPages);
//		
//		// show list of products
//		return "products";
//	}

	@RequestMapping("/products/{id}")
	public String showProductDetail(Model model, @PathVariable int id) {
		logger.debug("showProductDetail() started. Product Id is \"" + id
				+ "\"");
		
		// get Product from DB
		model.addAttribute("product", productService.findOne(id));
		
		// show product detail
		return "product-detail";
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.POST, params = {"delete"})
	public String doDeleteProduct(Model model, @PathVariable int id,
			RedirectAttributes redirectAttributes) {
		logger.debug("doUpdateProduct() started. Product Id is \"" + id + "\"");
		
		// delete Product by id
		productService.delete(id);
		
		// prepare message
		redirectAttributes.addFlashAttribute("message",
				"Congratulations! You've just successfully deleted a product.");
		
		// show list of products again
		return "redirect:/products";
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.POST, params = {"send-to-cart"})
	public String doSendToCart(Model model, @PathVariable int id, Principal principal,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		logger.debug("doSendToCart() started. Product Id is \"" + id + "\"");
		
		// get Product from DB
		Product product = productService.findOne(id);
		if (product == null) {
			model.addAttribute("message", "Error! Product is unavailable. Please, choose another one.");
			model.addAttribute("jspPage", "/WEB-INF/view/common/message.jsp");
			return "template";
		}
		
		// get Order from session
		// if absent, then get new one
		Order order = (Order) request.getSession().getAttribute("order");
		if (order == null) {
			order = new Order();
		}
		
		// add Product to Order
		order.addProduct(product);
		
		// save Order to session
		request.getSession().setAttribute("order", order);
		
		// prepare ProductMap to show products in a cart page
		ProductMap productMap = new ProductMap(order);
		request.getSession().setAttribute("productMap", productMap);
		
		// show cart.jsp
		redirectAttributes.addFlashAttribute("message", "Product is successfully added to your cart.");
		return "redirect:/cart";
	}
	
	/**
	 * parse url parameter and check it
	 */
	private int parsePathVariable(String string) {
		int number;
		try {
			number = Integer.valueOf(string);
		} catch (NumberFormatException e) {
//			throw new WrongUrlException(e);
			// just show first
			number = 0;
		}
		return number;
	}



}
