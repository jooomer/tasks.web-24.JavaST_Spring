package ua.store.controller.category;

import java.security.Principal;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.Category;
import ua.store.service.CategoryService;

@Controller
public class AddCategoryController {
	
	private static Logger logger = LogManager.getLogger(AddCategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MessageSource messageSource;

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
	public String doAddCategory(@Valid @ModelAttribute("category") Category category,
								BindingResult result, 
								Principal principal, 
								Model model,
								RedirectAttributes redirectAttributes,
								Locale locale) {
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
		String message_success = messageSource.getMessage("category.New_category_created", null, locale);
		redirectAttributes.addFlashAttribute("message_success", message_success);
		
		// call add-product-type.jsp again
		return "redirect:/categories";
	}
}
