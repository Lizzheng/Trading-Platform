package com.fdmgroup.commands;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;
import com.fdmgroup.receivers.AccountViewer;

public class ReturnUserCommand implements ICommand {
	private AccountViewer accountViewer;
	private User user;

	public ReturnUserCommand(Crud<Storable> crud, int adminid) {
		this.accountViewer = new AccountViewer(crud, adminid);
	}

	@Override
	public void execute() {
		this.user = accountViewer.returnUser();

	}

	public User getUser() {
		return user;
	}
}
