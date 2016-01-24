package com.fdmgroup.springaop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCuts {
	@Pointcut("execution(* com.fdmgroup.gateway.Gateway.executeAddRequest(..))")
	public void addRequest() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeDeleteCompletedRequests(..))")
	public void deleteCompleteRequests() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeViewAllRequests(..))")
	public void viewAllRequest() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeViewOneRequest(..))")
	public void viewOneRequest() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeUpdateRequest(..))")
	public void updateRequest() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeViewRequestsByStatus(..))")
	public void viewRequestByStatus() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeReturnPermissionsByUserID(..))")
	public void returnPermissionsByUserID() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeBanUser(..))")
	public void banUser() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeUnbanUser(..))")
	public void unbanUser() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeAddUser(..))")
	public void addUser() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeRemoveUser(..))")
	public void removeUser() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.Gateway.Login(..))")
	public void logLogin() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.Gateway.Logout(..))")
	public void logLogout() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.Gateway.setLoggedInUser(..))")
	public void LoggedInUser() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeAddPermissions(..))")
	public void addPermissions() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeRemovePermissions(..))")
	public void removePermissions() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.Gateway.executeReturnRequestsMadeByUser(..))")
	public void returnUsersMadeRequests() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.AdminGateway.executeReturnRequestsHandledByAdmin(..))")
	public void returnAdminHandledRequests() {
	}

	@Pointcut("execution(* com.fdmgroup.gateway.Gateway.executeReturnUser(..))")
	public void returnUser() {
	}
}
