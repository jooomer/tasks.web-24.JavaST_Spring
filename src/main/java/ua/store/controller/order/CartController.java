package ua.store.controller.order;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.store.model.entity.Order;
import ua.store.service.OrderService;
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

	/**
	 * just show cart
	 */
	@RequestMapping("/cart")
	public String showCart(Model model, HttpServletRequest request, Principal principal) {
		logger.debug("showCart() started.");
		
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
			model.addAttribute("jspPage", "/WEB-INF/view/common/cart.jsp");
			return "template";
		}

		// prepare ProductMap to show products in a cart page
		ProductMap productMap = new ProductMap(order);
		request.getSession().setAttribute("productMap", productMap);
		request.getSession().setAttribute("order", order);
		
		model.addAttribute("jspPage", "/WEB-INF/view/common/cart.jsp");
		return "template";
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.POST, params = {"clear_cart"})
	public String doClearCart(Model model, HttpServletRequest request) {
		logger.debug("doClearCart() started.");
		request.getSession().setAttribute("order", null);
		return "redirect:/cart";
	}
	

}
