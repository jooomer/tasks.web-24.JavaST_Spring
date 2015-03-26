package ua.store.controller.register;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.model.entity.Product;
import ua.store.model.entity.User;
import ua.store.service.ProductService;
import ua.store.service.UserService;

@Controller
@RequestMapping("/my-products")
public class MyProductsController {
	
	@Autowired
	private ProductService productService;
		
	// handle request "/my-products" to show all products of current user
	// get all user's products by user name
	// call my-products.jsp
	@RequestMapping
	public String showMyProducts(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("myProducts", productService.findAllByUserName(name));
		return "my-products";
		
	}
	
	// handle request "my-products/{strId}" to show product detail
	// strId - is a part of url getting from AddProductController.doAddproduct()
	@RequestMapping("/{strId}")
	public String detail(Model model, @PathVariable String strId) {
		
		// get clear id of product
		int id = Integer.valueOf(strId.replace("?success=true", ""));
		
		// get one product from DB by id
		Product product = productService.findOne(id	);
		
		// prepare attributes
		model.addAttribute("product", product);
		model.addAttribute("success", true);
		
		// call product-detail.jsp
		return "product-detail";
	}
	
}
