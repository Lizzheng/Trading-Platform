package com.fdmgroup.commands;

import java.util.List;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.AccountViewer;

public class ViewRequestsHandledCommand implements ICommand {
	private AccountViewer accountViewer;
	private List<Storable> requestList;

	public ViewRequestsHandledCommand(Crud<Storable> crud, int adminid) {
		this.accountViewer = new AccountViewer(crud, adminid);
	}

	@Override
	public void execute() {
		this.requestList = accountViewer.viewRequestsHandledByAdminID();

	}

	public List<Storable> getRequestList() {
		return requestList;
	}
}
