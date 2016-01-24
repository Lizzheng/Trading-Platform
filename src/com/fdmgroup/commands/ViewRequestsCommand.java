package com.fdmgroup.commands;

import java.util.List;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.RequestViewer;

public class ViewRequestsCommand implements ICommand {

	private RequestViewer requestViewer;
	private List<Storable> requestList;

	public ViewRequestsCommand(Crud<Storable> crud) {
		this.requestViewer = new RequestViewer(crud);
	}

	@Override
	public void execute() {
		this.requestList = requestViewer.returnAllRequests();
	}

	public List<Storable> getRequestList() {
		return requestList;
	}

}
