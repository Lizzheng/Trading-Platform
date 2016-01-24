package com.fdmgroup.request;

import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.commands.ActivityInvoker;
import com.fdmgroup.commands.ViewRequestByStatusCommand;
import com.fdmgroup.receivers.RequestViewerByStatus;

public class RequestViewerByStatusTest {

	static Crud<Storable> crud;

	@BeforeClass
	public static void before() {
		crud = new Crud<Storable>();
	}

	@Test
	public void test_completed() {
		RequestViewerByStatus requestViewer = new RequestViewerByStatus(
				RequestStatus.COMPLETED, crud);
		assertTrue(requestViewer.returnListByStatus().size() == 0);
	}

	@Test
	public void test_commandPending() {
		ViewRequestByStatusCommand command = new ViewRequestByStatusCommand(
				RequestStatus.PENDING, crud);
		ActivityInvoker invoker = new ActivityInvoker(command);
		invoker.invoke();
		assertTrue(command.getRequestList().size() == 11);
	}

	@AfterClass
	public static void after() {
		crud.getEm().close();
	}

}
