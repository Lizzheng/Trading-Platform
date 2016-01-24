package com.fdmgroup.receivers;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;

public class AllUsersReturner {
	private Crud<Storable> crud;
	private List<Storable> storableUsers;
	private List<User> userList = new ArrayList<User>();

	public AllUsersReturner(Crud<Storable> crud) {
		this.crud = crud;
	}

	public List<Storable> returnAllUsersofTypeStorable() {
		storableUsers = crud.read("trading_user", new User());

		return storableUsers;
	}

	public List<User> returnUsers() {
		returnAllUsersofTypeStorable();
		for (Storable user : storableUsers) {
			userList.add((User) user);
		}
		return userList;
	}

}
