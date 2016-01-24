package com.fdmgroup.receivers;

import java.sql.Date;
import java.util.Calendar;
import java.util.Set;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;
import com.fdmgroup.User.UserRoles;
import com.fdmgroup.User.UserType;
import com.fdmgroup.request.Request;
import com.fdmgroup.request.RequestStatus;
import com.fdmgroup.request.RequestType;

public class UserBanner {

	private Crud<Storable> crud;
	private int userID;
	private User bannedUser;

	public UserBanner(int userID, Crud<Storable> crud) {
		this.userID = userID;
		this.crud = crud;
	}

	public boolean banUser() {
		bannedUser = (User) crud.read(userID, new User());
		if (bannedUser != null) {
			Set<UserType> userTypeList = bannedUser.getRoleType();
			if (!userTypeList.contains(UserType.BANNED)) {
				UserRoles banRole = new UserRoles(UserType.BANNED);
				bannedUser.setRole(banRole);
				crud.update(bannedUser);
				Request unbanRequest = new Request(bannedUser.getName(),
						RequestType.UNBAN, "Unban this person");
				unbanRequest.setStatus(RequestStatus.PENDING);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, 30);
				Date time = new Date(cal.getTime().getTime());
				unbanRequest.setTimeStamp(time);
				crud.create(unbanRequest);
				return true;
			}
		}
		return false;
	}

}
