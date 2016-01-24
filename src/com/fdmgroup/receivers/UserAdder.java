package com.fdmgroup.receivers;

import java.util.List;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;

public class UserAdder {
	private Crud<Storable> crud;
	private User userToBeAdded;

	public UserAdder(User userToBeAdded, Crud<Storable> crud) {
		this.crud = crud;
		this.userToBeAdded = userToBeAdded;
	}

	public boolean addUser() {
		List<Storable> userList = crud.read("trading_user", new User());
		for (Storable user : userList) {
			if (((User) user).getName().equals(userToBeAdded.getName())) {
				return false;
			}
		}
		crud.create(userToBeAdded);
		return true;
	}
}
