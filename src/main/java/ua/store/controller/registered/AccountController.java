package ua.store.controller.registered;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.controller.common.LoginController;
import ua.store.model.entity.User;
import ua.store.service.UserService;

@Controller
//@SessionAttributes("user")
public class AccountController {

	private static final Logger logger = LogManager
			.getLogger(AccountController.class);

	@Autowired
	private UserService userService;

//	@ModelAttribute("updatedUser")
//	public User construct() {
//		return new User();
//	}

	/**
	 * call account.jsp to show user detail
	 */
	@RequestMapping("/account**")
	public String showAccount(Model model, Principal principal,
			HttpServletRequest request) {
		logger.debug("Method showAccount() started.");

		// get current user from DB
		String name = principal.getName();
//		model.addAttribute("user", userService.findByName(name));
		request.getSession().setAttribute("user", userService.findByName(name));
		
		// show user account
		return "account";
	}

	/**
	 * just shows account with update form
	 */
	@RequestMapping("/update-account**")
	public String showUpdateAccount(Model model, Principal principal,
			HttpServletRequest request) {
		logger.debug("Method showUpdateAccount() started.");
		User user = (User) request.getSession().getAttribute("user");
		user.setConfirmPassword(user.getPassword());
		System.out.println(user.toString());
		model.addAttribute("updatedUser", user);
		
		// show user account with update form
		return "account-update";
	}

	/**
	 * receives a new user account data, validates it and update to DB
	 */
	@RequestMapping(value = "/account**", method = RequestMethod.POST)
	public String doUpdateAccount(Model model, Principal principal,
			@Valid @ModelAttribute("updatedUser") User updatedUser,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		logger.debug("doUpdateAccount() started.");

		System.out.println("updatedUser: " + updatedUser.toString());

		if (result.hasErrors()) {
			logger.debug("doUpdateAccount() - result.hasErrors()"
					+ "; result: " + result.getFieldError("name")); 
			
			model.addAttribute("error", true);
			return "account-update";
		}
		
		
//		if (newUser == null) {
//			redirectAttributes.addFlashAttribute("newUser", new User());
//			return "redirect:/account";
//		}
		
		// get current user from DB
		String name = principal.getName();
		User user = userService.findByName(name);

		// check fields filling
//		if (updatedUser.getFirstName() != null && !updatedUser.getFirstName().equals("")) {
//			user.setFirstName(updatedUser.getFirstName());
//		}
//		if (updatedUser.getLastName() != null && !updatedUser.getLastName().equals("")) {
//			user.setLastName(updatedUser.getLastName());
//		}
//		if (updatedUser.getEmail() != null && !updatedUser.getEmail().equals("")) {
//			user.setEmail(updatedUser.getEmail());
//		}
//		if (updatedUser.getPhone() != null && !updatedUser.getPhone().equals("")) {
//			user.setPhone(updatedUser.getPhone());
//		}
//		if (updatedUser.getAddress() != null && !updatedUser.getAddress().equals("")) {
//			user.setAddress(updatedUser.getAddress());
//		}

		// update current user with new fields in DB
		userService.update(user);

		// show user account
		model.addAttribute("user", user);
		return "account";
	}

}
