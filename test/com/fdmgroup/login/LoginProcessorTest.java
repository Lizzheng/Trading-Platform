package com.fdmgroup.login;

import static org.junit.Assert.*;
import org.junit.Test;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;

public class LoginProcessorTest
{

	@Test
	public void testUserLogin_Success()
	{
		Crud<Storable> crud = new Crud<Storable>();
		LoginProcessor lp = new LoginProcessor("will", "pw", crud);
		
		assertTrue(lp.validateLogin() != null);
	}

}
