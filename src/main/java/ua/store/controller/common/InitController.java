package ua.store.controller.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.store.service.InitDbService;

@Controller
public class InitController {
	
	private static final Logger logger = LogManager.getLogger(InitController.class);

	@Autowired
	InitDbService initDbService;
	
	@RequestMapping(value = "/initialization")
	public String showInitialization(Model model) {
		logger.debug("Method showInitialization() started");
		model.addAttribute("jspPage", "/WEB-INF/view/common/initialization.jsp");
		return "template";
	}

	@RequestMapping(value = "/initialization", method = RequestMethod.POST)
	public String doInitialization(Model model) {
		logger.debug("Method doInitialization() started");
		initDbService.doInit();
		
		model.addAttribute("jspPage", "/WEB-INF/view/common/main.jsp");
		return "template";
	}


}
