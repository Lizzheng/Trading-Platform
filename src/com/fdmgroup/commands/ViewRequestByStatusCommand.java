package com.fdmgroup.commands;

import java.util.List;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.RequestViewerByStatus;
import com.fdmgroup.request.RequestStatus;

public class ViewRequestByStatusCommand implements ICommand {

	private RequestViewerByStatus rvbs;
	private List<Storable> requestList;

	public ViewRequestByStatusCommand(RequestStatus status, Crud<Storable> crud) {
		this.rvbs = new RequestViewerByStatus(status, crud);
	}

	@Override
	public void execute() {
		this.requestList = rvbs.returnListByStatus();
	}

	public List<Storable> getRequestList() {
		return requestList;
	}

}
