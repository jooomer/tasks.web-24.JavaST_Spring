package ua.store.web.controller.product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.entity.Product;
import ua.store.domain.util.Util;
import ua.store.service.ProductService;

@Controller
public class DeleteProductController {

	private static final Logger logger = LogManager
			.getLogger(DeleteProductController.class);

	@Autowired
	private ProductService productService;

	/**
	 * deletes current product
	 */
	@RequestMapping(value = "/products/{idStr}", method = RequestMethod.POST, params = {"delete_product"})
	public String doDeleteProduct(
			Model model, 
			@PathVariable String idStr,
			RedirectAttributes redirectAttributes) {
		logger.debug("--- started");

		// parse id and check it
		long id = Util.parsePathVariable(idStr);
		if (id < 0) {
			logger.debug("ERROR! Wrong product id: " + idStr);
			model.addAttribute("message_alert", "ERROR! Wrong product id: " + idStr);
			return "message";
		}
		Product product = productService.findOne(id);
		if (product == null) {
			logger.debug("ERROR! Product is absent in DB. Wrong product id: " + idStr);
			model.addAttribute("message_alert", "Error! Product is unavailable. Please, choose another one.");
			return "message";
		}

		// delete product from DB by id
		productService.delete(product);
		
		model.addAttribute("message_success", "Product was successfully deleted.");

		return "message";
	}
	
}
