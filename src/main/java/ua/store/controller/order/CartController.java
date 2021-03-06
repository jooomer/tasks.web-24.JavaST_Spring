package ua.store.controller.order;

import java.security.Principal;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.store.domain.Order;
import ua.store.domain.OrderItem;
import ua.store.service.OrderService;
import ua.store.service.ProductService;
import ua.store.service.UserService;

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
	
	@Autowired
	private MessageSource messageSource;

	/**
	 * just show cart
	 */
	@RequestMapping("/cart")
	public String showCart(Model model, 
							HttpSession session, 
							Principal principal,
							Locale locale) {
		logger.debug("--- started");
		
		Order order = (Order) session.getAttribute("order");
		String message_info = messageSource.getMessage("cart.Your_cart_is_empty", null, locale);
		
		// check state of order
		if (order == null
				|| (session.getAttribute("orderSaved") != null)
						&& session.getAttribute("orderSaved").equals("success")) {
			logger.debug("showCart() - check state of order=true. "
					+ "order: " + order 
					+ "; orderSaved: " + session.getAttribute("orderSaved"));
			session.setAttribute("listOfOrderItems", null);
			session.setAttribute("order", null);
			session.setAttribute("orderSaved", null);
			model.addAttribute("message_info", message_info);
			return "cart";
		}

		// prepare list of orderItems to show products in a cart page
		Set<OrderItem> listOfOrderItems = order.getOrderItems();
		if (listOfOrderItems.isEmpty()) {
			model.addAttribute("message_info", message_info);
		} else {
			model.addAttribute("listOfOrderItems", listOfOrderItems);
		}
		return "cart";
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.POST, params = {"remove_from_cart"})
	public String doRemoveFromCart(
			RedirectAttributes redirectAttributes, 
			HttpSession session,
			Locale locale,
			@RequestParam("remove_from_cart") Long id) {
		logger.debug("--- started");
		logger.debug("Product id to remove: " + id);
		
		Order order = (Order) session.getAttribute("order");
		if (order == null) {
			logger.debug("An order doesn't exist in a session.");
			return "redirect:/cart";
		}
		logger.debug("An order exists in a session.");
		
		order.deleteProduct(id);
		
		String message_success = messageSource.getMessage("cart.Product_is_removed", null, locale);
		redirectAttributes.addFlashAttribute("message_success", message_success);
		
		return "redirect:/cart";
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.POST, params = {"clean_cart"})
	public String doClearCart(Model model, HttpServletRequest request) {
		logger.debug("--- started");
		request.getSession().setAttribute("order", null);
		return "redirect:/cart";
	}
	

}
