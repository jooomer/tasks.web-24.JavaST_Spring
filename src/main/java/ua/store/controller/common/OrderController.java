package ua.store.controller.common;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.store.controller.admin.ProductsController;
import ua.store.model.entity.Order;
import ua.store.model.entity.User;
import ua.store.repository.OrderRepository;
import ua.store.repository.UserRepository;
import ua.store.service.OrderService;
import ua.store.service.UserService;
import ua.store.tag.ProductMap;

@Controller
public class OrderController {

	private static final Logger logger = LogManager
			.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@RequestMapping("/cart")
	public String showCart(Model model, HttpServletRequest request) {
		logger.debug("showCart() started.");

		// prepare ProductMap to show products in a cart page
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		ProductMap productMap = new ProductMap(order);
		session.setAttribute("productMap", productMap);
		model.addAttribute("jspPage", "/WEB-INF/view/common/cart.jsp");
		return "template";

	}

	@RequestMapping(value = "/order", method = RequestMethod.POST, params = {"make_an_order"})
	public String showOrder(Model model, HttpServletRequest request,
			Principal principal) {
		logger.debug("showOrder() started.");

		if (principal == null) {
			model.addAttribute("jspPage", "/WEB-INF/view/common/login.jsp");
			return "template";
		}

		// prepare order and save it to DB
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		order.setDate(new Date());
		User user = userService.findByName(principal.getName());
		order.setUser(user);

		// prepare User and Order to show in an order page
		model.addAttribute("user", user);
		model.addAttribute("order", order);

		// prepare ProductMap to show products in an order page
		ProductMap productMap = new ProductMap(order);
		session.setAttribute("productMap", productMap);
		model.addAttribute("jspPage", "/WEB-INF/view/register/order.jsp");
		return "template";

	}

	@RequestMapping(value = "/order", method = RequestMethod.POST, params = {"save_order"})
	public String doSaveOrder(Model model, HttpServletRequest request,
			Principal principal, @ModelAttribute("order") Order order) {
		logger.debug("doSaveOrder() started.");

		// prepare order and save it to DB
		HttpSession session = request.getSession();
//		Order order = (Order) session.getAttribute("order");
		orderService.save(order);

		// prepare User and Order to show in an order page
		User user = userService.findByName(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("order", order);

		// prepare ProductMap to show products in an order page
		ProductMap productMap = new ProductMap(order);
		session.setAttribute("productMap", productMap);
		model.addAttribute("jspPage", "/WEB-INF/view/register/order-created.jsp");
		return "template";

	}

}
