package ua.store.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.model.entity.Product;
import ua.store.model.entity.ProductType;
import ua.store.service.ProductService;
import ua.store.service.ProductTypeService;

@Controller
@RequestMapping("/product-types/delete/{id}")
public class DeleteProductTypeController {

	@Autowired
	private ProductTypeService productTypeService;
	
	// handle request "/product-types/delete/{id}" to delete current product type
	@RequestMapping
	public String doRemove(Model model, @PathVariable int id, RedirectAttributes redirectAttributes) {
		
		// delete product type from DB by id
		ProductType productType = productTypeService.findOne(id);
		productTypeService.delete(productType);
		
		// prepare message and show all product types with message
		redirectAttributes.addFlashAttribute("message", "Congratulations! Product type was successfully deleted.");
		return "redirect:/product-types";
	}
}
