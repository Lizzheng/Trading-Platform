package com.fdmgroup.receivers;

import java.util.Set;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;
import com.fdmgroup.User.UserRoles;
import com.fdmgroup.User.UserType;

public class PermissionModifier {
	private Crud<Storable> crud;
	private int UserID;
	private UserType userType;
	private User user;

	public PermissionModifier(int UserID, UserType type, Crud<Storable> crud) {
		this.crud = crud;
		this.UserID = UserID;
		this.userType = type;
	}

	public boolean addAccess() {
		user = (User) crud.read(UserID, new User());
		UserRoles role = null;
		if (user != null) {
			ConflictOfInterest conflict = new ConflictOfInterest();
			if (conflict.noConflictOfInterest(userType, user)) {
				role = new UserRoles(userType);
				user.setRole(role);
				crud.update(user);
				return true;
			}
		}
		return false;
	}

	public boolean removeAccess() {
		user = (User) crud.read(UserID, new User());
		if (user != null) {
			Set<UserRoles> userRolesList = user.getRole();
			for (UserRoles userRole : userRolesList) {
				if (userRole.getRoles().equals(userType)) {
					user.removeRole(userRole);
					crud.delete(userRole);
					crud.update(user);
					return true;
				}
			}
		}
		return false;
	}
}
