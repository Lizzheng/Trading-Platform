package com.fdmgroup.gateway;

import java.util.List;
import java.util.Set;
import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.User.User;
import com.fdmgroup.User.UserType;
import com.fdmgroup.commands.AddPermissionsCommand;
import com.fdmgroup.commands.AddUserCommand;
import com.fdmgroup.commands.BanUserCommand;
import com.fdmgroup.commands.DeleteCompletedRequestsCommand;
import com.fdmgroup.commands.RemovePermissionsCommand;
import com.fdmgroup.commands.RemoveUserCommand;
import com.fdmgroup.commands.ReturnAllUsersCommand;
import com.fdmgroup.commands.ReturnPermissionsCommand;
import com.fdmgroup.commands.UpdateRequestCommand;
import com.fdmgroup.commands.ViewRequestByStatusCommand;
import com.fdmgroup.commands.ViewRequestsCommand;
import com.fdmgroup.commands.ViewRequestsHandledCommand;
import com.fdmgroup.commands.ViewSingleRequestCommand;
import com.fdmgroup.request.Request;
import com.fdmgroup.request.RequestStatus;

public class AdminGateway extends Gateway {

	private Crud<Storable> crud = getCrud();

	public int executeDeleteCompletedRequests() {
		DeleteCompletedRequestsCommand deleteCompCommand = new DeleteCompletedRequestsCommand(
				crud);
		setInvokerCommand(deleteCompCommand);
		return deleteCompCommand.returnNumberDeleted();
	}

	public List<Storable> executeViewAllRequests() {
		ViewRequestsCommand viewRequestsCommand = new ViewRequestsCommand(crud);
		setInvokerCommand(viewRequestsCommand);
		return viewRequestsCommand.getRequestList();
	}

	public Request executeViewOneRequest(int requestID) {
		ViewSingleRequestCommand viewSingleRequestCommand = new ViewSingleRequestCommand(
				requestID, crud);
		setInvokerCommand(viewSingleRequestCommand);
		return viewSingleRequestCommand.returnDesiredRequest();
	}

	public boolean executeUpdateRequest(int requestID, int adminID,
			String description, RequestStatus status) {
		UpdateRequestCommand updateRequestCommand = new UpdateRequestCommand(
				requestID, adminID, description, status, crud);
		setInvokerCommand(updateRequestCommand);
		return updateRequestCommand.isUpdated();
	}

	public List<Storable> executeViewRequestsByStatus(RequestStatus status) {
		ViewRequestByStatusCommand viewRequestByStatusCommand = new ViewRequestByStatusCommand(
				status, crud);
		setInvokerCommand(viewRequestByStatusCommand);
		return viewRequestByStatusCommand.getRequestList();
	}

	public Set<UserType> executeReturnPermissionsByUserID(int userID) {
		ReturnPermissionsCommand returnPermissionsCommand = new ReturnPermissionsCommand(
				userID, crud);
		setInvokerCommand(returnPermissionsCommand);
		return returnPermissionsCommand.getPermissionsList();
	}

	public boolean executeBanUser(int userID) {
		BanUserCommand banUserCommand = new BanUserCommand(userID, crud);
		setInvokerCommand(banUserCommand);
		return banUserCommand.isBanned();
	}

	public boolean executeUnBan(int userID) {
		RemovePermissionsCommand removePermission = new RemovePermissionsCommand(
				userID, UserType.BANNED, crud);
		setInvokerCommand(removePermission);
		return removePermission.isPermissionRemoved();
	}

	public boolean executeAddUser(User user) {
		AddUserCommand addUserCommand = new AddUserCommand(user, crud);
		setInvokerCommand(addUserCommand);
		return addUserCommand.isAddedUserConfirmation();
	}

	public boolean executeRemoveUser(int userID) {
		RemoveUserCommand removeUserCommand = new RemoveUserCommand(userID,
				crud);
		setInvokerCommand(removeUserCommand);
		return removeUserCommand.isRemovedUserConfirmation();
	}

	public boolean executeAddPermissions(int userID, UserType userType) {
		AddPermissionsCommand addCommand = new AddPermissionsCommand(userID,
				userType, crud);
		setInvokerCommand(addCommand);
		return addCommand.isPermissionAdded();
	}

	public boolean executeRemovePermission(int userID, UserType userType) {
		RemovePermissionsCommand removePermission = new RemovePermissionsCommand(
				userID, userType, crud);
		setInvokerCommand(removePermission);
		return removePermission.isPermissionRemoved();
	}

	public List<Storable> executeReturnRequestsHandledByAdmin(int userID) {
		ViewRequestsHandledCommand viewRequestsCommand = new ViewRequestsHandledCommand(
				crud, userID);
		setInvokerCommand(viewRequestsCommand);
		return viewRequestsCommand.getRequestList();
	}

	public List<User> executeReturnAllUsers() {
		ReturnAllUsersCommand returnUsersCommand = new ReturnAllUsersCommand(
				crud);
		setInvokerCommand(returnUsersCommand);
		return returnUsersCommand.getUserList();
	}
}
