package com.fdmgroup.receivers;

import java.util.List;

import com.fdmgroup.Crud.Crud;
import com.fdmgroup.Crud.Storable;
import com.fdmgroup.request.Request;

public class CompletedRequestDeleter {

	private Crud<Storable> crud;
	private int numberDeleted;

	public CompletedRequestDeleter(Crud<Storable> request_CRUD) {
		this.crud = request_CRUD;
	}

	public List<Storable> returnAllRequests() {
		RequestViewer requestViewer = new RequestViewer(crud);
		return requestViewer.returnAllRequests();
	}

	public void removeCompletedRequests() {
		List<Storable> former_RequestList = returnAllRequests();
		for (Storable request : former_RequestList) {
			if (((Request) request).getStatus().equals("COMPLETED")) {
				crud.delete(request);
				numberDeleted++;
			}
		}
	}

	public int getNumberDeleted() {
		return numberDeleted;
	}

}
