package ua.store.controller.catalog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CatalogController {

	private static final Logger logger = LogManager
			.getLogger(CatalogController.class);

	/**
	 * just shows first page of products list
	 */
	@RequestMapping("/catalog")
	public String showCatalog(Model model) {
		logger.debug("--- started");

		return "redirect:category/0/products/page/1";
	}

}
