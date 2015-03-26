/*
 * 
 */

package ua.store.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.store.model.entity.Product;
import ua.store.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductService productService;
		
	// handle request "/products" to show all products
	// get all products from DB and prepare them for jsp
	// call products.jsp
	@RequestMapping
	public String users(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		return "products";
		
	}
	
	// handle request "/products/{id}" to show product detail
	// get product by id and prepare it for jsp
	// call product-detail.jsp
	@RequestMapping(value = "/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("product", productService.findOne(id));
		return "product-detail";
	}
	
}
