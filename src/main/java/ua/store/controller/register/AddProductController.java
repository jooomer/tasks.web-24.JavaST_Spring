package ua.store.controller.register;

import java.security.Principal;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

//import jdk.nashorn.internal.objects.annotations.Constructor;




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

@Controller
@RequestMapping("/add-product")
public class AddProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private UserService userService;
	
	// prepare entity Product for add-product form
	@ModelAttribute("product")
	public Product construct() {
		return new Product();
	}
		
	// handle request "/add-product" to show add-product form
	// get all product types from DB and prepare them for jsp
	// call add-product.jsp
	@RequestMapping
	public String showAddProduct(Model model) {
		model.addAttribute("listOfProductTypes", productTypeService.findAll());
		return "add-product";
		
	}
	
	// handle request "/add-product" with data from add-product form
	@RequestMapping(method = RequestMethod.POST)
	public String doAddProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Principal principal, Model model) {

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
		
		// show product detail by id 
		// set parameter "success" to show message in a product detail page
		return "redirect:/my-products/" + id + "?success=true";
	}
	
}
