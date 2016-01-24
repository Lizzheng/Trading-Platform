package com.fdmgroup.gateway;

import java.util.List;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;
import com.fdmgroup.commands.ActivityInvoker;
import com.fdmgroup.commands.ICommand;
import com.fdmgroup.commands.RegisterRequestCommand;
import com.fdmgroup.commands.ReturnUserCommand;
import com.fdmgroup.commands.ViewRequestsMadeCommand;
import com.fdmgroup.login.LoginProcessor;
import com.fdmgroup.request.Request;
import com.fdmgroup.request.RequestType;

public class Gateway {

	private Crud<Storable> crud = new Crud<Storable>();
	private ActivityInvoker activityInvoker = new ActivityInvoker();
	private User loggedInUser;

	public void setInvokerCommand(ICommand command) {
		activityInvoker.setCommand(command);
		activityInvoker.invoke();
	}

	public boolean executeAddRequest(Request request, String requestType) {
		RegisterRequestCommand registerCommand = new RegisterRequestCommand(
				request, crud, RequestType.valueOf(requestType));
		setInvokerCommand(registerCommand);
		return registerCommand.requestIsRegistered();
	}

	public List<Storable> executeReturnRequestsMadeByUser(int userID) {
		ViewRequestsMadeCommand viewRequestsCommand = new ViewRequestsMadeCommand(
				crud, userID);
		setInvokerCommand(viewRequestsCommand);
		return viewRequestsCommand.getRequestList();
	}

	public User executeReturnUser(int userID) {
		ReturnUserCommand returnUserCommand = new ReturnUserCommand(crud,
				userID);
		setInvokerCommand(returnUserCommand);
		return returnUserCommand.getUser();
	}

	protected void finalize() {
		crud.getEm().close();
	}

	public User Login(String userName, String passWord) {
		LoginProcessor lp = new LoginProcessor(userName, passWord, crud);

		User result = lp.validateLogin();
		if (result != null) {
			setLoggedInUser(result);
			return result;
		} else
			return null;
	}

	public void Logout() {
		loggedInUser = null;
	}

	public Crud<Storable> getCrud() {
		return crud;
	}

	public void setCrud(Crud<Storable> crud) {
		this.crud = crud;
	}

	public ActivityInvoker getActivityInvoker() {
		return activityInvoker;
	}

	public void setActivityInvoker(ActivityInvoker activityInvoker) {
		this.activityInvoker = activityInvoker;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

}
