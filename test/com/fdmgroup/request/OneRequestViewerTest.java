package com.fdmgroup.request;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.commands.ActivityInvoker;
import com.fdmgroup.commands.ViewSingleRequestCommand;
import com.fdmgroup.receivers.OneRequestViewer;

public class OneRequestViewerTest {

	private static Crud<Storable> crud;

	@BeforeClass
	public static void init() {
		crud = new Crud<Storable>();
	}

	@Ignore
	public void fail_test1() {
		OneRequestViewer requestViewer = new OneRequestViewer(600, crud);
		assertFalse(requestViewer.viewSingleRequest() != null);
	}

	@Test
	public void success_test1() {
		OneRequestViewer requestViewer = new OneRequestViewer(43, crud);
		assertTrue(requestViewer.viewSingleRequest() != null);
	}

	@Test
	public void command_test_fail1() {
		ViewSingleRequestCommand command = new ViewSingleRequestCommand(456,
				crud);
		ActivityInvoker activityInvoker = new ActivityInvoker(command);
		activityInvoker.invoke();
		assertFalse(command.returnDesiredRequest() != null);
	}

	@Test
	public void command_test_success1() {
		ViewSingleRequestCommand command = new ViewSingleRequestCommand(51,
				crud);
		ActivityInvoker activityInvoker = new ActivityInvoker(command);
		activityInvoker.invoke();
		assertTrue(command.returnDesiredRequest() != null);
	}

	@AfterClass
	public static void after() {
		crud.getEm().close();
	}
}
