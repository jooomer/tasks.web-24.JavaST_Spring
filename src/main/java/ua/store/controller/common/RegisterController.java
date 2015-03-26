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
@RequestMapping("/register")
public class RegisterController {
	
	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * prepare entity User for registration form
	 */
	@ModelAttribute("user")
	public User construct() {
		return new User();
	}
	
	/**
	 * handle request "/register"
	 * call register.jsp to show register form
	 */
	@RequestMapping
	public String showRegister(Model model) {
		
		logger.debug("showRegister() started."); 
		
		model.addAttribute("jspPage", "/WEB-INF/view/common/register.jsp");
		return "template";
	}
	
	/**
	 * handle request "/register" with data from register form
	 * Entity User consists all data from register form
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String doRegister(Model model, @Valid @ModelAttribute("user") User user, BindingResult result) {
		
		logger.debug("doRegister() started."); 

		// check validation of data from register form
		// if not valid - call register form again
		if (result.hasErrors()) {
			logger.debug("doRegister() - result.hasErrors()"); 
			model.addAttribute("message", "Sorry. This username is already exists.");
			model.addAttribute("jspPage", "/WEB-INF/view/common/register.jsp");
			return "template";
		}
		
		// save user in DB
		userService.save(user);
		
		// show greeting message
		model.addAttribute("message", "Congratulations! You are successfully registered! Now you can sign in.");
		model.addAttribute("jspPage", "/WEB-INF/view/common/message.jsp");
		return "template";

	}

}
