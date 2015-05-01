package ua.store.controller.category;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.Category;
import ua.store.domain.Product;
import ua.store.service.CategoryService;
import ua.store.util.Util;

@Controller
public class CategoriesController {
	
	private static Logger logger = LogManager.getLogger(CategoriesController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	// prepare entity ProductType for add-product-type form
	@ModelAttribute("category")
	public Category construct() {
		return new Category();
	}
	
	@RequestMapping(value = "/categories")
	public String showCategories(Model model) {
		logger.debug("--- started");
		model.addAttribute("categories", categoryService.findAll());
		return "categories";
		
	}
		
	@RequestMapping(value = "/categories/{id}/delete", method = RequestMethod.GET)
	public String showPopup(
			Model model, 
			@PathVariable Long id) {
		logger.debug("--- started");
		
		model.addAttribute("id", id);
		model.addAttribute("action", "delete_category");

		System.out.println(id);
		
		return "popup_delete";
	}

	@RequestMapping(value = {"/categories", "/categories/{id}/delete"}, 
					method = RequestMethod.POST, 
					params = {"delete_category"})
	public String doDeleteCategory(
			Model model, 
			@RequestParam("delete_category") Long id,
			RedirectAttributes redirectAttributes) {
		logger.debug("--- started");
		
		// delete product type from DB by id
		categoryService.delete(id);
		
		// prepare message and show all product types with message
		redirectAttributes.addFlashAttribute("message", "Congratulations! Category was successfully deleted.");

		return "redirect:/categories";
	}

	@RequestMapping(value = "/categories/{idStr}/update")
	public String showUpdateCategory(
			Model model,
			@PathVariable String idStr) {
		logger.debug("--- started");
		
		// parse id and check it
		long id = Util.parsePathVariable(idStr);
		if (id < 0) {
			logger.debug("ERROR! Wrong category id: " + idStr);
			model.addAttribute("message_alert", "ERROR! Wrong category id: " + idStr);
			return "message";
		}
		Category category = categoryService.findOne(id);
		if (category == null) {
			logger.debug("ERROR! Category is absent in DB. Wrong category id: " + idStr);
			model.addAttribute("message_alert", "Error! Category is unavailable.");
			return "message";
		}
		logger.debug("Category is found. Id: " + id);
		
		model.addAttribute("category", category);
		
		return "update-category";
	}
	
	@RequestMapping(value = "/categories/{idStr}/update", 
					method = RequestMethod.POST,
					params = {"do-update-category"})
	public String doUpdateCategory(
			Model model,
			RedirectAttributes redirectAttributes,
			@ModelAttribute("do-update-category") Long id,
			@Valid @ModelAttribute("category") Category newCategory,
			@PathVariable String idStr) {
		logger.debug("--- started");
		
		Category category = categoryService.findOne(id);
		if (category == null) {
			logger.debug("ERROR! Category is absent in DB. Wrong category id: " + idStr);
			model.addAttribute("message_alert", "Error! Category is unavailable.");
			return "message";
		}
		logger.debug("Category is found. Id: " + id);

		category.setName(newCategory.getName());
		categoryService.save(category);
		redirectAttributes.addFlashAttribute("message_success", "Category is successfully updated.");

		return "redirect:/categories";
	}

}