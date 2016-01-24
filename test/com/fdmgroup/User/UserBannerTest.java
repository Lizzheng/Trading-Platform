package com.fdmgroup.User;

import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.commands.ActivityInvoker;
import com.fdmgroup.commands.BanUserCommand;
import com.fdmgroup.receivers.UserBanner;

public class UserBannerTest {

	static Crud<Storable> crud;

	@BeforeClass
	public static void init() {
		crud = new Crud<Storable>();
	}

	@Ignore
	public void testUserBannerSuccess() {
		UserBanner userban = new UserBanner(140, crud);

		assertTrue(userban.banUser());
	}

	@Test
	public void testUserBannerCommandFail() {
		BanUserCommand banUserCommand = new BanUserCommand(140, crud);
		ActivityInvoker invoker = new ActivityInvoker(banUserCommand);
		invoker.invoke();
		assertTrue(banUserCommand.isBanned());
	}

	// @Test
	// public void unBan_Matt() {
	// User user = (User) crud.read(140, new User());
	// UserBanner banner = new UserBanner(140, crud);
	// banner.unBanUser(user);
	// }

	@AfterClass
	public static void after() {
		crud.getEm().close();
	}

}
