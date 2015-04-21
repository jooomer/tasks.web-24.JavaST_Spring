package ua.store.domain.controller.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LanguageController {
	
	private static final Logger logger = LogManager.getLogger(LanguageController.class);
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String doSetLanguageGet(Model model) {
		
		logger.debug("doSetLanguage() started");

		model.addAttribute("message", "------------ Language");
		model.addAttribute("jspPage", "/WEB-INF/view/common/test.jsp");
		return "template";
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST, params = {"language"})
	public String doSetLanguagePost(Model model) {
		
		logger.debug("doSetLanguage() started");

		model.addAttribute("message", "------------ Language");
		model.addAttribute("jspPage", "/WEB-INF/view/common/test.jsp");
		return "template";
	}
}
