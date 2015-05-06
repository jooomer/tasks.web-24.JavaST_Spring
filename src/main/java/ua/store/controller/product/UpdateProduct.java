package ua.store.controller.product;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.Product;
import ua.store.dto.ProductDto;
import ua.store.service.CategoryService;
import ua.store.service.ProductService;
import ua.store.util.Util;

@Controller
public class UpdateProduct {

	private static Logger logger = LogManager.getLogger(UpdateProduct.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MessageSource messageSource;

	/**
	 * shows update product form
	 */
	@RequestMapping(value = "/products/{idStr}", 
					method = RequestMethod.POST, 
					params = {"update_product"})
	public String showUpdateProduct(Model model,
									@RequestParam("update_product") Long id,
									Locale locale) {
		logger.debug("--- started");
		
		String message_warning = messageSource.getMessage("error.Product_is_unavailable", null, locale);

		Product product = productService.findOne(id);
		if (product == null) {
			logger.debug("ERROR! Product is absent in DB. Wrong product id: " + id);
			model.addAttribute("message_warning", message_warning);
			return "message";
		}
		logger.debug("Product is found. Id: " + id);
		logger.debug(product.toString());
		
		ProductDto productDto = new ProductDto();
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setQuantityInStock(product.getQuantityInStock());
		productDto.setDescription(product.getDescription());
		productDto.setCategory(product.getCategory());
		productDto.setPublishedDate(product.getPublishedDate());
		
		model.addAttribute("productDto", productDto);
		model.addAttribute("product", product);
		model.addAttribute("listOfCategories", categoryService.findAll());
		
		return "product-update";
	}
	
	@RequestMapping(value = "/products/{idStr}", 
					method = RequestMethod.POST, 
					params = {"save_product"})
	public String doUpdateProduct(Model model,
								RedirectAttributes redirectAttributes,  
								@Valid @ModelAttribute("productDto") ProductDto productDto,
								BindingResult bindingResult,
								@RequestParam("save_product") Long id,
								Locale locale) {
		logger.debug("--- started");
		
		String message_warning = messageSource.getMessage("error.Product_is_unavailable", null, locale);

		Product product = productService.findOne(id);
		if (product == null) {
			logger.debug("Error. Product is not present in DB");
			model.addAttribute("message_warning", message_warning);
			return "message";
		}

		if (bindingResult.hasErrors()) {
			logger.debug("bindingResult.hasErrors() = true"
					+ "; bindingResult: " + bindingResult); 
			model.addAttribute("error", true);

			// prepare data for view 
			model.addAttribute("listOfCategories", categoryService.findAll());
			model.addAttribute("product", product);
			
			// show add-product form
			return "product-update";
		}
		
		product = productDto.getAllFields(product);
		productService.save(product);
		
		String message_success = messageSource.getMessage("products.Product_updated", null, locale);
		redirectAttributes.addFlashAttribute("message_success", message_success);
		redirectAttributes.addFlashAttribute("success", true);
		
		return "redirect:/products/" + id;
	}
}
