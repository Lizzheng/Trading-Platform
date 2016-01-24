package com.fdmgroup.receivers;

import com.fdmgroup.User.User;
import com.fdmgroup.User.UserType;

public class ConflictOfInterest
{
	public boolean noConflictOfInterest(UserType type, User user)
	{
		if (user.getRoleType().contains(UserType.BANNED) || user.getRoleType().contains(type))
			return false;
		if (type.equals(UserType.SYSTEMADMIN))
		{
			if (!(user.getRoleType().contains(UserType.BROKER) || user.getRoleType().contains(
					UserType.EXCHANGE_MANAGER)))
				return true;
		}
		if (type.equals(UserType.BROKER))
		{
			if (!(user.getRoleType().contains(UserType.EXCHANGE_MANAGER) || user.getRoleType()
					.contains(UserType.SYSTEMADMIN)))
				return true;
		}
		if (type.equals(UserType.EXCHANGE_MANAGER))
		{
			if (!(user.getRoleType().contains(UserType.BROKER) || user.getRoleType().contains(
					UserType.SYSTEMADMIN)))
				return true;
		}
		if (type.equals(UserType.SHAREHOLDER))
			return true;
		return false;
	}
}
