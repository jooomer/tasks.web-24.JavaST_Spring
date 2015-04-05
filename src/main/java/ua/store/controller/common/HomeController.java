package ua.store.controller.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
// @SessionAttributes("user")
public class HomeController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	/*
	 * handle request "/"
	 * call template.jsp to show home page
	 */
	@RequestMapping("/**")
	public String viewMainPage() {
		logger.debug("viewMainPage() started."); 
		return "index";
	}
	
	
}
