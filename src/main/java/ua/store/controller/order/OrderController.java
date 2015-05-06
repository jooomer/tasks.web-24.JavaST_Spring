package ua.store.controller.order;

import java.security.Principal;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.store.domain.Order;
import ua.store.domain.OrderItem;
import ua.store.domain.User;
import ua.store.service.OrderService;
import ua.store.service.UserService;

@Controller
public class OrderController {

	private static final Logger logger = LogManager
			.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping("/order")
	public String showNewOrder(Model model, 
								HttpSession session, 
								Principal principal,
								Locale locale) {
		return doMakeOrder(model, session, principal, locale);
	}

	/**
	 * response to the "Make an order" button
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST, params = {"make_order"})
	public String doMakeOrder(Model model, 
								HttpSession session,
								Principal principal,
								Locale locale) {
		logger.debug("--- started");

		// prepare order and save it to DB
		Order order = (Order) session.getAttribute("order");
		if (order == null) {
			String message_warning = messageSource.getMessage("order.You_dont_have_new_order", null, locale);
			model.addAttribute("message_warning", message_warning);
			return "message";
		}
		
		User user = userService.findByName(principal.getName());
		order.setUser(user);

		// prepare User and Order to show in an order page
		session.setAttribute("user", user);
		session.setAttribute("order", order);

		// prepare list of orderItems to show products in a cart page
		Set<OrderItem> listOfOrderItems = order.getOrderItems();
		model.addAttribute("listOfOrderItems", listOfOrderItems);
		
		return "order";
	}

	/**
	 * response to the "Save and checkout" button
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST, params = {"save_and_checkout"})
	public String doSaveAndCheckoutOrder(Model model, 
										HttpSession session,
										Principal principal, 
										Locale locale,
										@ModelAttribute("comments") String comments) {
		logger.debug("--- started");

		// set Comments, OrderStatus and save order into DB
		Order order = (Order) session.getAttribute("order");
		order.setComments(comments);
		orderService.saveNewOrder(order);
		session.setAttribute("orderSaved", "success");
		
		// prepare User and Order to show an order in a page
		User user = userService.findByName(principal.getName());
		session.setAttribute("user", user);

		// prepare list of orderItems to show products in a cart page
		Set<OrderItem> listOfOrderItems = order.getOrderItems();
		model.addAttribute("listOfOrderItems", listOfOrderItems);
		
		String message_success = messageSource.getMessage("order.Thank_you_for_your_order", null, locale);
		model.addAttribute("message_success", message_success);
		return "order-created";
	}
	
}
