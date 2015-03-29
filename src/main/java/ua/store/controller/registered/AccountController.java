package ua.store.controller.registered;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

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

	private static final Logger logger = LogManager
			.getLogger(AccountController.class);

	@Autowired
	private UserService userService;

	@ModelAttribute("newUser")
	public User construct() {
		return new User();
	}

	/**
	 * call account.jsp to show user detail
	 */
	@RequestMapping("/account")
	public String showAccount(Model model, Principal principal,
			HttpServletRequest request) {
		logger.debug("Method showAccount() started.");

		// get current user from DB
		String name = principal.getName();
//		model.addAttribute("user", userService.findByName(name));
		request.getSession().setAttribute("user", userService.findByName(name));
		
		// show user account
		model.addAttribute("jspPage", "/WEB-INF/view/registered/account.jsp");
		return "template";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doAccountUpdate(Model model, Principal principal,
			@ModelAttribute("newUser") User newUser) {
		logger.debug("Method doAccountUpdate() started.");

		// get current user from DB
		String name = principal.getName();
		User user = userService.findOneWithCarts(name);

		// check fields filling
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

		// update current user with new fields in DB
		userService.update(user);

		// show user account
		model.addAttribute("user", user);
		model.addAttribute("jspPage", "/WEB-INF/view/registered/account.jsp");
		return "template";
	}

}
