package com.fdmgroup.receivers;

import java.util.Set;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;
import com.fdmgroup.User.UserType;

public class PermissionsReceiver {

	private Crud<Storable> crud;
	private int userID;

	public PermissionsReceiver(int userID, Crud<Storable> crud) {
		this.crud = crud;
		this.userID = userID;
	}

	public Set<UserType> returnPermissions() {
		User user = (User) crud.read(userID, new User());
		return user.getRoleType();
	}
}
