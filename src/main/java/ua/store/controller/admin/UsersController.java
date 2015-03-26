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
@RequestMapping(value = "/users")
public class UsersController {
	
	private static final Logger logger = LogManager.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * handle request "/administrator/users" to show all users
	 * get all users from DB and prepare them for jsp
	 * call users.jsp
	 */
	@RequestMapping
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
	
	// handle request "/users/{id}" to show user detail
	// get one user by id from DB and prepare him for jsp
	// call user-detail.jsp
	@RequestMapping(value = "/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("user", userService.findOneWithProducts(id));
		return "user-detail";
	}
	
}
