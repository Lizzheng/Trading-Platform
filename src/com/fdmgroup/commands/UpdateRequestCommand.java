package com.fdmgroup.commands;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.RequestUpdater;
import com.fdmgroup.request.RequestStatus;

public class UpdateRequestCommand implements ICommand {

	private RequestUpdater requestUpdater;
	private boolean updateSuccess;

	public UpdateRequestCommand(int requestID, int adminID, String description,
			RequestStatus status, Crud<Storable> crud) {
		this.requestUpdater = new RequestUpdater(requestID, adminID,
				description, status, crud);
	}

	@Override
	public void execute() {
		this.updateSuccess = requestUpdater.updateRequest();

	}

	public boolean isUpdated() {
		return updateSuccess;
	}

}
