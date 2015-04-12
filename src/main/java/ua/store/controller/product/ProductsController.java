/*
 * 
 */

package ua.store.controller.product;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.model.dto.SelectSortByDto;
import ua.store.model.dto.SelectProductCategoryDto;
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

	@ModelAttribute("category")
	public SelectProductCategoryDto constructSelectProductCategoryDto() {
		return new SelectProductCategoryDto();
	}

	@ModelAttribute("selectOrderBy")
	public SelectSortByDto constructSelectSortByDto() {
		return new SelectSortByDto();
	}

	/**
	 * just shows first page of list of product
	 */
	@RequestMapping("/catalog")
	public String showCatalog(Model model) {
		logger.debug("--- started");

		// return "catalog";
		return "redirect:category/0/products/page/1";
	}

	/**
	 * shows list of products by selected category (page-by-page)
	 */
	@RequestMapping("/category/{catIdStr}/products/page/{pageStr}")
	public String showProductsByCategory(
			Model model,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request,
			@PathVariable String catIdStr,
			@PathVariable String pageStr,
			@ModelAttribute("category") SelectProductCategoryDto selectProductCategoryDto,
			@ModelAttribute("selectSortBy") SelectSortByDto selectSortByDto) {
		logger.debug("--- started");
		logger.debug("URL: /category/" + catIdStr + "/products/page/" + pageStr);

		// ---------------------------------
		// handle and initialize parameters of select form
		// ---------------------------------
		HttpSession session = request.getSession();
		
		// initialize itemsOnpage
		int itemsOnPage;
		if (session.getAttribute("catalog_itemsOnPage") != null) {
			itemsOnPage = (int) session.getAttribute("catalog_itemsOnPage");
		} else {
			itemsOnPage = 10;
			session.setAttribute("catalog_itemsOnPage", itemsOnPage);
		}
		if (selectSortByDto != null && selectSortByDto.getItemsOnPage() > 0) {
			itemsOnPage = selectSortByDto.getItemsOnPage();
			session.setAttribute("catalog_itemsOnPage", itemsOnPage);
		} 

		// initialize sort direction 
		Direction direction;
		if (session.getAttribute("catalog_direction") != null) {
			direction = (Direction) session.getAttribute("catalog_direction");
		} else {
			direction = Direction.ASC;
			session.setAttribute("catalog_direction", direction);
		}
		if (selectSortByDto != null && selectSortByDto.getSortDirection() != null) {
			direction = Direction.valueOf(selectSortByDto.getSortDirection());
			session.setAttribute("catalog_direction", direction);
		} 

		// initialize selected category
		if (selectProductCategoryDto.getProductCategory() != null) {
			logger.debug("Selected category: "
					+ selectProductCategoryDto.getProductCategory().getName());

			// get catId from DTO and reload page
			int catId = getCatIdFromDto(selectProductCategoryDto);
			return "redirect:/category/" + catId + "/products/page/1";
		}

		// ---------------------------------
		// handle {catIdStr}
		// ---------------------------------

		// get category Id and name from URL and check Id
		int catId = parsePathVariable(catIdStr);
		List<ProductCategory> listOfProductCategories = productCategoryService
				.findAll();
		String categoryName = null;

		// check min/max category request
		if (catId < 0 || catId > listOfProductCategories.size()) {
			logger.debug("catId < 0 or catId > category number; catId = "
					+ catId);
			return "redirect:/category/" + 0 + "/products/page/" + pageStr;
		}

		int totalPages;
		if (catId == 0) {
			logger.debug("catId == 0 ; catId = " + catId);

			// just show products for all categories
			categoryName = "All categories";
			catId = 0;
			totalPages = productService.getTotalPages();
		} else {
			logger.debug("catId is between 0 and category number; catId = "
					+ catId);

			// get category name
			categoryName = listOfProductCategories.get(catId - 1).getName();
			totalPages = productService.getTotalPagesByCategory(categoryName, itemsOnPage);
		}

		// ---------------------------------
		// handle {pageStr}
		// ---------------------------------

		// get page number from URL
		int page = parsePathVariable(pageStr);

		// check min/max page request
		if (page < 1) {
			logger.debug("page < 1; page = " + page);
			return "redirect:/category/" + catId + "/products/page/1";
		} else if (page > totalPages) {
			logger.debug("page > totalPages");
			return "redirect:/category/" + catId + "/products/page/"
					+ (totalPages);
		}

		// ---------------------------------
		// prepare parameters to display
		// ---------------------------------
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("catId", catId);

		// prepare list of categories for a view
		model.addAttribute("listOfProductCategories", listOfProductCategories);

		// prepare list of products for view
		List<Product> listOfProducts = null;
		if (catId == 0) {
			listOfProducts = productService.findAllByPage(page - 1, itemsOnPage, direction);
		} else {
			listOfProducts = productService.findByCategoryByPage(categoryName, page - 1, itemsOnPage, direction);
		}

		// prepare some parameters
		model.addAttribute("listOfProducts", listOfProducts);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", totalPages);

		return "products-by-category";
	}

	/**
	 * gets category Id that user selected
	 */
	private int getCatIdFromDto(
			SelectProductCategoryDto selectProductCategoryDto) {
		String productCategoryName = selectProductCategoryDto
				.getProductCategory().getName();
		ProductCategory productCategory = productCategoryService
				.findByName(productCategoryName);
		if (productCategory != null) {
			return productCategory.getId();
		} else {
			return 0;
		}
	}

	/**
	 * shows product detail
	 */
	@RequestMapping("/products/{id}")
	public String showProductDetail(Model model, @PathVariable int id) {
		logger.debug("showProductDetail() started. Product Id is \"" + id
				+ "\"");

		// get Product from DB
		model.addAttribute("product", productService.findOne(id));

		// show product detail
		return "product-detail";
	}

	/**
	 * deletes product
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.POST, params = { "delete" })
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

	/**
	 * 
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.POST, params = { "send-to-cart" })
	public String doSendToCart(Model model, @PathVariable int id,
			Principal principal, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		logger.debug("doSendToCart() started. Product Id is \"" + id + "\"");

		// get Product from DB
		Product product = productService.findOne(id);
		if (product == null) {
			model.addAttribute("message",
					"Error! Product is unavailable. Please, choose another one.");
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
		redirectAttributes.addFlashAttribute("message",
				"Product is successfully added to your cart.");
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
			// throw new WrongUrlException(e);
			// just show first
			number = -1;
		}
		return number;
	}

}
