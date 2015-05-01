package ua.store.controller.user;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.Role;
import ua.store.domain.User;
import ua.store.dto.UserRegisterDto;
import ua.store.service.RoleService;
import ua.store.service.UserService;

@Controller
public class RegisterController {

	private static final Logger logger = LogManager
			.getLogger(RegisterController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/**
	 * prepare entity User for registration form
	 */
	@ModelAttribute("userRegisterDto")
	public UserRegisterDto construct() {
		return new UserRegisterDto();
	}

	@RequestMapping("/register**")
	public String showRegisterForm() {
		logger.debug("showRegisterForm() started.");
		return "register";
	}

	/**
	 * Receives user data from registration form and saves it into DB
	 */
	@RequestMapping(value = "/register**", 
					method = RequestMethod.POST, 
					params = {"do_register"})
	public String doRegister(
			Model model,
			RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute("userRegisterDto") UserRegisterDto userRegisterDto,
			BindingResult bindingResult) {

		logger.debug("doRegister() started.");

		// check validation of data from register form
		// if not valid - call register form again
		if (bindingResult.hasErrors()) {
			logger.debug("bindingResult.hasErrors() = true"
					+ "; bindingResult: " + bindingResult);
			model.addAttribute("error", true);
			return "register";
		}

		// create and save new user 
		userService.createAndSaveNewUser(userRegisterDto);
		logger.debug("createAndSaveNewUser(userRegisterDto) is done");

		// set attribute "success" to show success message
		redirectAttributes.addFlashAttribute("register_success", true);

		// call home page
		return "redirect:/";

	}

}
