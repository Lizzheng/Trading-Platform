package com.fdmgroup.springaop;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;
import com.fdmgroup.User.UserRoles;
import com.fdmgroup.User.UserType;
import com.fdmgroup.gateway.AdminGateway;
import com.fdmgroup.request.Request;
import com.fdmgroup.request.RequestStatus;

public class ConsoleLogTest {

	static Crud<Storable> crud;

	@BeforeClass
	public static void before() {
		crud = new Crud<Storable>();
	}

	@Test
	public void testLogRequestRegistrar() {
		Request request = new Request();
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"SpringAOP.xml");
		request.setDescription("Want to be a shareholder");
		request.setUser_Name("matt");
		AdminGateway gateway = (AdminGateway) ctx.getBean("gateway");
		gateway.executeAddRequest(request, "REGISTRATION");
		gateway.executeDeleteCompletedRequests();
		gateway.executeViewAllRequests();
		gateway.executeAddUser(new User("liz", "pw", "liz", "zheng",
				new UserRoles(UserType.SYSTEMADMIN)));
		gateway.executeBanUser(140);
		gateway.executeRemoveUser(120);
		gateway.executeReturnPermissionsByUserID(140);
		gateway.executeUpdateRequest(163, 140, "NO", RequestStatus.COMPLETED);
		gateway.executeViewOneRequest(51);
		gateway.executeViewRequestsByStatus(RequestStatus.IN_PROGRESS);
		gateway.Login("will", "pw");
		gateway.Logout();
	}

}
