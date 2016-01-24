package com.fdmgroup.receivers;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;

public class UserRemover {
	private Crud<Storable> crud;
	private int userIDtoBeRemoved;

	public UserRemover(int userIDtoBeRemoved, Crud<Storable> crud) {
		this.crud = crud;
		this.userIDtoBeRemoved = userIDtoBeRemoved;
	}

	public boolean removeUser() {
		User userToBeRemoved = (User) crud.read(userIDtoBeRemoved, new User());
		if (userToBeRemoved != null) {
			crud.delete(userToBeRemoved);
			return true;
		}
		return false;
	}
}
