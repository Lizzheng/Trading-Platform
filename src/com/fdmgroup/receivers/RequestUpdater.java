package com.fdmgroup.receivers;

import java.sql.Date;
import java.util.Calendar;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.request.Request;
import com.fdmgroup.request.RequestStatus;

public class RequestUpdater {

	private Crud<Storable> request_CRUD;
	private Request updatedRequest;
	private int requestID;
	private int adminID;
	private RequestStatus status;
	private String description;

	public RequestUpdater(int requestID, int adminID, String description,
			RequestStatus status, Crud<Storable> request_CRUD) {
		this.requestID = requestID;
		this.request_CRUD = request_CRUD;
		this.adminID = adminID;
		this.status = status;
		this.description = description;
	}

	public RequestUpdater() {
	}

	public boolean updateRequest() {
		updatedRequest = (Request) request_CRUD.read(requestID, new Request());
		if (updatedRequest != null) {
			Calendar cal = Calendar.getInstance();
			Date currentTime = new Date(cal.getTime().getTime());
			updatedRequest.setTimeStamp(currentTime);
			updatedRequest.setAdmin_ID(adminID);
			updatedRequest.setDescription(description);
			updatedRequest.setStatus(status);
			request_CRUD.update(updatedRequest);
			return true;
		}
		return false;
	}
}
