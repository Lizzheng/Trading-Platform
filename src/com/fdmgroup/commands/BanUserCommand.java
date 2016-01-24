package com.fdmgroup.commands;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.UserBanner;

public class BanUserCommand implements ICommand {

	private UserBanner userBanner;
	private boolean banUserSuccess;

	public BanUserCommand(int userID, Crud<Storable> crud) {
		this.userBanner = new UserBanner(userID, crud);
	}

	@Override
	public void execute() {
		this.banUserSuccess = userBanner.banUser();
	}

	public boolean isBanned() {
		return banUserSuccess;
	}

}
