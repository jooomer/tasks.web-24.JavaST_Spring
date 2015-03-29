/*
 * 
 */

package ua.store.controller.common;

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

import ua.store.model.entity.Order;
import ua.store.model.entity.Product;
import ua.store.model.entity.ProductType;
import ua.store.service.ProductService;
import ua.store.service.ProductTypeService;
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
	private ProductTypeService productTypeService;

	@RequestMapping("/categories/{type}")
	public String showProductsByType(Model model, HttpServletRequest request,
			@PathVariable String type) {
		logger.debug("showProductsByType() started. Product type is \"" + type
				+ "\"");
		
		// get products list by product type
		ProductType productType = productTypeService.findByName(type);
		List<Product> products = productService
				.findAllByProductType(productType);
		
		// prepare products for view
		ProductMap productMap = new ProductMap(products);
		request.getSession().setAttribute("productMap", productMap);
		
		// show list of products
		model.addAttribute("jspPage",
				"/WEB-INF/view/administrator/products.jsp");
		return "template";
	}

	@RequestMapping("/products")
	public String showProducts(Model model, HttpServletRequest request) {
		logger.debug("showProducts() started.");
		
		// get list of all products from DB
		List<Product> products = productService.findAll();
		
		// prepare products for view 
		ProductMap productMap = new ProductMap(products);
		request.getSession().setAttribute("productMap", productMap);
		
		// show list of products
		model.addAttribute("jspPage",
				"/WEB-INF/view/administrator/products.jsp");
		return "template";
	}

	@RequestMapping("/products/{id}")
	public String showProductDetail(Model model, @PathVariable int id) {
		logger.debug("showProductDetail() started. Product Id is \"" + id
				+ "\"");
		
		// get Product from DB
		model.addAttribute("product", productService.findOne(id));
		
		// show product detail
		model.addAttribute("jspPage", "/WEB-INF/view/common/product-detail.jsp");
		return "template";
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

}
