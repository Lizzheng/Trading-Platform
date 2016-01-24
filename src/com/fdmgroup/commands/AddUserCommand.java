package com.fdmgroup.commands;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;
import com.fdmgroup.receivers.UserAdder;

public class AddUserCommand implements ICommand {

	private UserAdder userAdder;
	private boolean addedUserConfirmation;

	public AddUserCommand(User user, Crud<Storable> userStorage) {
		this.userAdder = new UserAdder(user, userStorage);
	}

	@Override
	public void execute() {
		this.addedUserConfirmation = userAdder.addUser();
	}

	public boolean isAddedUserConfirmation() {
		return addedUserConfirmation;
	}

}
