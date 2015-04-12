package ua.store.controller.product;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.model.dto.AddProductDto;
import ua.store.model.entity.Product;
import ua.store.model.entity.User;
import ua.store.repository.ProductRepository;
import ua.store.service.ProductService;
import ua.store.service.ProductCategoryService;
import ua.store.service.UserService;
import ua.store.tag.ProductMap;

@Controller
public class AddProductController {

	private static final Logger logger = LogManager
			.getLogger(AddProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService productCategoryService;

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
		logger.debug("--- started");

		// get all product types from DB and prepare them for view
		model.addAttribute("listOfProductCategories", productCategoryService.findAll());

		// prepare product DTO
		model.addAttribute("addProductDto", new AddProductDto());
		
		// show add-product form
		return "add-product";
	}

	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public String doAddProduct(
			@Valid @ModelAttribute("addProductDto") AddProductDto addProductDto,
			BindingResult bindingResult, Principal principal, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		logger.debug("doAddProduct() started.");

		// check data validation from form
		// if not valid - show add-product form again
		if (bindingResult.hasErrors()) {
			logger.debug("bindingResult.hasErrors() = true"
					+ "; bindingResult: " + bindingResult); 
			model.addAttribute("error", true);
			return "add-product";
		}
		
		// create a new product with the received fields
		Product product = addProductDto.getAllFields(new Product());
		
		// set name of user and current date to new product
		product.setPublishedDate(new Date());

		// save new product in DB
		productService.save(product);

//		model.addAttribute("success", true);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:products/" + product.getId();
	}

}
