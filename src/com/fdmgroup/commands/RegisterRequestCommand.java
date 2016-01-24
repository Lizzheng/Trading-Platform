package com.fdmgroup.commands;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.receivers.RequestAccountRegistrar;
import com.fdmgroup.request.Request;
import com.fdmgroup.request.RequestType;

public class RegisterRequestCommand implements ICommand {

	private RequestAccountRegistrar rar;
	private boolean registerConfirmation;

	public RegisterRequestCommand(Request request, Crud<Storable> crud,
			RequestType requestType) {
		this.rar = new RequestAccountRegistrar(request, requestType, crud);
	}

	@Override
	public void execute() {
		this.registerConfirmation = rar.registerRequest();
	}

	public boolean requestIsRegistered() {
		return registerConfirmation;
	}

}
