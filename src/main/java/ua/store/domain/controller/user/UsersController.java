package ua.store.domain.controller.user;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String showUsers(Model model) {
		logger.debug("--- started"); 
		
		// get list of all users from DB
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	
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
		return "account-update";
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
