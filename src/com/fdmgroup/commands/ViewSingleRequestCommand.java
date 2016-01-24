package com.fdmgroup.commands;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.OneRequestViewer;
import com.fdmgroup.request.Request;

public class ViewSingleRequestCommand implements ICommand {

	private OneRequestViewer singleRequestViewer;
	private Request desiredRequest;

	public ViewSingleRequestCommand(int requestID, Crud<Storable> crud) {
		this.singleRequestViewer = new OneRequestViewer(requestID, crud);
	}

	@Override
	public void execute() {
		this.desiredRequest = singleRequestViewer.viewSingleRequest();
	}

	public Request returnDesiredRequest() {
		return desiredRequest;
	}

}
