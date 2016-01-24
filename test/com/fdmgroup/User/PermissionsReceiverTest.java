package com.fdmgroup.User;

import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.commands.ActivityInvoker;
import com.fdmgroup.commands.ReturnPermissionsCommand;
import com.fdmgroup.receivers.PermissionsReceiver;

public class PermissionsReceiverTest {

	static Crud<Storable> crud;

	@BeforeClass
	public static void init() {
		crud = new Crud<Storable>();
	}

	@Test
	public void testPermissionsrecieverWorks() {
		PermissionsReceiver pReceiever = new PermissionsReceiver(122, crud);
		assertTrue(pReceiever.returnPermissions().size() == 1);
	}

	@Test
	public void testPermissionsrecieverFails() {
		PermissionsReceiver pReceiever = new PermissionsReceiver(10, crud);
		assertTrue(pReceiever.returnPermissions().size() == 0);
	}

	@Test
	public void testPermissionsReceieverCommandWorks() {
		ReturnPermissionsCommand permissCommand = new ReturnPermissionsCommand(
				122, crud);
		ActivityInvoker invokePermissions = new ActivityInvoker(permissCommand);
		invokePermissions.invoke();
		assertTrue(permissCommand.getPermissionsList().size() == 1);

	}

	@Test
	public void testPermissionsReceieverCommandFails() {
		ReturnPermissionsCommand permissCommand = new ReturnPermissionsCommand(
				10, crud);
		ActivityInvoker invokePermissions = new ActivityInvoker(permissCommand);
		invokePermissions.invoke();
		assertTrue(permissCommand.getPermissionsList().size() == 0);
	}

	@AfterClass
	public static void after() {
		crud.getEm().close();
	}
}
