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

	@RequestMapping(value = "/add-product")
	public String showAddProduct(Model model) {
		logger.debug("showAddProduct() started.");

		// get all product types from DB and prepare them for view
		model.addAttribute("listOfProductTypes", productTypeService.findAll());

		// show add-product form
		model.addAttribute("jspPage",
				"/WEB-INF/view/administrator/add-product.jsp");
		return "template";
	}

	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public String doAddProduct(
			@Valid @ModelAttribute("product") Product product,
			BindingResult result, Principal principal, Model model,
			HttpServletRequest request) {
		logger.debug("doAddProduct() started.");

		// check data validation from form
		// if not valid - show add-product form again
		if (result.hasErrors()) {
			return showAddProduct(model);
		}

		// set name of user and current date to new product
		product.setPublishedDate(new Date());

		// save new product in DB
		productService.save(product);

		// get this product from DB to get its id
		product = productService.findOneByName(product.getName());
		int id = product.getId();

		// prepare products for display
		List<Product> products = productService.findAll();
		ProductMap productMap = new ProductMap(products);
		request.getSession().setAttribute("productMap", productMap);

		// show products list
		model.addAttribute("jspPage",
				"/WEB-INF/view/administrator/products.jsp");
		return "template";
	}

}
