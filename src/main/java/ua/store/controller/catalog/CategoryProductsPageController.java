package ua.store.controller.catalog;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.Category;
import ua.store.domain.Order;
import ua.store.domain.Product;
import ua.store.dto.SelectCategoryDto;
import ua.store.service.CategoryService;
import ua.store.service.ProductService;
import ua.store.service.view.CatalogSelectFormHandler;
import ua.store.util.Util;

@Controller
public class CategoryProductsPageController {

	private static final Logger logger = LogManager
			.getLogger(CategoryProductsPageController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private HttpSession session;

	@ModelAttribute("category")
	public SelectCategoryDto constructSelectCategoryDto() {
		return new SelectCategoryDto();
	}
	
	@ModelAttribute("catalogSelectFormHandler")
	public CatalogSelectFormHandler constructCatalogSelectFormHandler() {
		if (session.getAttribute("catalogSelectFormHandler") != null) {
			return (CatalogSelectFormHandler) session.getAttribute("catalogSelectFormHandler");
		} else {
			return new CatalogSelectFormHandler();
		}
	}

	/**
	 * sends product to cart from catalog
	 */
	@RequestMapping(value = "/category/{catIdStr}/products/page/{pageStr}", 
					method = RequestMethod.POST, 
					params = {"send_to_cart"})
	public String doSendToCart(
			Model model,
			Locale locale,
			RedirectAttributes redirectAttributes,
			@RequestParam("send_to_cart") Long id,
			@PathVariable String catIdStr,
			@PathVariable String pageStr) {
		logger.debug("--- started");
		
		// get Product from DB
		Product product = productService.findOne(id);
		if (product == null) {
			logger.debug("Product is not found in DB");
			String message_warning = messageSource.getMessage("error.Product_is_unavailable", null, locale);
			model.addAttribute("message_warning", message_warning);
			return "message";
		}
		logger.debug("Product is found in DB. Product id: " + product.getId());
		
		// get Order from session
		// if absent, then get new one
		Order order = (Order) session.getAttribute("order");
		if (order == null) {
			order = new Order();
		}
		
		// add Product to Order
		order.addProduct(product, 1);

		// save Order to session
		session.setAttribute("order", order);

		String message_success = messageSource.getMessage("cart.Product_is_added_to_cart", null, locale);
		redirectAttributes.addFlashAttribute("message_success", message_success);
		
		return "redirect:/category/" + catIdStr + "/products/page/" + pageStr;
	}
		
	/**
	 * shows list of products by selected category (page-by-page)
	 */
	@RequestMapping("/category/{catIdStr}/products/page/{pageStr}")
	public String showProductsByCategory(
			Model model,
			RedirectAttributes redirectAttributes,
			@PathVariable String catIdStr,
			@PathVariable String pageStr,
			@ModelAttribute("category") SelectCategoryDto selectCategoryDto,
			@ModelAttribute("catalogSelectFormHandler") CatalogSelectFormHandler catalogSelectFormHandler) {
		logger.debug("--- started");
		logger.debug("URL: /category/" + catIdStr + "/products/page/" + pageStr);

		// ---------------------------------
		// handle and initialize parameters of select form
		// ---------------------------------
		if (catalogSelectFormHandler == null) {
			catalogSelectFormHandler = new CatalogSelectFormHandler();
		} 

		// initialize sort fields
		Direction direction = catalogSelectFormHandler.getSortDirection();
		int itemsOnPage = catalogSelectFormHandler.getItemsOnPage();
		String sortField = catalogSelectFormHandler.getSortField();
		session.setAttribute("catalogSelectFormHandler", catalogSelectFormHandler);
		
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
	private Long getCatIdFromDto(SelectCategoryDto selectCategoryDto) {
		String categoryName = selectCategoryDto.getCategory().getName();
		Category category = categoryService.findByName(categoryName);
		if (category != null) {
			return category.getId();
		} else {
			return 0L;
		}
	}


}
