/*
 * 
 */

package ua.store.controller.admin;

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

import ua.store.model.entity.Product;
import ua.store.model.entity.ProductType;
import ua.store.service.ProductService;
import ua.store.service.ProductTypeService;
import ua.store.tag.ProductMap;

@Controller
public class ProductsController {

	private static final Logger logger = LogManager
			.getLogger(ProductsController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductTypeService productTypeService;

	/**
	 * handle request "/products" to show all products get all products from DB
	 * and prepare them for jsp call products.jsp
	 */
	@RequestMapping("/products")
	public String showProducts(Model model, HttpServletRequest request) {
		logger.debug("showProducts() started.");
		List<Product> products = productService.findAll();
		ProductMap productMap = new ProductMap(products);
		request.getSession().setAttribute("productMap", productMap);
		model.addAttribute("jspPage",
				"/WEB-INF/view/administrator/products.jsp");
		return "template";
	}

	@RequestMapping("/product-types/{type}")
	public String showProductsByType(Model model, HttpServletRequest request,
			@PathVariable String type) {
		logger.debug("showProductsByType() started. Product type is \"" + type
				+ "\"");
		ProductType productType = productTypeService.findByName(type);
		List<Product> products = productService
				.findAllByProductType(productType);
		ProductMap productMap = new ProductMap(products);
		request.getSession().setAttribute("productMap", productMap);
		model.addAttribute("jspPage",
				"/WEB-INF/view/administrator/products.jsp");
		return "template";
	}

	/**
	 * show product detail get product by id
	 * and prepare it for jsp call product-detail.jsp
	 */
	@RequestMapping("/products/{id}")
	public String showProductDetail(Model model, @PathVariable int id) {
		logger.debug("showProductDetail() started. Product Id is \"" + id
				+ "\"");
		model.addAttribute("product", productService.findOne(id));
		model.addAttribute("jspPage", "/WEB-INF/view/common/product-detail.jsp");
		return "template";
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.POST, params = {"delete"})
	public String doUpdateProduct(Model model, @PathVariable int id,
			RedirectAttributes redirectAttributes) {
		logger.debug("doUpdateProduct() started. Product Id is \"" + id + "\"");
		productService.delete(id);
		redirectAttributes.addFlashAttribute("message",
				"Congratulations! You've just successfully deleted a product.");
		
		// call products.jsp again
		return "redirect:/products";
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.POST, params = {"send-to-cart"})
	public String doSendToCart(Model model, @PathVariable int id, Principal principal,
			RedirectAttributes redirectAttributes) {
		logger.debug("doSendToCart() started. Product Id is \"" + id + "\"");
		if (principal != null) {
			System.out.println("User name: " + principal.getName());
		} else {
			System.out.println("principal == null");
		}
		
//		redirectAttributes.addFlashAttribute("message",
//				"Congratulations! You've just successfully deleted a product.");
		
		// call products.jsp again
		return "redirect:/products";
	}

}
