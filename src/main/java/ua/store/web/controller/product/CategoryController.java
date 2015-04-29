package ua.store.web.controller.product;

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

import ua.store.domain.entity.Category;
import ua.store.domain.entity.Product;
import ua.store.domain.util.Util;
import ua.store.service.CategoryService;

@Controller
public class CategoryController {
	
	private static Logger logger = LogManager.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	// prepare entity ProductType for add-product-type form
	@ModelAttribute("category")
	public Category construct() {
		return new Category();
	}

	@RequestMapping(value = "/add-category", method = RequestMethod.POST, params = {"add-category"})
	public String showAddCategory(Model model) {
		logger.debug("--- started");
		return "add-category";
	}

	@RequestMapping(value = "/add-category", method = RequestMethod.POST, params = {"do-add-category"})
	public String doAddCategory(
			@Valid @ModelAttribute("category") Category category,
			BindingResult result, Principal principal, Model model,
			RedirectAttributes redirectAttributes) {
		logger.debug("--- started");

		// check data validation from form
		// if not valid - show add-product-type form again
		if (result.hasErrors()) {
			model.addAttribute("error", true);
			return showAddCategory(model);
		}

		// save new product type in DB
		categoryService.save(category);

		// set attribute "success" to show success message
		redirectAttributes.addFlashAttribute("success", true);

		// prepare message and show all product types with message
		redirectAttributes.addFlashAttribute("message", "Congratulations! New category was successfully created.");
		
		// call add-product-type.jsp again
		return "redirect:/categories";
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
