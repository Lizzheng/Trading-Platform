package com.fdmgroup.receivers;

import java.util.List;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;
import com.fdmgroup.request.Request;

public class AccountViewer {

	private Crud<Storable> crud;
	private int userid;
	private User user;

	public AccountViewer(Crud<Storable> crud, int adminid) {
		this.crud = crud;
		this.userid = adminid;
	}

	public User returnUser() {
		user = (User) crud.read(userid, new User());
		return user;
	}

	public List<Storable> viewRequestsHandledByAdminID() {
		return crud.readByAdminID(userid, new Request());
	}

	public List<Storable> viewRequestsMadeByUser() {
		returnUser();
		return crud.readByAdminUserName(user.getName(), new Request());
	}
}
