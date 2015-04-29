package ua.store.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	
	/**
	 * show login form and check result 
	 */
	@RequestMapping(value = "/login**")
	public String showLoginForm(RedirectAttributes redirectAttributes,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "success", required = false) String success) {
		logger.debug("--- started."); 
		
		// check login error 
		if (error != null) {
			logger.debug("login_error = true"); 
			redirectAttributes.addFlashAttribute("login_error", true);
			return "redirect:/login";
		}
		
		// check login success
		if (success != null) {
			logger.debug("login_success = true"); 
			redirectAttributes.addFlashAttribute("login_success", true);
			return "redirect:/";
		}
		
		// refresh login page
		logger.debug("jast refresh login page"); 
		return "login";
	}
	
}