package com.fdmgroup.commands;

import java.util.List;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;
import com.fdmgroup.receivers.AllUsersReturner;

public class ReturnAllUsersCommand implements ICommand {

	private AllUsersReturner usersReturner;
	private List<User> userList;

	public ReturnAllUsersCommand(Crud<Storable> crud) {
		this.usersReturner = new AllUsersReturner(crud);
	}

	@Override
	public void execute() {
		this.userList = usersReturner.returnUsers();
	}

	public List<User> getUserList() {
		return userList;
	}

}
