package com.fdmgroup.receivers;

import java.util.List;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.request.Request;
import com.fdmgroup.request.RequestStatus;

public class RequestViewerByStatus {

	private Crud<Storable> request_CRUD;
	private RequestStatus status;

	public RequestViewerByStatus(RequestStatus status,
			Crud<Storable> request_CRUD) {
		this.status = status;
		this.request_CRUD = request_CRUD;
	}

	public List<Storable> returnListByStatus() {
		return request_CRUD.readAllByStatus("request_table", status,
				new Request());
	}
}
