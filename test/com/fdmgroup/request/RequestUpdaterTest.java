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
import com.fdmgroup.commands.UpdateRequestCommand;
import com.fdmgroup.receivers.RequestUpdater;

public class RequestUpdaterTest {

	static Crud<Storable> crud;

	@BeforeClass
	public static void init() {
		crud = new Crud<Storable>();
	}

	@Test
	public void testUpdaterUpdatesRequest_Success() {
		RequestUpdater updater = new RequestUpdater(128, 140,
				"I dont want to make you a shareholder",
				RequestStatus.COMPLETED, crud);
		assertTrue(updater.updateRequest());
	}

	@Test
	public void testUpdaterFailsWithInvalidRequestID() {
		RequestUpdater updater = new RequestUpdater(124645645, 140,
				"I dont want to make you a shareholder",
				RequestStatus.COMPLETED, crud);
		assertFalse(updater.updateRequest());
	}

	@Test
	public void testUpdaterWithCommand_Success() {
		UpdateRequestCommand updateCommand = new UpdateRequestCommand(164, 124,
				"MAYBE", RequestStatus.IN_PROGRESS, crud);
		ActivityInvoker actIn = new ActivityInvoker(updateCommand);
		actIn.invoke();
		assertTrue(updateCommand.isUpdated());
	}
	
	@Test
	public void testUpdaterWithCommandFails(){
		UpdateRequestCommand updateCommand = new UpdateRequestCommand(16434, 124,
				"MAYBE", RequestStatus.IN_PROGRESS, crud);
		ActivityInvoker actIn = new ActivityInvoker(updateCommand);
		actIn.invoke();
		assertFalse(updateCommand.isUpdated());
	}

	@Ignore
	public void createRequest() {
		Request request = new Request("jesse", RequestType.PASSWORD_RESET,
				"doesn't matter");
		crud.create(request);
	}

	@AfterClass
	public static void after() {
		crud.getEm().close();
	}

}
