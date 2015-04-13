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

@Controller
public class ProductController {

	private static final Logger logger = LogManager
			.getLogger(ProductController.class);

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
	 * sends product to cart from product detail
	 */
	@RequestMapping(value = "/products/{idStr}", method = RequestMethod.POST, params = { "send_to_cart" })
	public String doSendToCart(Model model, @PathVariable String idStr,
			Principal principal, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		logger.debug("doSendToCart() started. Product Id is \"" + idStr + "\"");

		// get product id from URL
		int id = parsePathVariable(idStr);
		if (id < 0) {
			logger.debug("ERROR! Wrong product id: " + idStr);
			return "error-page";
		}
				
		// get Product from DB
		Product product = productService.findOne(id);
		if (product == null) {
			model.addAttribute("message", "Error! Product is unavailable. Please, choose another one.");
			return "message";
		}

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
		redirectAttributes.addFlashAttribute("message",
				"Product is successfully added to your cart.");
		return "redirect:/products/" + id;
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
