package com.fdmgroup.request;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fdmgroup.Crud.Storable;

@Entity
@Table(name = "REQUEST_TABLE")
public class Request implements Storable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REQUEST_ID")
	private int request_ID;

	@Column(name = "TIMESTAMP")
	private Date timeStamp;

	@Column(name = "REQUEST_TYPE")
	private String requestType;

	@Column(name = "USER_NAME")
	private String user_Name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "ASSIGNED_ADMIN")
	private int admin_ID;

	public Request() {
	}

	public Request(String user_Name, RequestType requestType, String description) {
		this.user_Name = user_Name;
		this.requestType = requestType.toString();
		this.description = description;
	}

	public int getID() {
		return request_ID;
	}
	
	public int getRequest_ID()
	{
		return request_ID;
	}

	public void setRequest_ID(int request_ID) {
		this.request_ID = request_ID;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType.toString();
	}

	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status.toString();
	}

	public int getAdmin_ID() {
		return admin_ID;
	}

	public void setAdmin_ID(int admin_ID) {
		this.admin_ID = admin_ID;
	}

}
