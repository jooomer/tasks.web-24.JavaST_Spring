package ua.store.web.controller.order;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.constant.OrderStatus;
import ua.store.domain.entity.Order;
import ua.store.domain.entity.OrderItem;
import ua.store.domain.entity.User;
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

	/**
	 * just show order
	 */
	@RequestMapping(value = "/orders/{idStr}")
	public String showOrder(
			Model model, 
			Principal principal,
			@PathVariable String idStr,
			@ModelAttribute("orderStatusStr") String orderStatusStr) {
		logger.debug("--- started");
		
		System.out.println(orderStatusStr);

		// get and check order Id 
		long id = parsePathVariable(idStr);
		if (id < 1) {
			logger.debug("ERROR! Wrong order number.");
			model.addAttribute("message_danger", "ERROR! Wrong order number.");
			return "message";
		}
		
		// prepare order 
		Order order = orderService.findOneByIdWithProducts(id);
		if (order == null) {
			logger.debug("ERROR! Order with this number doesn't exist.");
			model.addAttribute("message_danger", "ERROR! Order with this number doesn't exist.");
			return "message";
		}

		logger.debug("Order is found. Order #: " + id);
		
		model.addAttribute("order", order);
		
//		OrderStatus[] listOfOrderStatuses = OrderStatus.values();
		List<String> listOfOrderStatuses = new ArrayList<>();
		for (OrderStatus orderStatus : OrderStatus.values()) {
			listOfOrderStatuses.add(orderStatus.getName());
		}
		model.addAttribute("listOfOrderStatuses", listOfOrderStatuses);

		return "order-created";
	}
	
	@RequestMapping("/order")
	public String showNewOrder(Model model, HttpServletRequest request, Principal principal) {
		return doMakeOrder(model, request, principal);
	}

	/**
	 * response to the "Make an order" button
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST, params = {"make_order"})
	public String doMakeOrder(Model model, HttpServletRequest request,
			Principal principal) {
		logger.debug("doMakeOrder() started.");

		// prepare order and save it to DB
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		if (order == null) {
			model.addAttribute("message_warning", "You don't have a new order.");
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
	public String doSaveAndCheckoutOrder(Model model, HttpServletRequest request,
			Principal principal, @ModelAttribute("comments") String comments) {
		logger.debug("--- started");

		// set Comments, OrderStatus and save order into DB
		Order order = (Order) request.getSession().getAttribute("order");
		order.setComments(comments);
		order.setOrderStatus(OrderStatus.WAITING_FOR_PAIMENT);
		orderService.saveNewOrder(order);
		request.getSession().setAttribute("orderSaved", "success");
		
		// prepare User and Order to show an order in a page
		User user = userService.findByName(principal.getName());
		request.getSession().setAttribute("user", user);

		// prepare list of orderItems to show products in a cart page
		Set<OrderItem> listOfOrderItems = order.getOrderItems();
		model.addAttribute("listOfOrderItems", listOfOrderItems);
		
		model.addAttribute("message_success", "Thank you for your order! \n"
				+ "As soon as you pay the order, we will send the goods to the specified address.");
		return "order-created";
	}
	
	/**
	 * just show list of orders
	 */
	@RequestMapping("/orders")
	public String showOrders(Model model, Principal principal) {
		logger.debug("--- started");
		
		// get and prepare list of orders to view
		User user = userService.findOneWithOrders(principal.getName());
		
		// get list of orders from DB and prepare it for view
		Set<Order> listOfOrders = orderService.findAllByUser(user);
		model.addAttribute("listOfOrders", listOfOrders);

		// 
//		OrderStatus[] listOfOrderStatuses = OrderStatus.values();
		List<String> listOfOrderStatuses = new ArrayList<>();
		for (OrderStatus orderStatus : OrderStatus.values()) {
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
	public String showCancelOrder(
			RedirectAttributes redirectAttributes,
			@RequestParam("cancel_order") Long id) {
		logger.debug("--- started");

		// set order status to "Canceled"
		Order order = orderService.findOneById(id);
		order.setOrderStatus(OrderStatus.CANCELED);
		orderService.update(order);
		System.out.println("Order status: " + order.getOrderStatus());

		redirectAttributes.addFlashAttribute("message_success", "Your order was successfully canceled.");
				
		return "redirect:/orders";
	}

	/**
	 * parse url parameter and check it
	 */
	private int parsePathVariable(String string) {
		int number;
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
