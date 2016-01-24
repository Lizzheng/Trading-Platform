package com.fdmgroup.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.commands.ActivityInvoker;
import com.fdmgroup.commands.AddPermissionsCommand;
import com.fdmgroup.commands.RemovePermissionsCommand;
import com.fdmgroup.receivers.PermissionModifier;

public class PermissionModifierTest {

	static Crud<Storable> crud;

	@BeforeClass
	public static void init() {
		crud = new Crud<Storable>();
	}

	@Test
	public void testAddValidAccess_Success() {
		PermissionModifier permission = new PermissionModifier(124,
				UserType.SHAREHOLDER, crud);
		assertTrue(permission.addAccess());
	}

	@Test
	public void testAddValidAccess_FailsWithBannedUSer() {
		PermissionModifier permission = new PermissionModifier(140,
				UserType.SHAREHOLDER, crud);
		assertTrue(permission.addAccess());
	}

	@Test
	public void testAddValidAccess_FailsWithInvalidUserType() {
		PermissionModifier permission = new PermissionModifier(124,
				UserType.BROKER, crud);
		assertFalse(permission.addAccess());
	}

	@Test
	public void testRemoveAccess_Success() {
		PermissionModifier permission = new PermissionModifier(124,
				UserType.SHAREHOLDER, crud);
		assertTrue(permission.removeAccess());
	}

	@Test
	public void testRemoveAccess_FailsWithMissingType() {
		PermissionModifier permission = new PermissionModifier(124,
				UserType.SHAREHOLDER, crud);
		assertFalse(permission.removeAccess());
	}

	@Test
	public void testAddPermissionsWithCommand() {
		AddPermissionsCommand addCommand = new AddPermissionsCommand(124,
				UserType.SHAREHOLDER, crud);
		ActivityInvoker actIn = new ActivityInvoker(addCommand);
		actIn.invoke();
		assertTrue(addCommand.isPermissionAdded());
	}

	@Test
	public void testAddPermissionsWithCommand_Fails() {
		AddPermissionsCommand addCommand = new AddPermissionsCommand(124,
				UserType.SHAREHOLDER, crud);
		ActivityInvoker actIn = new ActivityInvoker(addCommand);
		actIn.invoke();
		assertFalse(addCommand.isPermissionAdded());
	}

	@Test
	public void testRemovePermissionWithCommand_Success() {
		RemovePermissionsCommand removePermission = new RemovePermissionsCommand(
				140, UserType.BANNED, crud);
		ActivityInvoker actIn = new ActivityInvoker(removePermission);
		actIn.invoke();
		assertTrue(removePermission.isPermissionRemoved());
	}

	@Test
	public void testRemovePermissionWithCommand_Fails() {
		RemovePermissionsCommand removePermission = new RemovePermissionsCommand(
				140, UserType.BANNED, crud);
		ActivityInvoker actIn = new ActivityInvoker(removePermission);
		actIn.invoke();
		assertFalse(removePermission.isPermissionRemoved());
	}

	@AfterClass
	public static void after() {
		crud.getEm().close();
	}

}
