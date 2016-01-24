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
import com.fdmgroup.commands.AddUserCommand;
import com.fdmgroup.receivers.UserAdder;

public class UserAdderTest {

	static Crud<Storable> crud;

	@BeforeClass
	public static void init() {
		crud = new Crud<Storable>();
	}

	@Ignore
	public void addUser_test() {
		UserRoles userRoles = new UserRoles(UserType.BANNED);
		User user = new User("lizhasnoidea", "liz", "liz", "zheng", userRoles);
		UserAdder userAdder = new UserAdder(user, crud);
		assertTrue(userAdder.addUser());
	}

	@Test
	public void addUserFails_test() {
		UserRoles userRoles = new UserRoles(UserType.BROKER);
		User user = new User("jesse", "mccullough", "jesse", "mccullough", userRoles);
		UserAdder userAdder = new UserAdder(user, crud);
		assertFalse(userAdder.addUser());
	}

	@Ignore
	public void addUserCommand_test() {
		UserRoles userRoles = new UserRoles(UserType.BROKER);
		User user = new User("jesse", "mccullough", "jesse", "mccullough", userRoles);
		AddUserCommand userAdder = new AddUserCommand(user, crud);
		ActivityInvoker actIn = new ActivityInvoker(userAdder);
		actIn.invoke();
		assertTrue(userAdder.isAddedUserConfirmation());
	}

	@Test
	public void addUserCommandFails_test() {
		UserRoles userRoles = new UserRoles(UserType.BROKER);
		User user = new User("jesse", "mccullough", "jesse", "mccullough", userRoles);
		AddUserCommand userAdder = new AddUserCommand(user, crud);
		ActivityInvoker actIn = new ActivityInvoker(userAdder);
		actIn.invoke();
		assertFalse(userAdder.isAddedUserConfirmation());
	}

	@AfterClass
	public static void after() {
		crud.getEm().close();
	}

}
