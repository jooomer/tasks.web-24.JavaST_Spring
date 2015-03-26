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
@SessionAttributes("user")
@RequestMapping("/")
public class HomeController {
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	/*
	 * handle request "/"
	 * call template.jsp to show home page
	 */
	@RequestMapping
	public String viewMainPage(Model model) {
		
		logger.debug("viewMainPage() started."); 
		
		model.addAttribute("jspPage", "/WEB-INF/view/common/main.jsp");
		return "template";
	}
	
	
}
