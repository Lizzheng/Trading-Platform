package com.fdmgroup.commands;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.UserType;
import com.fdmgroup.receivers.PermissionModifier;

public class RemovePermissionsCommand implements ICommand{

	private PermissionModifier permission;
	private boolean permissionRemovedSuccess;

	public RemovePermissionsCommand(int userID, UserType userType,
			Crud<Storable> crud) {
		this.permission = new PermissionModifier(userID, userType, crud);
	}

	@Override
	public void execute() {
		this.permissionRemovedSuccess = permission.removeAccess();
	}
	
	public boolean isPermissionRemoved(){
		return permissionRemovedSuccess;
	}

}
