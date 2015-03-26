package ua.store.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ua.store.model.entity.ProductType;
import ua.store.service.ProductTypeService;

@Controller
public class ProductTypesController {
	
	@Autowired
	private ProductTypeService productTypeService;
	
	/**
	 * handle request "/product-types" to show all product types
	 * get all product types from DB and prepare them for jsp
	 * call product-types.jsp
	 */
	@RequestMapping(value = "/product-types")
	public String showProductTypes(Model model) {
		model.addAttribute("productTypes", productTypeService.findAll());
		return "product-types";
		
	}
	
	/**
	 * control product types by REST 
	 */
	@RequestMapping(value = "/product-types-rest")
	public @ResponseBody ProductType showProductTypesRest(HttpServletRequest request) {
		String name = request.getParameter("name");
		ProductType productType = new ProductType();
		productType.setName(name);
		RestTemplate restTemplate = new RestTemplate();
		
		return productType;
		
	}
	
	
}
