package ua.store.controller.common;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.model.entity.User;
import ua.store.service.UserService;

@Controller
public class RegisterController {
	
	private static final Logger logger = LogManager.getLogger(RegisterController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * prepare entity User for registration form 
	 */
	@ModelAttribute("user")
	public User construct() {
		return new User();
	}
	
	@RequestMapping("/register**")
	public String showRegisterForm() {
		logger.debug("showRegisterForm() started."); 
		return "register";
	}
	
	/**
	 * Receives user data from registration form
	 * and saves it into DB
	 */
	@RequestMapping(value = "/register**", method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result,
			RedirectAttributes redirectAttributes, Model model) {
		
		logger.debug("doRegister() started."); 

		// check validation of data from register form
		// if not valid - call register form again
		if (result.hasErrors()) {
			logger.debug("doRegister() - result.hasErrors()"); 
			model.addAttribute("error", true);
			return "register";
		}
		
		// check locale changing to avoid password confirmation checking
		// if not valid - call register form again
//		if (user == null 
//				|| user.getConfirmPassword() == null) {
//			logger.debug("doRegister() - just locale changes"); 
//			return "redirect:/register";
//		}

		// check password confirmation
		// if not valid - call register form again
//		if (user == null 
//				|| user.getPassword() == null 
//				|| user.getConfirmPassword() == null 
//				|| !user.getPassword().equals(user.getConfirmPassword())) {
//			logger.debug("doRegister() - incorrect password confirmation"); 
//			redirectAttributes.addFlashAttribute("message", "This is an error. Please, confirm your password correctly.");
//			return "redirect:/register";
//		}
		
		// save user in DB
		userService.save(user);
		
		// show greeting message
//		model.addAttribute("message", "Congratulations! You are successfully registered! Now you can sign in.");
//		return "message";

		// set attribute "success" to show success message 
		redirectAttributes.addFlashAttribute("register_success", true);
		
		// call index.jsp
		return "redirect:/";

	}

}
