package com.fdmgroup.commands;

import java.util.Set;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.UserType;
import com.fdmgroup.receivers.PermissionsReceiver;

public class ReturnPermissionsCommand implements ICommand {

	private PermissionsReceiver permissionsReceiver;
	private Set<UserType> permissionsList;

	public ReturnPermissionsCommand(int userID, Crud<Storable> crud) {
		this.permissionsReceiver = new PermissionsReceiver(userID, crud);
	}

	@Override
	public void execute() {
		this.permissionsList = permissionsReceiver.returnPermissions();
	}

	public Set<UserType> getPermissionsList() {
		return permissionsList;
	}

}
