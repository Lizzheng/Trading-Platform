package com.fdmgroup.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.commands.ActivityInvoker;
import com.fdmgroup.commands.RemoveUserCommand;
import com.fdmgroup.receivers.UserRemover;

public class UserRemoverTest {

	static Crud<Storable> crud;

	@BeforeClass
	public static void init() {
		crud = new Crud<Storable>();
	}

	@Ignore
	public void test_UserRemover_Success() {
		UserRemover useRem = new UserRemover(148, crud);
		assertTrue(useRem.removeUser());
	}

	@Test
	public void test_UserRemover_Fails() {
		UserRemover useRem = new UserRemover(148, crud);
		assertFalse(useRem.removeUser());
	}

	@Ignore
	public void test_UseRemoverCommand_Success() {
		RemoveUserCommand remUser = new RemoveUserCommand(143, crud);
		ActivityInvoker actIn = new ActivityInvoker(remUser);
		actIn.invoke();
		assertTrue(remUser.isRemovedUserConfirmation());
	}

	@Test
	public void tes_UserRemoverCommand_Fails() {
		RemoveUserCommand remUser = new RemoveUserCommand(143, crud);
		ActivityInvoker actIn = new ActivityInvoker(remUser);
		actIn.invoke();
		assertFalse(remUser.isRemovedUserConfirmation());
	}

	@AfterClass
	public static void after() {
		crud.getEm().close();
	}

}
