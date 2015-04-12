package ua.store.controller.order;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.store.model.entity.Order;
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
	@RequestMapping(value = "/order")
	public String showOrder(Model model, HttpServletRequest request,
			Principal principal) {
		logger.debug("showOrder() started.");

		// check user login
		if (principal == null) {
			model.addAttribute("jspPage", "/WEB-INF/view/common/login.jsp");
			return "template";
		}

		// prepare order 
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		User user = userService.findByName(principal.getName());

		
		// prepare User and Order to show in an order page
		session.setAttribute("user", user);
		session.setAttribute("order", order);

		// prepare ProductMap to show products in a page
		ProductMap productMap = new ProductMap(order);
		session.setAttribute("productMap", productMap);
		
		// check if order already saved
		if (session.getAttribute("orderSaved") != null
				&& session.getAttribute("orderSaved").equals("success")) {
			model.addAttribute("jspPage", "/WEB-INF/view/registered/order-created.jsp");
			return "template";
		}
		
		
		model.addAttribute("jspPage", "/WEB-INF/view/registered/order.jsp");
		return "template";
	}

	/**
	 * response to the "Make an order" button
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST, params = {"make_an_order"})
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

		// prepare ProductMap to show products in a page
		ProductMap productMap = new ProductMap(order);
		session.setAttribute("productMap", productMap);
		model.addAttribute("jspPage", "/WEB-INF/view/registered/order.jsp");
		return "template";
	}

	/**
	 * response to the "Save and checkout" button
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST, params = {"save_and_checkout"})
	public String doSaveAndCheckoutOrder(Model model, HttpServletRequest request,
			Principal principal, @ModelAttribute("comments") String comments) {
		logger.debug("doSaveAndCheckoutOrder() started.");

		// set Comments, OrderStatus and save order into DB
		Order order = (Order) request.getSession().getAttribute("order");
		order.setComments(comments);
		order.setOrderStatus(OrderStatus.WAITING_FOR_PAIMENT);
		orderService.save(order);
		request.getSession().setAttribute("orderSaved", "success");
		
		// remove Order from session
//		request.getSession().setAttribute("order", null);

		// prepare User and Order to show an order in a page
		User user = userService.findByName(principal.getName());
//		model.addAttribute("user", user);
//		model.addAttribute("order", order);
		request.getSession().setAttribute("user", user);
//		request.getSession().setAttribute("order", order);

		// prepare ProductMap to show products in an order page
		ProductMap productMap = new ProductMap(order);
		request.getSession().setAttribute("productMap", productMap);
		
		model.addAttribute("message", "Thank you for your order!");
		model.addAttribute("jspPage", "/WEB-INF/view/registered/order-created.jsp");
		return "template";

	}
	
	/**
	 * just show list of orders
	 */
	@RequestMapping("/orders")
	public String showOrders(Model model, Principal principal, HttpServletRequest request) {
		logger.debug("showOrders() started.");
		
		// check user in a session
		if (principal == null) {
			model.addAttribute("jspPage", "/WEB-INF/view/common/login.jsp");
			return "template";
		}
		
		// get and prepare list of orders to view
		User user = userService.findOneWithOrders(principal.getName());

		// get list of orders from DB and prepate it for view
		OrdersList ordersList = new OrdersList(user);

		System.out.println("--- after: OrdersList ordersList = new OrdersList(user);");

		request.getSession().setAttribute("ordersList", ordersList);
		
		model.addAttribute("jspPage", "/WEB-INF/view/registered/orders.jsp");
		return "template";
	}

}
