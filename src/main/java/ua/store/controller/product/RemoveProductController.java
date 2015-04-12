package ua.store.controller.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.model.entity.Product;
import ua.store.model.entity.ProductCategory;
import ua.store.service.ProductService;
import ua.store.tag.ProductMap;

@Controller
public class RemoveProductController {

	private static final Logger logger = LogManager
			.getLogger(RemoveProductController.class);

	@Autowired
	private ProductService productService;

	/**
	 * handle request "/products/remove/{id}" to remove current product
	 */
	@RequestMapping("/products/remove/{id}")
	public String doRemoveProduct(Model model, @PathVariable int id,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		logger.debug("doRemoveProduct() started.");

		// delete product from DB by id
		Product product = productService.findOne(id);
		productService.delete(product);
		// model.addAttribute("remove", "success");

		List<Product> products = productService.findAll();
		ProductMap productMap = new ProductMap(products);
		request.getSession().setAttribute("productMap", productMap);

		// prepare message and show all products of user with message
		model.addAttribute("message",
				"Congratulations! Your product was successfully deleted.");

		model.addAttribute("jspPage",
				"/WEB-INF/view/administrator/products.jsp");
		return "template";
	}
}
