package com.fdmgroup.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;
import com.fdmgroup.User.UserRoles;
import com.fdmgroup.User.UserType;
import com.fdmgroup.gateway.AdminGateway;

@Controller
public class UserController {

	@Autowired
	ServletContext sc;

	@RequestMapping("/addUser")
	public String displayAddUser(Model model) {
		model.addAttribute("ADDUSER", true);
		return "home";
	}

	@RequestMapping("/createdUser")
	public String displayCreatedUser(UserRoles role,
			@RequestParam String username, @RequestParam String password,
			@RequestParam String userType, @RequestParam String first_name,
			@RequestParam String last_name, Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("CREATEDUSER", true);
		if (username.isEmpty() || password.isEmpty() || first_name.isEmpty()
				|| last_name.isEmpty()) {
			model.addAttribute("ERROR", true);
			model.addAttribute(
					"errorMessage",
					"Cannot create user with missing field information. Please fill in all neccessary fields.");
		} else {
			model.addAttribute("ifCreated", gateway.executeAddUser(new User(
					username, password, first_name, last_name, new UserRoles(
							UserType.valueOf(userType)))));
		}
		return "home";
	}

	@RequestMapping("/removeUser")
	public String displayRemoveUser(Model model) {
		model.addAttribute("REMOVEUSER", true);
		return "home";
	}

	@RequestMapping("/userDeleted")
	public String displayDeletedUser(@RequestParam String userID, Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("USERDELETED", true);
		if (!userID.isEmpty()) {
			try {
				int ID = Integer.parseInt(userID);
				model.addAttribute("deletedSuccess",
						gateway.executeRemoveUser(ID));
			} catch (NumberFormatException e) {
				model.addAttribute("ERROR", true);
				model.addAttribute("errorMessage", "Input is not a number.");
			}
		} else {
			model.addAttribute("ERROR", true);
			model.addAttribute("errorMessage",
					"Cannot delete a user if you don't gimme the number!!");
		}
		return "home";
	}

	@RequestMapping("/banUser")
	public String banAUser(Model model) {
		model.addAttribute("BANUSER", true);
		return "home";
	}

	@RequestMapping("/userIsBanned")
	public String userIsBanned(@RequestParam String userID, Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("USERISBANNED", true);
		if (!userID.isEmpty()) {
			try {
				int ID = Integer.parseInt(userID);
				model.addAttribute("isBanned", gateway.executeBanUser(ID));
			} catch (NumberFormatException e) {
				model.addAttribute("ERROR", true);
				model.addAttribute("errorMessage", "Input is not a number.");
			}
		} else {
			model.addAttribute("ERROR", true);
			model.addAttribute("errorMessage",
					"Cannot ban a user if you don't gimme the number!!");
		}
		return "home";
	}

	@RequestMapping("/unbanUser")
	public String unbanUser(Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("UNBANUSER", true);
		model.addAttribute("requestList", gateway.executeViewAllRequests());
		return "home";
	}

	@RequestMapping("/permissionRestored")
	public String restoredPermissions(@RequestParam String userID, Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("requestList", gateway.executeViewAllRequests());
		model.addAttribute("UPDATEREQUEST", true);
		if (!userID.isEmpty()) {
			try {
				int ID = Integer.parseInt(userID);
				model.addAttribute("unbanSuccess", gateway.executeUnBan(ID));
			} catch (NumberFormatException e) {
				model.addAttribute("ERROR", true);
				model.addAttribute("errorMessage", "Input is not a number.");
			}
		} else {
			model.addAttribute("ERROR", true);
			model.addAttribute("errorMessage",
					"Cannot restore permissions to a user if you don't gimme the number!!");
		}
		return "home";
	}

	@RequestMapping("/addPermissions")
	public String addUserPermission(Model model) {
		model.addAttribute("ADDPERMISSIONS", true);
		return "home";
	}

	@RequestMapping("/userPermissionsAdded")
	public String permissionsAdded(@RequestParam String userID,
			@RequestParam String userType, Model model) {
		model.addAttribute("USERPERMISSIONSADDED", true);
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		if (!userID.isEmpty()) {
			try {
				int ID = Integer.parseInt(userID);
				model.addAttribute(
						"permissionAdded",
						gateway.executeAddPermissions(ID,
								UserType.valueOf(userType)));
			} catch (NumberFormatException e) {
				model.addAttribute("ERROR", true);
				model.addAttribute("errorMessage", "Input is not a number.");
			}
		} else {
			model.addAttribute("ERROR", true);
			model.addAttribute("errorMessage",
					"Cannot add permissions to a user if you don't gimme the number!!");
		}
		return "home";
	}

	@RequestMapping("/removeUserPermissions")
	public String removePermissions(Model model) {
		model.addAttribute("REMOVEUSERPERMISSIONS", true);
		return "home";
	}

	@RequestMapping("/permissionsRemoved")
	public String permissionsRemoved(@RequestParam String userID,
			@RequestParam String userType, Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		if (!userID.isEmpty()) {
			try {
				int ID = Integer.parseInt(userID);
				model.addAttribute(
						"permissionsRemoved",
						gateway.executeRemovePermission(ID,
								UserType.valueOf(userType)));
				model.addAttribute("PERMISSIONSREMOVED", true);
			} catch (NumberFormatException e) {
				model.addAttribute("ERROR", true);
				model.addAttribute("errorMessage", "Input is not a number.");
			}
		} else {
			model.addAttribute("ERROR", true);
			model.addAttribute("errorMessage",
					"Cannot remove permissions to a user if you don't gimme the number!!");
		}
		return "home";
	}

	@RequestMapping("/returnPermissions")
	public String returnPermissions(Model model) {
		model.addAttribute("RETURNPERMISSIONS", true);
		return "home";
	}

	@RequestMapping("/permissionsReturned")
	public String permissionsReturned(@RequestParam String userID,
			Crud<Storable> crud, Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		if (!userID.isEmpty()) {
			try {
				int ID = Integer.parseInt(userID);
				model.addAttribute("permissionsReturned",
						gateway.executeReturnPermissionsByUserID(ID));
				model.addAttribute("PERMISSIONSRETURNED", true);
			} catch (NumberFormatException e) {
				model.addAttribute("ERROR", true);
				model.addAttribute("errorMessage", "Input is not a number.");
			}
		} else {
			model.addAttribute("ERROR", true);
			model.addAttribute("errorMessage",
					"Cannot return permissions to a user if you don't gimme the number!!");
		}
		return "home";
	}

	@RequestMapping("/viewAllUsers")
	public String viewAllUsersController(Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("VIEWALLUSERS", true);
		model.addAttribute("usersList", gateway.executeReturnAllUsers());
		return "home";
	}
}
