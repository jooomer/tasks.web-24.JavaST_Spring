package ua.store.domain.controller.user;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.store.domain.model.dto.UserAccountDto;
import ua.store.domain.model.entity.Order;
import ua.store.domain.model.entity.User;
import ua.store.service.OrderService;
import ua.store.service.UserService;

@Controller
public class UsersController {
	
	private static final Logger logger = LogManager.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/users")
	public String showUsers(Model model, HttpSession session) {
		logger.debug("--- started"); 
		
		// get list of all users from DB
//		model.addAttribute("users", userService.findAll());
		session.setAttribute("users", userService.findAll());
		return "users";
	}
	
	/**
	 * shows user account
	 */
	@RequestMapping(value = "/users/{idStr}")
	public String showUserDetail(
			Model model, 
			@PathVariable String idStr
//			@ModelAttribute("userAccountDto") UserAccountDto userAccountDto
			) {
		logger.debug("--- started"); 

		long id = parsePathVariable(idStr);
		if (id < 0) {
			logger.debug("ERROR! Wrong user Id in URL.");
			model.addAttribute("message_danger", "This user doesn't exist.");
			return "message";
		}
		User user = userService.findOneWithOrders(id);
		if (user == null) {
			logger.debug("ERROR! Wrong user Id in URL.");
			model.addAttribute("message_warning", "This user doesn't exist.");
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
//		return "account-update";
		return "account";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST, params = {"update_users"}) 
	public String doSaveUser(
			Model model,
			HttpSession session,
			@RequestParam(value = "inBlackList", required = false) String inBlackListId,
			@RequestParam(value = "enabled", required = false) String enabledId
			) {
		logger.debug("--- started");
		
		List<User> users;
		if (session.getAttribute("users") instanceof List) {
			users = (List<User>) session.getAttribute("users");
		} else {
			logger.debug("Error. Cannot cast to List");
			return "redirect:/users";
		}
		System.out.println(users.get(0).toString());
		System.out.println("inBlackListUserId - " + inBlackListId);
		System.out.println("enabledUserId - " + enabledId);
		
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


	
	@RequestMapping("/users/{idStr}/orders") 
	public String showUserOrders(
			Model model,
			@PathVariable("idStr") String idStr) {
		logger.debug("--- started");
		
		// parse and check user id
		Long id = parsePathVariable(idStr);
		if (id < 0) {
			logger.debug("ERROR! Wrong user Id in URL.");
			model.addAttribute("message_danger", "This user doesn't exist.");
			return "message";
		}
		
		// get user from DB and and check 
		User user = userService.findOneWithOrders(id);
		if (user == null) {
			logger.debug("ERROR! Wrong user Id in URL.");
			model.addAttribute("message_warning", "This user doesn't exist.");
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
	
	/**
	 * parse url parameter and check it
	 */
	private long parsePathVariable(String string) {
		long number;
		try {
			number = Integer.valueOf(string);
		} catch (NumberFormatException e) {
			// throw new WrongUrlException(e);
			// just show first
			number = -1;
		}
		return number;
	}


	
}
