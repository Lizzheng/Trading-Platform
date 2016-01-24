package com.fdmgroup.springaop;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import com.fdmgroup.User.User;
import com.fdmgroup.User.UserType;

@Aspect
public class ConsoleLog
{

	static Logger log = Logger.getLogger(ConsoleLog.class);

	ConsoleLog()
	{

		PropertyConfigurator
				.configure("H:\\Java\\Workspace\\TradePlatformAdmin\\UserLog.properties");
	}

	@Before("com.fdmgroup.springaop.PointCuts.addRequest()")
	public void addRequest()
	{
		log.info("New Request has been created.");
	}

	@Before("com.fdmgroup.springaop.PointCuts.deleteCompleteRequests()")
	public void deleteCompleteRequests()
	{
		log.info("Request is complete, removing request.");
	}

	@Before("com.fdmgroup.springaop.PointCuts.viewAllRequest()")
	public void viewAllRequest()
	{
		log.info("Retrieving all request for view.");
	}

	@Before("com.fdmgroup.springaop.PointCuts.viewOneRequest()")
	public void viewOneRequest()
	{
		log.info("Retrieving a single request for view.");
	}

	@Before("com.fdmgroup.springaop.PointCuts.updateRequest()")
	public void updateRequest()
	{
		log.info("Updating a request's details.");
	}

	@Before("com.fdmgroup.springaop.PointCuts.viewRequestByStatus()")
	public void viewRequestByStatus()
	{
		log.info("Retrieving all request of one status for view.");
	}

	@Before("com.fdmgroup.springaop.PointCuts.returnPermissionsByUserID()")
	public void returnPermissionsByUserID()
	{
		log.info("Retrieving a users permissions by user id.");
	}

	@Before("com.fdmgroup.springaop.PointCuts.banUser()")
	public void banUser()
	{
		log.info("User is banned from our system.");
	}

	@Before("com.fdmgroup.springaop.PointCuts.addUser()")
	public void addUser()
	{
		log.info("Adding user to our system.");
	}

	@Before("com.fdmgroup.springaop.PointCuts.removeUser()")
	public void removeUser()
	{
		log.info("Removing user from our system.");
	}

	@Before("com.fdmgroup.springaop.PointCuts.logLogin()")
	public void logLogin()
	{
		log.info("User is attempting to log in.");
	}

	@Before("com.fdmgroup.springaop.PointCuts.logLogout()")
	public void logLogout()
	{
		PropertyConfigurator
				.configure("H:\\Java\\Workspace\\TradePlatformAdmin\\UserLog.properties");
		log.info("User has logged out.");
	}

	@AfterReturning(pointcut = "com.fdmgroup.springaop.PointCuts.logLogin()", returning = "user")
	public void LoggedInUser(Object user)
	{
		if (user != null)
		{
			if (((User) user).getRoleType().contains(UserType.SYSTEMADMIN))
				PropertyConfigurator
						.configure("H:\\Java\\Workspace\\TradePlatformAdmin\\AdminLog.properties");
			log.info("User has logged in.");
		}
		else
			log.info("Login failed");
	}
	
	@Before("com.fdmgroup.springaop.PointCuts.addPermissions()")
	public void addPermissions()
	{
		log.info("Permissions added to User");
	}
	
	@Before("com.fdmgroup.springaop.PointCuts.removePermissions()")
	public void removePermissions()
	{
		log.info("Permissions removed from user");
	}
	
	@Before("com.fdmgroup.springaop.PointCuts.returnUsersMadeRequests()")
	public void returnUsersMadeRequests()
	{
		log.info("Viewing user's request");
	}
	
	@Before("com.fdmgroup.springaop.PointCuts.returnAdminHandledRequests()")
	public void returnAdminHandledRequests()
	{
		log.info("Viewing requests handled by admin");
	}
	
	@Before("com.fdmgroup.springaop.PointCuts.returnUser()")
	public void returnUser()
	{
		log.info("View user's information");
	}
}
