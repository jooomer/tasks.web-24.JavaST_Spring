package ua.store.controller.admin;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.store.controller.common.LoginController;
import ua.store.model.entity.User;
import ua.store.service.UserService;
import ua.store.tag.UserList;

@Controller
public class UsersController {
	
	private static final Logger logger = LogManager.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users")
	public String showUsers(Model model, HttpServletRequest request) {
		
		logger.debug("showUsers() started."); 
		
		// get list of all users from DB
		List<User> users = userService.findAll();
		UserList userList = new UserList();
		userList.createUserList(users);
		
		request.getSession().setAttribute("userList", userList);
		
		model.addAttribute("jspPage", "/WEB-INF/view/administrator/users.jsp");
		return "template";
		
	}
	
//	@RequestMapping(value = "/users/{id}")
//	public String detail(Model model, @PathVariable int id) {
//		model.addAttribute("user", userService.findOneWithOrders(id));
//		return "user-detail";
//	}
	
}
