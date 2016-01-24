package com.fdmgroup.receivers;

import java.util.List;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.request.Request;

public class RequestViewer {

	private Crud<Storable> request_CRUD;
	
	public RequestViewer(){}
	
	public RequestViewer(Crud<Storable> request_CRUD) {
		this.request_CRUD = request_CRUD;
	}

	public List<Storable> returnAllRequests() {
		return request_CRUD.read("request_table", new Request());
	}
}
