package com.fdmgroup.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.User.User;
import com.fdmgroup.gateway.AdminGateway;
import com.fdmgroup.gateway.Gateway;
import com.fdmgroup.request.Request;
import com.fdmgroup.request.RequestStatus;

@Controller
public class RequestController {

	@Autowired
	ServletContext sc;

	@RequestMapping("/addrequest")
	public String addRequestController(Model model) {
		model.addAttribute("ADDREQUEST", true);
		return "home";
	}

	@RequestMapping("/submitrequest")
	public String submitRequestController(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "requestType") String requestType,
			@RequestParam(value = "description") String description, Model model) {
		Gateway gateway = (Gateway) sc.getAttribute("gateway");
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"SpringAOP.xml");
		model.addAttribute("SUBMITREQUEST", true);
		Request request = (Request) ctx.getBean("request");
		if (username.isEmpty() || requestType.isEmpty()
				|| description.isEmpty()) {
			model.addAttribute("ERROR", true);
			model.addAttribute(
					"errorMessage",
					"Cannot enter requests with empty fields. Please fill in all neccessary information.");
		} else {
			request.setUser_Name(username);
			request.setDescription(description);
			model.addAttribute("request", request);
			model.addAttribute("requestType", requestType);
			model.addAttribute("addRequest",
					gateway.executeAddRequest(request, requestType));
		}
		return "home";
	}

	@RequestMapping("/deleteCompletedRequests")
	public String completeRequestController(Model model) {
		model.addAttribute("DELETECOMPLETEREQUESTS", true);
		return "home";
	}

	@RequestMapping("/executeDeleteCompletedRequests")
	public String submitCompleteReq(Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("EXECUTEDELETECOMPLETEDREQUESTS", true);
		model.addAttribute("completedRequestNumberDeleted",
				gateway.executeDeleteCompletedRequests());
		return "home";
	}

	@RequestMapping("/viewAllRequests")
	public String viewAllRequestController(Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("VIEWALLREQUESTS", true);
		model.addAttribute("requestList", gateway.executeViewAllRequests());
		return "home";
	}

	@RequestMapping("/viewOneRequest")
	public String viewOneRequestController(Model model) {
		model.addAttribute("VIEWONEREQUEST", true);
		return "home";
	}

	@RequestMapping("/submitToViewOneRequest")
	public String submitViewOneRequestController(
			@RequestParam(value = "requestID") String requestID, Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("SUBMITVIEWONEREQUEST", true);
		if (requestID.isEmpty()) {
			model.addAttribute("ERROR", true);
			model.addAttribute("errorMessage",
					"Please enter a request ID to view a request.");
		} else {
			try {
				int id = Integer.parseInt(requestID);
				model.addAttribute("request", gateway.executeViewOneRequest(id));
			} catch (NumberFormatException e) {
				model.addAttribute("ERROR", true);
				model.addAttribute("errorMessage", "Input is not a number.");
			}
		}
		return "home";
	}

	@RequestMapping("/updateRequest")
	public String updateRequestController(Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("UPDATEREQUEST", true);
		model.addAttribute("requestList", gateway.executeViewAllRequests());
		return "home";
	}

	@RequestMapping("/submitUpdatedRequest")
	public String submitUpdatedRequestController(
			@RequestParam(value = "requestID") String requestID,
			HttpSession session,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "status") String status, Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("SUBMITUPDATEDREQUEST", true);
		if (requestID.isEmpty() || description.isEmpty()) {
			model.addAttribute("ERROR", true);
			model.addAttribute(
					"errorMessage",
					"Cannot update request with empty field(s). Please fill in all neccessary information.");
		} else {
			try {
				int request_ID = Integer.parseInt(requestID);
				User user = (User) session.getAttribute("LoggedInUser");
				int admin_ID = user.getID();
				model.addAttribute("updaterequest", gateway
						.executeUpdateRequest(request_ID, admin_ID,
								description, RequestStatus.valueOf(status)));
			} catch (NumberFormatException e) {
				model.addAttribute("ERROR", true);
				model.addAttribute("errorMessage", "Input is not a number.");
			}
		}
		return "home";
	}

	@RequestMapping("/viewRequestsByStatus")
	public String viewRequestByStatusController(Model model) {
		model.addAttribute("VIEWREQUESTSBYSTATUS", true);
		return "home";
	}

	@RequestMapping("/submitViewRequestByStatus")
	public String submitViewRequestByStatusController(
			@RequestParam(value = "status") String status, Model model) {
		AdminGateway gateway = (AdminGateway) sc.getAttribute("gateway");
		model.addAttribute("SUBMITVIEWREQUESTBYSTATUS", true);
		RequestStatus requestStatus = RequestStatus.valueOf(status);
		model.addAttribute("viewRequests",
				gateway.executeViewRequestsByStatus(requestStatus));
		return "home";
	}
}
