/*
 * 
 */

package ua.store.controller.product;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.Order;
import ua.store.domain.Product;
import ua.store.dto.SelectCategoryDto;
import ua.store.dto.SelectSortByDto;
import ua.store.service.CategoryService;
import ua.store.service.ProductService;
import ua.store.service.UserService;
import ua.store.util.Util;

@Controller
public class ProductController {

	private static final Logger logger = LogManager
			.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@ModelAttribute("category")
	public SelectCategoryDto constructSelectProductCategoryDto() {
		return new SelectCategoryDto();
	}

	@ModelAttribute("selectOrderBy")
	public SelectSortByDto constructSelectSortByDto() {
		return new SelectSortByDto();
	}

	/**
	 * shows product detail
	 */
	@RequestMapping("/products/{idStr}")
	public String showProductDetail(
			Model model, 
			@PathVariable String idStr) {
		logger.debug("showProductDetail() started. Product Id is \"" + idStr
				+ "\"");

		// parse id and check it
		long id = Util.parsePathVariable(idStr);
		if (id < 0) {
			logger.debug("ERROR! Wrong product id: " + idStr);
			model.addAttribute("message_warning", "ERROR! Product is unavailable. Please, choose another one.");
			return "message";
		}
		Product product = productService.findOne(id);
		if (product == null) {
			model.addAttribute("message_warning", "Error! Product is unavailable. Please, choose another one.");
			return "message";
		}
		logger.debug("Product is found. Id: " + id);

		// get Product from DB
		model.addAttribute("product", product);

		// show product detail
		return "product-detail";
	}

	/**
	 * sends product to cart from product detail
	 */
	@RequestMapping(value = "/products/{idStr}", 
					method = RequestMethod.POST, 
					params = { "send_to_cart" })
	public String doSendToCart(
			Model model, 
			@PathVariable String idStr,
			Principal principal, 
			RedirectAttributes redirectAttributes,
			HttpSession session) {
		logger.debug("--- started. Product Id is \"" + idStr + "\"");

		// parse id and check it
		long id = Util.parsePathVariable(idStr);
		if (id < 0) {
			logger.debug("ERROR! Wrong product id: " + idStr);
			model.addAttribute("message", "ERROR! Wrong product id: " + idStr);
			return "message";
		}
		Product product = productService.findOne(id);
		if (product == null) {
			model.addAttribute("message", "Error! Product is unavailable. Please, choose another one.");
			return "message";
		}
		logger.debug("Product is found. Id: " + id);

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

		redirectAttributes.addFlashAttribute("message",
				"Product is successfully added to your cart.");
		return "redirect:/products/" + id;
	}

}