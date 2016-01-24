package com.fdmgroup.login;

import java.util.List;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;

public class LoginProcessor
{
	private String username;
	private String password;
	private Crud<Storable> crud;

	public LoginProcessor(String username, String password, Crud<Storable> crud)
	{
		this.username = username;
		this.password = password;
		this.crud = crud;
	}

	public LoginProcessor()
	{
	}

	public User validateLogin()
	{

		List<Storable> ulist = crud.read("trading_user", new User());
		for (Storable u : ulist)
		{
			if (((User) u).getName().equals(username))
				if (((User) u).getPassword().equals(password))
				{
					return (User) u;
				}
		}
		return null;

	}

}
