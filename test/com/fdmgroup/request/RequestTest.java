package com.fdmgroup.request;

import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.CompletedRequestDeleter;
import com.fdmgroup.receivers.RequestAccountRegistrar;

public class RequestTest {

	static Crud<Storable> crud;
	Request request;

	@BeforeClass
	public static void before() {
		crud = new Crud<Storable>();
	}

	@Before
	public void init() {
		request = new Request();
	}

	@Test
	public void testAccountRegistrarAdded_Success() {
		request.setDescription("strange error message on my program");
		request.setUser_Name("liz");
		RequestAccountRegistrar rr = new RequestAccountRegistrar(request,
				RequestType.BUG_REPORT, crud);
		assertTrue(rr.registerRequest());
	}

	@Ignore
	public void testFinish() {
		request.setDescription("Want to be a broker");
		request.setUser_Name("jesse");
		request.setStatus(RequestStatus.PENDING);
		crud.create(request);
	}

	@Test
	public void testDeleteFinish() {
		CompletedRequestDeleter completedRequestedDeleter = new CompletedRequestDeleter(
				crud);
		completedRequestedDeleter.removeCompletedRequests();
		// assertTrue(completedRequestedDeleter.returnAllRequests().size() ==
		// 2);
		// assertTrue(completedRequestedDeleter.getNumberDeleted() == 3);
	}

	@Test
	public void readAllTest() {
		assertTrue(crud.read("request_table", new Request()).size() == 14);
	}

	@AfterClass
	public static void after() {
		crud.getEm().close();
	}
}
