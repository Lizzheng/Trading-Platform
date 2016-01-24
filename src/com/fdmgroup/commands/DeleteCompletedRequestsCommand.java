package com.fdmgroup.commands;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.CompletedRequestDeleter;

public class DeleteCompletedRequestsCommand implements ICommand {

	private CompletedRequestDeleter crd;
	private int numberDeleted;

	public DeleteCompletedRequestsCommand(Crud<Storable> crud) {
		this.crd = new CompletedRequestDeleter(crud);
	}

	@Override
	public void execute() {
		crd.removeCompletedRequests();
		this.numberDeleted = crd.getNumberDeleted();
	}

	public int returnNumberDeleted() {
		return numberDeleted;
	}

}
