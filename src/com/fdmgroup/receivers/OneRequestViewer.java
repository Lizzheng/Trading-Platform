package com.fdmgroup.receivers;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.request.Request;

public class OneRequestViewer {

	private Crud<Storable> request_CRUD;
	private int requestID;

	public OneRequestViewer(int requestID, Crud<Storable> request_CRUD) {
		this.request_CRUD = request_CRUD;
		this.requestID = requestID;
	}

	public Request viewSingleRequest() {
		return (Request) request_CRUD.read(requestID, new Request());
	}

}
