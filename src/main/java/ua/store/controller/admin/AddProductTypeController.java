package ua.store.controller.admin;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.model.entity.Product;
import ua.store.model.entity.ProductType;
import ua.store.model.entity.User;
import ua.store.repository.ProductRepository;
import ua.store.service.ProductService;
import ua.store.service.ProductTypeService;
import ua.store.service.UserService;

@Controller
@RequestMapping("/add-product-type")
public class AddProductTypeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductTypeService productTypeService;

	@Autowired
	private UserService userService;

	// prepare entity ProductType for add-product-type form
	@ModelAttribute("productType")
	public ProductType construct() {
		return new ProductType();
	}

	// handle request "/add-product-type" to show add-product-type form
	// call add-product-type.jsp
	@RequestMapping
	public String showAddProductType(Model model) {
		return "add-product-type";

	}

	// handle request "/add-product-type" with data from add-product-type form
	@RequestMapping(method = RequestMethod.POST)
	public String doAddProductType(
			@Valid @ModelAttribute("productType") ProductType productType,
			BindingResult result, Principal principal, Model model,
			RedirectAttributes redirectAttributes) {

		// check data validation from form
		// if not valid - show add-product-type form again
		if (result.hasErrors()) {
			return showAddProductType(model);
		}

		// save new product type in DB
		productTypeService.save(productType);

		// set attribute "success" to show success message
		redirectAttributes.addFlashAttribute("success", true);

		// prepare message and show all product types with message
		redirectAttributes.addFlashAttribute("message", "Congratulations! New product type was successfully created.");
		
		// call add-product-type.jsp again
		return "redirect:/add-product-type";

	}

}
