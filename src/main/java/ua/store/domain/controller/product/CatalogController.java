/*
 * 
 */

package ua.store.domain.controller.product;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.model.dto.SelectCategoryDto;
import ua.store.domain.model.dto.SelectSortByDto;
import ua.store.domain.model.entity.Order;
import ua.store.domain.model.entity.Product;
import ua.store.domain.model.entity.Category;
import ua.store.domain.util.Util;
import ua.store.service.CategoryService;
import ua.store.service.ProductService;
import ua.store.service.UserService;

@Controller
public class CatalogController {

	private static final Logger logger = LogManager
			.getLogger(CatalogController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@ModelAttribute("category")
	public SelectCategoryDto constructSelectCategoryDto() {
		return new SelectCategoryDto();
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
	 * sends product to cart from catalog
	 */
	@RequestMapping(value = "/category/{catIdStr}/products/page/{pageStr}", method = RequestMethod.POST, params = {"send_to_cart"})
	public String doSendToCart(
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			@RequestParam("send_to_cart") Integer id,
			@PathVariable String catIdStr,
			@PathVariable String pageStr) {
		logger.debug("--- started");
		
		// get Product from DB
		Product product = productService.findOne(id);
		if (product == null) {
			logger.debug("Product is not found in DB");
			model.addAttribute("message", "Error! Product is unavailable. Please, choose another one.");
			return "message";
		}
		logger.debug("Product is found in DB. Product id: " + product.getId());
		
		// get Order from session
		// if absent, then get new one
		Order order = (Order) request.getSession().getAttribute("order");
		if (order == null) {
			order = new Order();
		}
		
		// add Product to Order
		order.addProduct(product, 1);

		// save Order to session
		request.getSession().setAttribute("order", order);

		// show cart.jsp
		redirectAttributes.addFlashAttribute("message_success",
				"Product is successfully added to your cart.");

		
		
		return "redirect:/category/" + catIdStr + "/products/page/" + pageStr;
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
			@ModelAttribute("category") SelectCategoryDto selectCategoryDto,
			@ModelAttribute("selectSortBy") SelectSortByDto selectSortByDto) {
		logger.debug("--- started");
		logger.debug("URL: /category/" + catIdStr + "/products/page/" + pageStr);

		// ---------------------------------
		// handle and initialize parameters of select form
		// ---------------------------------
		HttpSession session = request.getSession();
		
		// initialize itemsOnPage
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

		// initialize sorting by direction 
		Direction direction;
		if (session.getAttribute("catalog_sortDirection") != null) {
			direction = (Direction) session.getAttribute("catalog_sortDirection");
		} else {
			direction = Direction.ASC;
			session.setAttribute("catalog_sortDirection", direction);
		}
		if (selectSortByDto != null && selectSortByDto.getSortDirection() != null) {
			direction = Direction.valueOf(selectSortByDto.getSortDirection());
			session.setAttribute("catalog_sortDirection", direction);
			System.out.println("Direction: " + direction);
		} 

		// initialize sorting by field
		String sortField;
		if (session.getAttribute("catalog_sortField") != null) {
			sortField = (String) session.getAttribute("catalog_sortField");
		} else {
			sortField = "id";
			session.setAttribute("catalog_sortField", sortField);
		}
		if (selectSortByDto != null && selectSortByDto.getSortField() != null) {
			sortField = selectSortByDto.getSortField();
			session.setAttribute("catalog_sortField", sortField);
		} 
		
		
		// initialize selected category
		if (selectCategoryDto.getCategory() != null) {
			logger.debug("Selected category: "
					+ selectCategoryDto.getCategory().getName());

			// get catId from DTO and reload page
			Long catId = getCatIdFromDto(selectCategoryDto);
			return "redirect:/category/" + catId + "/products/page/1";
		}

		// ---------------------------------
		// handle {catIdStr}
		// ---------------------------------

		// get category Id and name from URL and check Id
		int catId = (int) Util.parsePathVariable(catIdStr);
		List<Category> listOfCategories = categoryService
				.findAll();
		String categoryName = null;

		// check min/max category request
		if (catId < 0 || catId > listOfCategories.size()) {
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
			totalPages = productService.getTotalPages(itemsOnPage);
		} else {
			logger.debug("catId is between 0 and category number; catId = "
					+ catId);

			// get category name
			categoryName = listOfCategories.get(catId - 1).getName();
			totalPages = productService.getTotalPagesByCategory(categoryName, itemsOnPage);
		}

		// ---------------------------------
		// handle {pageStr}
		// ---------------------------------

		// get page number from URL
		int page = (int) Util.parsePathVariable(pageStr);

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
		model.addAttribute("listOfCategories", listOfCategories);

		// prepare list of products for view
		List<Product> listOfProducts = null;
		if (catId == 0) {
			listOfProducts = productService.findAllByPage(page - 1, itemsOnPage, direction, sortField);
		} else {
			listOfProducts = productService.findByCategoryByPage(categoryName, page - 1, itemsOnPage, direction, sortField);
		}

		// prepare some parameters
		model.addAttribute("listOfProducts", listOfProducts);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("catalog", true);

		return "catalog";
	}

	/**
	 * gets category Id that user selected
	 */
	private Long getCatIdFromDto(
			SelectCategoryDto selectCategoryDto) {
		String categoryName = selectCategoryDto
				.getCategory().getName();
		Category category = categoryService
				.findByName(categoryName);
		if (category != null) {
			return category.getId();
		} else {
			return 0L;
		}
	}


}
