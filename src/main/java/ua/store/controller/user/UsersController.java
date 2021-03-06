package ua.store.controller.user;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.Order;
import ua.store.domain.User;
import ua.store.dto.UserAccountDto;
import ua.store.service.OrderService;
import ua.store.service.UserService;
import ua.store.util.Util;

@Controller
public class UsersController {
	
	private static final Logger logger = LogManager.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/users")
	public String showUsers(Model model, HttpSession session) {
		logger.debug("--- started"); 
		
		// get list of all users from DB
		session.setAttribute("users", userService.findAll());
		return "users";
	}
	
	/**
	 * shows user account
	 */
	@RequestMapping(value = "/users/{idStr}")
	public String showUserDetail(Model model, 
								@PathVariable String idStr,
								Locale locale) {
		logger.debug("--- started"); 

		String message_warning = messageSource.getMessage("error.User_is_unavailable", null, locale);

		// parse id and check it
		long id = Util.parsePathVariable(idStr);
		if (id < 0) {
			logger.debug("ERROR! Wrong user Id in URL.");
			model.addAttribute("message_warning", message_warning);
			return "message";
		}
		User user = userService.findOneWithOrders(id);
		if (user == null) {
			logger.debug("ERROR! Wrong user Id in URL.");
			model.addAttribute("message_warning", message_warning);
			return "message";
		}
		logger.debug("User is found. Id: " + id);
		
		UserAccountDto userAccountDto = new UserAccountDto();
		userAccountDto.setFirstName(user.getFirstName());
		userAccountDto.setLastName(user.getLastName());
		userAccountDto.setEmail(user.getEmail());
		userAccountDto.setPhone(user.getPhone());
		userAccountDto.setAddress(user.getAddress());

		model.addAttribute("userAccountDto", userAccountDto);

		model.addAttribute("user", user);
		return "user-detail";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST, params = {"update_users"}) 
	public String doSaveUser(
			Model model,
			HttpSession session,
			@RequestParam(value = "inBlackList", required = false) String inBlackListId,
			@RequestParam(value = "enabled", required = false) String enabledId) {
		logger.debug("--- started");
		
		List<User> users;
		if (session.getAttribute("users") instanceof List) {
			users = (List<User>) session.getAttribute("users");
		} else {
			logger.debug("Error. Cannot cast to List");
			return "redirect:/users";
		}
		
		List<String> inBlackListArrayId = Arrays.asList(inBlackListId.split(","));
		List<String> enabledArrayId = Arrays.asList(enabledId.split(","));
		for (User user : users) {
			Long id = user.getId();
			if (inBlackListArrayId.contains(id.toString())) {
				user.setInBlackList(true);
			} else {
				user.setInBlackList(false);
			}
			if (enabledArrayId.contains(id.toString())) {
				user.setEnabled(true);
			} else {
				user.setEnabled(false);
			}
		}
		userService.update(users);
		
		return "redirect:/users";
		
	}
	
	@RequestMapping(value = {"/users", "/users/{idStr}"}, 
					method = RequestMethod.POST, 
					params = {"delete_user"})
	public String doDeleteUser(RedirectAttributes redirectAttributes,
								@RequestParam("delete_user") Long id,
								Locale locale) {
		logger.debug("--- started");
		
		String message_warning = messageSource.getMessage("error.User_is_unavailable", null, locale);

		if (id == null) {
			logger.debug("Error. Cannot get user id to delete him.");
			redirectAttributes.addFlashAttribute("message_warning", message_warning);
			return "redirect:/users";
		}
		logger.debug("User id to delete: " + id);

		userService.delete(id);
		
		String message_success = messageSource.getMessage("users.User_deleted", null, locale);
		redirectAttributes.addFlashAttribute("message_success", message_success);
		
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/users/{idStr}/delete")
	public String doDeleteUserFromUsers(Model model,
										HttpSession session,
										@PathVariable String idStr,
										Locale locale) {
		logger.debug("--- started"); 

		String message_warning = messageSource.getMessage("error.User_is_unavailable", null, locale);

		long id = Util.parsePathVariable(idStr);
		if (id < 0) {
			logger.debug("ERROR! Wrong user Id in URL.");
			model.addAttribute("message_warning", message_warning);
			return "message";
		}
		User user = userService.findOneWithOrders(id);
		if (user == null) {
			logger.debug("ERROR! Wrong user Id in URL.");
			model.addAttribute("message_warning", message_warning);
			return "message";
		}
		logger.debug("User is found. Id: " + id);
		
		model.addAttribute("id", id);
		model.addAttribute("action", "delete_user");
		
		return "popup_delete";
	}
 	
	@RequestMapping(value = "/users/{idStr}/orders") 
	public String showUserOrders(Model model,
								@PathVariable("idStr") String idStr,
								Locale locale) {
		logger.debug("--- started");
		
		String message_warning = messageSource.getMessage("error.User_is_unavailable", null, locale);

		// parse and check user id
		Long id = Util.parsePathVariable(idStr);
		if (id < 0) {
			logger.debug("ERROR! Wrong user Id in URL.");
			model.addAttribute("message_warning", message_warning);
			return "message";
		}
		
		// get user from DB and and check 
		User user = userService.findOneWithOrders(id);
		if (user == null) {
			logger.debug("ERROR! Wrong user Id in URL.");
			model.addAttribute("message_warning", message_warning);
			return "message";
		}
		logger.debug("User is found. Id: " + id);
		
		// get list of orders from DB and prepare it for view
		Set<Order> listOfOrders = orderService.findAllByUser(user);
		model.addAttribute("listOfOrders", listOfOrders);
		
		// prepare user to show
		model.addAttribute("user", user);
		
		return "orders";
	}
	
}
