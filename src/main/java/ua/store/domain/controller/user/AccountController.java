package ua.store.domain.controller.user;

import java.security.Principal;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.store.domain.model.dto.UserAccountDto;
import ua.store.domain.model.entity.User;
import ua.store.service.UserService;

@Controller
@SessionAttributes("user")
public class AccountController {

	private static final Logger logger = LogManager
			.getLogger(AccountController.class);

	@Autowired
	private UserService userService;

	// @ModelAttribute("updatedUser")
	// public User construct() {
	// return new User();
	// }

	/**
	 * call account.jsp to show user detail
	 */
	@RequestMapping("/account**")
	public String showAccount(Model model, Principal principal) {
		logger.debug("--- started");

		// get current user from DB
		String name = principal.getName();
		User user = userService.findByName(name);
		model.addAttribute("user", user);
		logger.debug("Show account of user: " + user.toString());

		// show user account
		return "account";
	}

	/**
	 * just shows account with update form
	 */
	@RequestMapping(value = {"/account**", "/users/{idStr}"}, method = RequestMethod.POST, params = {"update_account"})
	public String showUpdateAccount(
			Model model, 
			@RequestParam("update_account") Long id) {
		logger.debug("--- started");

		User user = userService.findOne(id);
		logger.debug(user.toString());

		UserAccountDto userAccountDto = new UserAccountDto();
		userAccountDto.setFirstName(user.getFirstName());
		userAccountDto.setLastName(user.getLastName());
		userAccountDto.setEmail(user.getEmail());
		userAccountDto.setPhone(user.getPhone());
		userAccountDto.setAddress(user.getAddress());

		model.addAttribute("userAccountDto", userAccountDto);
		model.addAttribute("user", user);

		// show user account with update form
		return "account-update";
	}

	/**
	 * receives a new account data, validates it and update to DB
	 */
	@RequestMapping(value = {"/account**", "/users/{idStr}"}, method = RequestMethod.POST)
	public String doUpdateAccount(
			Model model,
			@Valid @ModelAttribute("userAccountDto") UserAccountDto userAccountDto,
			BindingResult bindingResult, 
			@RequestParam("save_account") Long id) {
		logger.debug("--- started");
		logger.debug(userAccountDto.toString());

		if (bindingResult.hasErrors()) {
			logger.debug("bindingResult.hasErrors() = true"
					+ "; bindingResult: " + bindingResult);
			model.addAttribute("error", true);
			return "account-update";
		}

		User user = userService.findOne(id);
		user.setFirstName(userAccountDto.getFirstName());
		user.setLastName(userAccountDto.getLastName());
		user.setEmail(userAccountDto.getEmail());
		user.setPhone(userAccountDto.getPhone());
		user.setAddress(userAccountDto.getAddress());
		user.setInBlackList(userAccountDto.isInBlackList());
		user.setEnabled(userAccountDto.isEnabled());
		logger.debug(user.toString());

		// update current user with new fields in DB
		userService.update(user);

		// show user account
		model.addAttribute("user", user);
		return "account";
	}
	
}
