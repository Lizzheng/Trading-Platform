package com.fdmgroup.commands;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.UserType;
import com.fdmgroup.receivers.PermissionModifier;

public class AddPermissionsCommand implements ICommand{

	private PermissionModifier permission;
	private boolean permissionAddedSuccess;
	
	public AddPermissionsCommand(int userID, UserType userType,
			Crud<Storable> crud){
		this.permission = new PermissionModifier(userID, userType, crud); 
	}

	@Override
	public void execute() {
		this.permissionAddedSuccess = permission.addAccess();	
	}
	
	public boolean isPermissionAdded(){
		return permissionAddedSuccess;
	}
}
