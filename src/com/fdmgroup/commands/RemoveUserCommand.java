package com.fdmgroup.commands;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.UserRemover;

public class RemoveUserCommand implements ICommand {

	private UserRemover userRemover;
	private boolean removedUserConfirmation;

	public RemoveUserCommand(int id, Crud<Storable> userStorage) {
		this.userRemover = new UserRemover(id, userStorage);
	}

	@Override
	public void execute() {
		this.removedUserConfirmation = userRemover.removeUser();

	}

	public boolean isRemovedUserConfirmation() {
		return removedUserConfirmation;
	}

}
