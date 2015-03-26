package ua.store.controller.register;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.model.entity.Product;
import ua.store.service.ProductService;

@Controller
@RequestMapping("/my-products/remove/{id}")
public class RemoveProductController {

	@Autowired
	private ProductService productService;
	
	// handle request "/my-products/remove/{id}" to remove current product
	@RequestMapping
	public String doRemove(Model model, @PathVariable int id, RedirectAttributes redirectAttributes) {
		
		// delete product from DB by id
		Product product = productService.findOne(id);
		productService.delete(product);
//		model.addAttribute("remove", "success");
		
		// prepare message and show all products of user with message
		redirectAttributes.addFlashAttribute("message", "Congratulations! Your product was successfully deleted.");
		return "redirect:/my-products";
//		return "redirect:/my-products?success=true";
	}
}
