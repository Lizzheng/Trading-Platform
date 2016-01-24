package com.fdmgroup.User;

import org.junit.Test;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.AccountViewer;

public class AccountViewerTest {

	@Test
	public void testViewRequestsByAdminID() {
		Crud<Storable> crud = new Crud<Storable>();
		AccountViewer av = new AccountViewer(crud, 124);
		System.out.println(av.viewRequestsHandledByAdminID());
	}

}
