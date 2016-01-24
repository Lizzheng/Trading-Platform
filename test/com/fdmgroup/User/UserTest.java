package com.fdmgroup.User;

import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.UserRemover;

public class UserTest {

	private static Crud<Storable> crud;

	@BeforeClass
	public static void init() {
		crud = new Crud<Storable>();
	}

	@Test
	public void testCreateUser_Success() {

		User user = new User("matt", "pw", "matt", "guerron", new UserRoles(UserType.SYSTEMADMIN));
		crud.create(user);
		assertTrue(crud.read(123, user).getID() == 123);
	}

	//
	// @Test
	// public void testReadUser_Success() {
	// User user = (User) crud.read(122, new User());
	// assertTrue(user.getID() == 122);
	// }
	//
	@Test
	public void testDeleteUser_Success() {
		User user = (User) crud.read(122, new User());
		crud.delete(user);
	}

	@Test
	public void testUpdateUser_Success() {
		User user = (User) crud.read(140, new User());
		user.setFirstname("Matt");
		user.setLastname("G");
		crud.update(user);

		User user2 = (User) crud.read(140, new User());

		assertTrue(user2.getFirstname().equals("Matt"));
	}

	@Test
	public void test_delete_UserRemover() {
		UserRemover userRemover = new UserRemover(142, crud);
		assertTrue(userRemover.removeUser());
	}

	@AfterClass
	public static void after() {
		crud.getEm().close();
	}
}
