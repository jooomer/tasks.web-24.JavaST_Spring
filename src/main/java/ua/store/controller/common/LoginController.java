package ua.store.controller.common;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		logger.debug("showLoginForm() started."); 
		
		// check login error 
		if (error != null) {
			redirectAttributes.addFlashAttribute("login_error", true);
			return "redirect:/login";
		}
		
		// check login success
		if (success != null) {
			redirectAttributes.addFlashAttribute("login_success", true);
			return "redirect:/";
		}
		
		// refresh login page
		return "login";
	}
	
}
