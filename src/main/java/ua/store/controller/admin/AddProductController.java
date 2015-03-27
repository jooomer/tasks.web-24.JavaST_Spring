package ua.store.controller.admin;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//import jdk.nashorn.internal.objects.annotations.Constructor;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.store.model.entity.Product;
import ua.store.model.entity.User;
import ua.store.repository.ProductRepository;
import ua.store.service.ProductService;
import ua.store.service.ProductTypeService;
import ua.store.service.UserService;
import ua.store.tag.ProductMap;

@Controller
@RequestMapping("/add-product")
public class AddProductController {

	private static final Logger logger = LogManager
			.getLogger(AddProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductTypeService productTypeService;

	@Autowired
	private UserService userService;

	/**
	 * prepare entity Product for add-product form
	 */
	@ModelAttribute("product")
	public Product construct() {
		return new Product();
	}

	/**
	 * handle request "/add-product" to show add-product form get all product
	 * types from DB and prepare them for jsp call add-product.jsp
	 */
	@RequestMapping
	public String showAddProduct(Model model) {

		logger.debug("showAddProduct() started.");

		model.addAttribute("listOfProductTypes", productTypeService.findAll());

		model.addAttribute("jspPage",
				"/WEB-INF/view/administrator/add-product.jsp");
		return "template";

	}

	/**
	 * handle request "/add-product" with data from add-product form
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String doAddProduct(
			@Valid @ModelAttribute("product") Product product,
			BindingResult result, Principal principal, Model model, HttpServletRequest request) {

		logger.debug("doAddProduct() started.");

		// check data validation from form
		// if not valid - show add-product form again
		if (result.hasErrors()) {
			return showAddProduct(model);
		}

		// set name of user and current date to new product
		String name = principal.getName();
		product.setPublishedDate(new Date());

		// save new product in DB
		productService.save(product, name);

		// get this produat from DB to get its id
		product = productService.findOneByName(product.getName());
		int id = product.getId();

		// prepare products for display
		List<Product> products = productService.findAll();
		ProductMap productMap = new ProductMap(products);
		request.getSession().setAttribute("productMap", productMap);

		// show product detail by id
		// set parameter "success" to show message in a product detail page

		model.addAttribute("jspPage",
				"/WEB-INF/view/administrator/products.jsp");
		return "template";

		// return "redirect:/my-products/" + id + "?success=true";
	}

}
