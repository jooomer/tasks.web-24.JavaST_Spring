package ua.store.controller.common;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	private static Logger logger = LogManager.getLogger(ErrorController.class);
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/error")
	public String showErrorPage(Model model, Locale locale) {
		logger.debug("--- started");
		
		String message_warning = messageSource.getMessage("error.Category_deleted", null, locale);

		model.addAttribute("message_warning", message_warning);
		return "error";
	}
}
