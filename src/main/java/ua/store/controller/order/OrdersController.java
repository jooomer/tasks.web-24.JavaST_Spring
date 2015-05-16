package ua.store.controller.order;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.Order;
import ua.store.domain.User;
import ua.store.service.OrderService;
import ua.store.service.UserService;
import ua.store.util.Util;

@Controller
public class OrdersController {

	private static final Logger logger = LogManager
			.getLogger(OrdersController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * just show order
	 */
	@RequestMapping(value = "/orders/{idStr}")
	public String showOrder(Model model, 
							Principal principal,
							Locale locale,
							@PathVariable String idStr,
							@ModelAttribute("orderStatusStr") String orderStatusStr) {
		logger.debug("--- started");
		
		// get and check order Id 
		long id = Util.parsePathVariable(idStr);
		if (id < 1) {
			logger.debug("ERROR! Wrong order number.");
			String message_danger = messageSource.getMessage("orders.Wrong_order_number", null, locale);
			model.addAttribute("message_danger", message_danger);
			return "message";
		}
		
		// prepare order 
		Order order = orderService.findOneByIdWithProducts(id);
		if (order == null) {
			logger.debug("ERROR! Order with this number doesn't exist.");
			String message_danger = messageSource.getMessage("orders.Order_number_doesnt_exist", null, locale);
			model.addAttribute("message_danger", message_danger);
			return "message";
		}

		logger.debug("Order is found. Order #: " + id);
		
		model.addAttribute("order", order);
		
		List<String> listOfOrderStatuses = new ArrayList<>();
		for (Order.Status orderStatus : Order.Status.values()) {
			listOfOrderStatuses.add(orderStatus.getName());
		}
		model.addAttribute("listOfOrderStatuses", listOfOrderStatuses);

		return "order-created";
	}	
	
	/**
	 * just show list of orders
	 */
	@RequestMapping("/orders")
	public String showOrders(Model model, 
							Principal principal) {
		logger.debug("--- started");
		
		// get and prepare list of orders to view
		User user = userService.findOneWithOrders(principal.getName());
		
		// get list of orders from DB and prepare it for view
		Set<Order> listOfOrders = orderService.findAllByUser(user);
		model.addAttribute("listOfOrders", listOfOrders);

		// 
		List<String> listOfOrderStatuses = new ArrayList<>();
		for (Order.Status orderStatus : Order.Status.values()) {
			listOfOrderStatuses.add(orderStatus.getName());
		}
		model.addAttribute("listOfOrderStatuses", listOfOrderStatuses);
		model.addAttribute("user", user);
		
		return "orders";
	}

	/**
	 * just show list of orders
	 */
	@RequestMapping(value = "/orders", params = "cancel_order")
	public String showCancelOrder(RedirectAttributes redirectAttributes,
								Locale locale,
								@RequestParam("cancel_order") Long id) {
		logger.debug("--- started");

		// set order status to "Canceled"
		Order order = orderService.findOneById(id);
		order.setStatus(Order.Status.CANCELED);
		orderService.update(order);

		String message_success = messageSource.getMessage("orders.Order_canceled", null, locale);
		redirectAttributes.addFlashAttribute("message_success", message_success);
				
		return "redirect:/orders";
	}


}
