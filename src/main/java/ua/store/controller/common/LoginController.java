package ua.store.controller.common;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	
	/**
	 * handle request "/login"
	 * call login.jsp to show login form
	 */
	@RequestMapping
	public String login(Model model) {
		
		logger.debug("login() started."); 
		
		model.addAttribute("jspPage", "/WEB-INF/view/common/login.jsp");
		return "template";
	}
	
	/**
	 * handle request "/login" 
	 * call main.jsp 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(Model model) {
		
		logger.debug("doLogin() started."); 
		
		model.addAttribute("jspPage", "/WEB-INF/view/common/main.jsp");
		return "template";
	}
}
