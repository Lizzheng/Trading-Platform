package com.fdmgroup.receivers;

import java.sql.Date;
import java.util.Calendar;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.request.Request;
import com.fdmgroup.request.RequestStatus;
import com.fdmgroup.request.RequestType;

public class RequestAccountRegistrar {

	private Request request;
	private Crud<Storable> crud;
	private RequestType requestType;

	public RequestAccountRegistrar(Request request, RequestType requestType,
			Crud<Storable> crud) {
		this.request = request;
		this.crud = crud;
		this.requestType = requestType;
	}

	public RequestAccountRegistrar() {
	}

	public boolean registerRequest() {
		Calendar cal = Calendar.getInstance();
		Date currentTime = new Date(cal.getTime().getTime());
		request.setTimeStamp(currentTime);
		request.setRequestType(requestType);
		request.setStatus(RequestStatus.PENDING);
		crud.create(request);
		if (crud.read(request.getID(), request) != null)
			return true;
		return false;
	}

	public Request getRequest()
	{
		return request;
	}

	public void setRequest(Request request)
	{
		this.request = request;
	}

	public Crud<Storable> getCrud()
	{
		return crud;
	}

	public void setCrud(Crud<Storable> crud)
	{
		this.crud = crud;
	}

	public RequestType getRequestType()
	{
		return requestType;
	}

	public void setRequestType(RequestType requestType)
	{
		this.requestType = requestType;
	}
}
