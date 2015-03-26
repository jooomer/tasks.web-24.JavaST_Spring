package ua.store.controller.register;

import java.security.Principal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.store.controller.common.LoginController;
import ua.store.model.entity.User;
import ua.store.service.UserService;

@Controller
public class AccountController {
	
	private static final Logger logger = LogManager.getLogger(AccountController.class);

	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User construct() {
		return new User();
	}

	/**
	 * handle request "/account"
	 * get current user from DB
	 * call account.jsp to show user detail
	 */
	@RequestMapping("/account")
	public String showAccount(Model model, Principal principal) {
		
		logger.debug("Method showAccount() started.");
		
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithProducts(name));
		model.addAttribute("jspPage", "/WEB-INF/view/common/account.jsp");
		return "template";
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doAccountUpdate(Model model, Principal principal, @ModelAttribute("user") User newUser) {
		
		logger.debug("Method doAccountUpdate() started.");

		String name = principal.getName();
		User user = userService.findOneWithProducts(name);
		
		if (!newUser.getFirstName().equals("")) {
			user.setFirstName(newUser.getFirstName());
		}
		if (!newUser.getLastName().equals("")) {
			user.setLastName(newUser.getLastName());
		}
		if (!newUser.getEmail().equals("")) {
			user.setEmail(newUser.getEmail());
		}
		if (!newUser.getPhone().equals("")) {
			user.setPhone(newUser.getPhone());
		}
		if (!newUser.getAddress().equals("")) {
			user.setAddress(newUser.getAddress());
		}
		
		System.out.println(user.getId());

		userService.update(user);
		
		model.addAttribute("user", user);
		model.addAttribute("jspPage", "/WEB-INF/view/common/account.jsp");
		return "template";
	}
	
}
