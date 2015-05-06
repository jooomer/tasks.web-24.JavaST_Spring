package ua.store.controller.product;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.Product;
import ua.store.service.ProductService;
import ua.store.util.Util;

@Controller
public class DeleteProductController {

	private static final Logger logger = LogManager
			.getLogger(DeleteProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * deletes current product
	 */
	@RequestMapping(value = "/products/{idStr}", method = RequestMethod.POST, params = {"delete_product"})
	public String doDeleteProduct(Model model, 
								@PathVariable String idStr,
								RedirectAttributes redirectAttributes,
								Locale locale) {
		logger.debug("--- started");

		String message_warning = messageSource.getMessage("error.Product_is_unavailable", null, locale);

		// parse id and check it
		long id = Util.parsePathVariable(idStr);
		if (id < 0) {
			logger.debug("ERROR! Wrong product id: " + idStr);
			model.addAttribute("message_warning", message_warning);
			return "message";
		}
		Product product = productService.findOne(id);
		if (product == null) {
			logger.debug("ERROR! Product is absent in DB. Wrong product id: " + idStr);
			model.addAttribute("message_warning", message_warning);
			return "message";
		}

		// delete product from DB by id
		productService.delete(product);
		
		String message_success = messageSource.getMessage("product.Product_created", null, locale);
		model.addAttribute("message_success", message_success);

		return "message";
	}
	
}
