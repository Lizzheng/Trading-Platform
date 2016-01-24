package com.fdmgroup.commands;

import java.util.List;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.AccountViewer;

public class ViewRequestsMadeCommand implements ICommand {

	private AccountViewer accountViewer;
	private List<Storable> requestList;

	public ViewRequestsMadeCommand(Crud<Storable> crud, int userid) {
		this.accountViewer = new AccountViewer(crud, userid);
	}

	@Override
	public void execute() {
		this.requestList = accountViewer.viewRequestsMadeByUser();

	}

	public List<Storable> getRequestList() {
		return requestList;
	}

}
