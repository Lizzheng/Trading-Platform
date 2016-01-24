package com.fdmgroup.User;

import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.AllUsersReturner;

public class AllUsersReturnerTest {
	static Crud<Storable> crud;

	@BeforeClass
	public static void init() {
		crud = new Crud<Storable>();
	}

	@Ignore
	public void test_returnAllUsers_success() {
		AllUsersReturner returner = new AllUsersReturner(crud);
		assertTrue(returner.returnUsers() != null);
	}

	@Ignore
	public void test_returnAllUsers_success2() {
		AllUsersReturner returner = new AllUsersReturner(crud);
		assertTrue(returner.returnUsers().size() == 6);
	}

	@Test
	public void test_returnAllUsers_success3() {
		AllUsersReturner returner = new AllUsersReturner(crud);
		assertTrue((returner.returnUsers().get(0)).getName().equals("jesse"));
	}

	@AfterClass
	public static void after() {
		crud.getEm().close();
	}

}
