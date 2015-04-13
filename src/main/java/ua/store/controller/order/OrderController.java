package ua.store.controller.order;

import java.security.Principal;
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

import ua.store.model.entity.Order;
import ua.store.model.entity.OrderItem;
import ua.store.model.entity.OrderStatus;
import ua.store.model.entity.User;
import ua.store.service.OrderService;
import ua.store.service.UserService;
import ua.store.tag.OrdersList;
import ua.store.tag.ProductMap;

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
	public String showOrder(Model model, HttpServletRequest request,
			Principal principal,
			@PathVariable String idStr) {
		logger.debug("--- started");

		// check user login
		if (principal == null) {
			return "login";
		}
		
		// get user
		User user = userService.findByName(principal.getName());

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

		return "order-created";
	}

	/**
	 * response to the "Make an order" button
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST, params = {"make_order"})
	public String doMakeOrder(Model model, HttpServletRequest request,
			Principal principal) {
		logger.debug("doMakeOrder() started.");

		// check user login
		if (principal == null) {
			model.addAttribute("jspPage", "/WEB-INF/view/common/login.jsp");
			return "template";
		}

		// prepare order and save it to DB
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		
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
		orderService.save(order);
		request.getSession().setAttribute("orderSaved", "success");
		
		// prepare User and Order to show an order in a page
		User user = userService.findByName(principal.getName());
		request.getSession().setAttribute("user", user);

		// prepare list of orderItems to show products in a cart page
		Set<OrderItem> listOfOrderItems = order.getOrderItems();
		model.addAttribute("listOfOrderItems", listOfOrderItems);
		
		model.addAttribute("message", "Thank you for your order!");
		return "order-created";
	}
	
	/**
	 * just show list of orders
	 */
	@RequestMapping("/orders")
	public String showOrders(Model model, Principal principal) {
		logger.debug("--- started");
		
		// check user in a session
		if (principal == null) {
			return "login";
		}
		
		// get and prepare list of orders to view
		User user = userService.findOneWithOrders(principal.getName());
		
		// get list of orders from DB and prepare it for view
		Set<Order> listOfOrders = orderService.findAllByUser(user);
		model.addAttribute("listOfOrders", listOfOrders);
				
		return "orders";
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
