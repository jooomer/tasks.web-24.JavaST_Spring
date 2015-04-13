package ua.store.controller.order;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.store.model.entity.Order;
import ua.store.model.entity.OrderItem;
import ua.store.model.entity.Product;
import ua.store.service.OrderService;
import ua.store.service.ProductService;
import ua.store.service.UserService;
import ua.store.tag.ProductMap;

@Controller
public class CartController {

	private static final Logger logger = LogManager
			.getLogger(CartController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;
	
	@Autowired
	public ProductService productService;

	/**
	 * just show cart
	 */
	@RequestMapping("/cart")
	public String showCart(Model model, HttpServletRequest request, Principal principal) {
		logger.debug("--- started");
		
		Order order = (Order) request.getSession().getAttribute("order");
		
		// check state of order
		if (order == null
				|| (request.getSession().getAttribute("orderSaved") != null)
						&& request.getSession().getAttribute("orderSaved").equals("success")) {
			logger.debug("showCart() - check state of order=true. "
					+ "order: " + order 
					+ "; orderSaved: " + request.getSession().getAttribute("orderSaved"));
			request.getSession().setAttribute("productMap", null);
			request.getSession().setAttribute("order", null);
			request.getSession().setAttribute("orderSaved", null);
			model.addAttribute("message", "Your cart is empty. Please, choose your product.");
			return "cart";
		}

		// prepare list of orderItems to show products in a cart page
		Set<OrderItem> listOfOrderItems = order.getOrderItems();
		model.addAttribute("listOfOrderItems", listOfOrderItems);
		
		return "cart";
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.POST, params = {"clean_cart"})
	public String doClearCart(Model model, HttpServletRequest request) {
		logger.debug("--- started");
		request.getSession().setAttribute("order", null);
		return "redirect:/cart";
	}
	

}
