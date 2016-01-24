package com.fdmgroup.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.fdmgroup.User.User;
import com.fdmgroup.User.UserType;
import com.fdmgroup.gateway.AdminGateway;
import com.fdmgroup.gateway.Gateway;

@Controller
@SessionAttributes("LoggedInUser")
public class LoginController {

	@Autowired
	ServletContext sc;

	@RequestMapping("/")
	public String loginController(Model model) {
		model.addAttribute("HOME", true);
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submitLoginController(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, Model model) {
		Gateway gateway = (Gateway) sc.getAttribute("gateway");
		User user = gateway.Login(username, password);
		model.addAttribute("LOGIN", true);
		if (username.isEmpty() || password.isEmpty() || user == null
				|| user.getRoleType().contains(UserType.BANNED)) {
			if (user == null || !user.getRoleType().contains(UserType.BANNED)) {
				model.addAttribute("ERROR", true);
				model.addAttribute("errorMessage",
						"Incorrect username and/or password");
			} else {
				model.addAttribute("ERROR", true);
				model.addAttribute("errorMessage", "User is Banned");
			}
		} else
			model.addAttribute("LoggedInUser", user);
		return "home";
	}

	@RequestMapping("/returnHome")
	public String returnHomeWhileLoggedIn(User user, HttpSession session,
			Model model) {
		user = (User) session.getAttribute("LoggedInUser");
		if (session.getAttribute("LoggedInUser") != null) {
			model.addAttribute("firstname", user.getFirstname());
			model.addAttribute("lastname", user.getLastname());
			model.addAttribute("RETURNHOME", true);
		} else
			model.addAttribute("HOME", true);
		return "home";
	}

	@RequestMapping("/account")
	public String viewAccountController(HttpSession session, Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("ACCOUNT", true);
		User user = (User) session.getAttribute("LoggedInUser");
		model.addAttribute("requestsMade",
				gateway.executeReturnRequestsMadeByUser(user.getID()));
		if (user.getRoleType().contains(UserType.SYSTEMADMIN)) {
			model.addAttribute("requestsHandled",
					gateway.executeReturnRequestsHandledByAdmin(user.getID()));
		}
		return "home";
	}

	@RequestMapping("/logout")
	public String loggingOut(SessionStatus session, Model model) {
		Gateway gateway = (Gateway) sc.getAttribute("gateway");
		model.addAttribute("HOME", true);
		session.setComplete();
		gateway.Logout();
		return "home";
	}
	
	@RequestMapping("/adminlog")
	public String adminLog(Model model){
		return "adminlog";
	}
	
	@RequestMapping("/userlog")
	public String userLog(Model model){
		return "userlog";
	}

	@RequestMapping("/adminlog")
	public String adminLogController(Model model) {
		return "adminlog";
	}

	@RequestMapping("/userlog")
	public String userLogController(Model model) {
		return "userlog";
	}

}


