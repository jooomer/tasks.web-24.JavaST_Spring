package ua.store.controller.admin;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.model.entity.ProductType;
import ua.store.service.ProductTypeService;

@Controller
public class ProductTypesController {
	
	@Autowired
	private ProductTypeService productTypeService;
	
	// prepare entity ProductType for add-product-type form
	@ModelAttribute("productType")
	public ProductType construct() {
		return new ProductType();
	}

	@RequestMapping(value = "/add-product-type")
	public String showAddProductType(Model model) {
		return "add-product-type";
	}

	@RequestMapping(value = "/add-product-type", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/product-types")
	public String showProductTypes(Model model) {
		model.addAttribute("productTypes", productTypeService.findAll());
		return "product-types";
		
	}
		
	@RequestMapping(value = "/product-types/delete/{id}")
	public String doDeleteProductType(Model model, @PathVariable int id, RedirectAttributes redirectAttributes) {
		
		// delete product type from DB by id
		ProductType productType = productTypeService.findOne(id);
		productTypeService.delete(productType);
		
		// prepare message and show all product types with message
		redirectAttributes.addFlashAttribute("message", "Congratulations! Product type was successfully deleted.");
		return "redirect:/product-types";
	}

	
}
