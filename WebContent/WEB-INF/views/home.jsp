<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	session="true" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<c:import url="com.fdmgroup.User.UserType"></c:import>
<c:set var="addusertype"
	value="<%=com.fdmgroup.User.UserType.values()%>" />
<c:import url="com.fdmgroup.request.RequestType"></c:import>
<c:set var="addrequesttype"
	value="<%=com.fdmgroup.request.RequestType.values()%>" />
<c:import url="com.fdmgroup.request.RequestStatus" />
<c:set var="requeststatus"
	value="<%=com.fdmgroup.request.RequestStatus.values()%>" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>
<link rel="stylesheet" type="text/css" href="panelStyles.css" />
</head>
<body>
	<c:forEach var="role" items="${LoggedInUser.role}">
		<c:if test="${role.roles == 'SYSTEMADMIN'}">
			<c:set var="ADMINUSER" value="true" />
		</c:if>
	</c:forEach>
	<div class="header1">
		<div class="fdmcorelogo">
			<img src="fdm-logo2.gif" alt="FDM Logo">
		</div>
		<div class="headertitle">
			<h1>Trading Platform</h1>
		</div>
	</div>
	<div class="header2"></div>
	<div class="centerPanel">
		<c:choose>
			<c:when test="${ERROR}">
				<div class="loginform">${errorMessage}</div>
			</c:when>
			<c:when test="${HOME}">
				<div class="loginform">
					<form action="login" method="post">
						Username: <input name="username" type="text" /><br /> Password:
						<input name="password" type="password" /><br /> <input
							type="submit" value="Log in" />
					</form>
				</div>
			</c:when>
			<c:when test="${LOGIN}">
				<meta http-equiv="refresh" content="5; url=returnHome" />
				<div class="loginform">
					<c:choose>
						<c:when test='${sessionScope.LoggedInUser != null}'>
						logged in
					</c:when>
						<c:otherwise>
						not logged in.
					</c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:when test="${ADDUSER}">
				<c:choose>
					<c:when test="${!ADMINUSER}">
						<div>Authorized users only!</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:when>
					<c:otherwise>
						<div class="loginform">
							<form action='createdUser' method='post'>
								<fieldset>
									<legend> Add User Form</legend>
									Username: <input type='text' name='username' /><br />
									Password: <input type='text' name='password' /><br /> First
									Name: <input type='text' name='first_name' /><br /> Last
									Name: <input type='text' name='last_name' /><br /> User Role:
									<select name="userType">
										<c:forEach var="userType" items="${addusertype}">
											<option>${userType}</option>
										</c:forEach>
									</select> <input type='submit' value='Submit' />
								</fieldset>
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${CREATEDUSER}">
				<meta http-equiv="refresh" content="5; url=returnHome" />
				<div class="loginform">
					<c:choose>
						<c:when test="${ifCreated}">
                                  User created successfully
                           </c:when>
						<c:otherwise>
                                  Invalid information.  Try again.
                           </c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:when test="${REMOVEUSER}">
				<c:choose>
					<c:when test="${!ADMINUSER}">
						<div>Authorized users only!</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:when>
					<c:otherwise>
						<div class="loginform">
							<form action="userDeleted">
								User ID: <input type="text" name="userID" /> <input
									type="submit" value="Remove" />
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${USERDELETED}">
				<meta http-equiv="refresh" content="5; url=returnHome" />
				<div class="loginform">
					<c:choose>
						<c:when test="${deletedSuccess}">
                                  User Successfully Removed
                           </c:when>
						<c:otherwise>
                                  User Remove Unsuccessful. Please enter a valid ID to remove
                           </c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:when test="${ADDREQUEST}">
				<div class="loginform">
					<form action="submitrequest">
						Username: <br /> <input type="text" name="username" /><br />
						Select Request Type: <br /> <select name="requestType">
							<c:forEach var="i" items="${addrequesttype}">
								<option>${i}</option>
							</c:forEach>
						</select><br /> Description: <br /> <input type="text" name="description" /><br />
						<input type="submit" value="ADD REQUEST" />
					</form>
				</div>
			</c:when>
			<c:when test="${SUBMITREQUEST}">
				<c:choose>
					<c:when test="${addRequest}">
						<div class="loginform">Thank you for your request! A System
							Admin will tend to your Request as soon as Possible</div>
					</c:when>
					<c:otherwise>
						<div class="loginform">Request Not Submitted.</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${BANUSER}">
				<c:choose>
					<c:when test="${!ADMINUSER}">
						<div>Authorized users only!</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:when>
					<c:otherwise>
						<div class="loginform">
							<form action="userIsBanned">
								User ID: <input type="text" name="userID" /> <input
									type="submit" value="BAN NOW!" />
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${USERISBANNED}">
				<meta http-equiv="refresh" content="5; url=returnHome" />
				<div class="loginform">
					<c:choose>
						<c:when test="${isBanned}">
                                  User has been banned for 30 days
                           </c:when>
						<c:otherwise>
                                  User Ban failed. Enter valid ID number or user already banned. 
                           </c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:when test="${DELETECOMPLETEREQUESTS}">
				<c:choose>
					<c:when test="${!ADMINUSER}">
						<div>Authorized users only!</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:when>
					<c:otherwise>
						<div class="loginform">
							<form action="executeDeleteCompletedRequests">
								Are you sure you want delete all completed requests? <input
									type="submit" value="OK" />
							</form>
							<form action="returnHome">
								<input type="submit" value="Cancel" />
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${EXECUTEDELETECOMPLETEDREQUESTS}">
				<meta http-equiv="refresh" content="5; url=returnHome" />
				<div class="loginform">
					<div>${completedRequestNumberDeleted}completedrequestshave
						been deleted.</div>
					<a href="returnHome">Return Home</a>
				</div>
			</c:when>
			<c:when test="${UNBANUSER}">
				<c:choose>
					<c:when test="${!ADMINUSER}">
						<div>Authorized users only!</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:when>
					<c:otherwise>
						<div class="loginform">
							<form action="permissionRestored">
								User ID: <input type="text" name="userID"> <input
									type="submit" value="UnBan">
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${PERMISSIONRESTORED}">
				<meta http-equiv="refresh" content="5; url=returnHome" />
				<div class="loginform">
					<c:choose>
						<c:when test="${unbanSuccess}">
                                  User Permissions Successfully Restored
                           </c:when>
						<c:otherwise>
                                  Could not unban a user with given ID
                           </c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:when test="${VIEWALLREQUESTS}">
				<c:choose>
					<c:when test="${!ADMINUSER}">
						<div>Authorized users only!</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:when>
					<c:otherwise>
						<div>
							<table>
								<tr>
									<td>Request ID</td>
									<td>Time Stamp</td>
									<td>Request Type</td>
									<td>User name</td>
									<td>Description</td>
									<td>Status</td>
									<td>Admin ID</td>
								</tr>
								<c:forEach var="request" items="${requestList}">
									<tr>
										<td>${request.request_ID}</td>
										<td>${request.timeStamp}</td>
										<td>${request.requestType}</td>
										<td>${request.user_Name}</td>
										<td>${request.description}</td>
										<td>${request.status}</td>
										<td>${request.admin_ID}</td>
									</tr>
								</c:forEach>
							</table>
							<br> <br>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${RETURNHOME}">
				<div class="loginform">
					<h1>WELCOME ${firstname} ${lastname}!</h1>
					<h2>WELCOME TO THE FDM TRADING PLATFORM</h2>
				</div>
			</c:when>
			<c:when test="${VIEWONEREQUEST}">
				<c:choose>
					<c:when test="${!ADMINUSER}">
						<div>Authorized users only!</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:when>
					<c:otherwise>
						<div class="loginform">
							<form action="submitToViewOneRequest">
								Enter Request ID: <input type="text" name="requestID" /> <input
									type="submit" value="SUBMIT" />
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${SUBMITVIEWONEREQUEST}">
				<div>
					<c:choose>
						<c:when test="${request != null }">
							<table>
								<tr>
									<td>Request ID</td>
									<td>Time Stamp</td>
									<td>Request Type</td>
									<td>User name</td>
									<td>Description</td>
									<td>Status</td>
									<td>Admin ID</td>
								</tr>
								<tr>
									<td>${request.request_ID}</td>
									<td>${request.timeStamp}</td>
									<td>${request.requestType}</td>
									<td>${request.user_Name}</td>
									<td>${request.description}</td>
									<td>${request.status}</td>
									<td>${request.admin_ID}</td>
								</tr>
							</table>
						</c:when>
						<c:otherwise>
							<div>There is no such request ID.</div>
						</c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:when test="${ADDPERMISSIONS}">
				<c:choose>
					<c:when test="${!ADMINUSER}">
						<div>Authorized users only!</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:when>
					<c:otherwise>
						<div class="loginform">
							<form action="userPermissionsAdded">
								User ID: <input type="text" name="userID"><br /> User
								Role: <select name="userType">
									<c:forEach var="userType" items="${addusertype}">
										<option>${userType}</option>
									</c:forEach>
								</select> <input type="submit" value="Add Permission">
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${USERPERMISSIONSADDED}">
				<meta http-equiv="refresh" content="5; url=returnHome" />
				<div class="loginform">
					<c:choose>
						<c:when test="${permissionAdded}">
                                  Permission added for the desired User    
                           </c:when>
						<c:otherwise>
                                  Permission could not be added. Possible conflict of interest. 
                           </c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:when test="${UPDATEREQUEST}">
				<c:choose>
					<c:when test="${!ADMINUSER}">
						<div>Authorized users only!</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:when>
					<c:otherwise>
						<div class="loginform">
							<form action="submitUpdatedRequest">
								Enter Request ID: <input type="text" name="requestID" /> <br />
								Enter Request Status: <select name="status">
									<c:forEach var="i" items="${requeststatus}">
										<option>${i}</option>
									</c:forEach>
								</select><br /> Enter Request Description: <input type="text"
									name="description" /> <br /> <input type="submit"
									value="SUBMIT" />
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${SUBMITUPDATEDREQUEST}">
				<meta http-equiv="refresh" content="5; url=returnHome" />
				<div class="loginform">
					<c:choose>
						<c:when test="${updaterequest}">
							<div>Request #${param.requestID} has been updated.</div>
						</c:when>
						<c:otherwise>
							<div>There is no such request ID. Please create a new
								request. UPDATE FAIL.</div>
						</c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:when test="${REMOVEUSERPERMISSIONS}">
				<c:choose>
					<c:when test="${!ADMINUSER}">
						<div>Authorized users only!</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:when>
					<c:otherwise>
						<div class="loginform">
							<form action="permissionsRemoved">
								User ID: <input type="text" name="userID"> User Role: <select
									name="userType">
									<c:forEach var="userType" items="${addusertype}">
										<option>${userType}</option>
									</c:forEach>
								</select> <input type="submit" value="Remove Permission">
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${PERMISSIONSREMOVED}">
				<meta http-equiv="refresh" content="5; url=returnHome" />
				<div class="loginform">
					<c:choose>
						<c:when test="${permissionsRemoved}">
                                  Permission removed for the desired User  
                           </c:when>
						<c:otherwise>
                                  Permission could not be removed. User does not have that permission. 
                           </c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:when test="${VIEWREQUESTSBYSTATUS}">
				<c:choose>
					<c:when test="${!ADMINUSER}">
						<div>Authorized users only!</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:when>
					<c:otherwise>
						<div class="loginform">
							<form action="submitViewRequestByStatus">
								Enter Status: <select name="status">
									<c:forEach var="i" items="${requeststatus}">
										<option>${i}</option>
									</c:forEach>
								</select> <br /> <input type="submit" value="SUBMIT" />
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${SUBMITVIEWREQUESTBYSTATUS}">

				<c:choose>
					<c:when test="${viewRequests !=null}">
						<table>
							<tr>
								<th>Request ID</th>
								<th>Time Stamp</th>
								<th>Request Type</th>
								<th>User name</th>
								<th>Description</th>
								<th>Status</th>
								<th>Admin ID</th>
							</tr>
							<c:forEach var="request" items="${viewRequests}">
								<tr>
									<td>${request.request_ID}</td>
									<td>${request.timeStamp}</td>
									<td>${request.requestType}</td>
									<td>${request.user_Name}</td>
									<td>${request.description}</td>
									<td>${request.status}</td>
									<td>${request.admin_ID}</td>
								</tr>
							</c:forEach>
						</table>
					</c:when>
					<c:otherwise>
						<div>There are no requests with such status.</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${RETURNPERMISSIONS}">
				<c:choose>
					<c:when test="${!ADMINUSER}">
						<div>Authorized users only!</div>
						<meta http-equiv="refresh" content="5; url=returnHome" />
					</c:when>
					<c:otherwise>
						<div class="loginform">
							<form action="permissionsReturned">
								User ID: <input type="text" name="userID"> <input
									type="submit" value="View Permissions">
							</form>
						</div>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="${PERMISSIONSRETURNED}">
				<div class="loginform">
					<table>
						<tr>
							<th>Roles</th>
						</tr>
						<c:forEach var="role" items="${permissionsReturned}">
							<tr>
								<td>"${role}"</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:when>
			<c:when test="${ACCOUNT}">
                     User Information:
                     <table>
					<tr>
						<th>User ID</th>
						<th>Username</th>
						<th>Password</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>User Type</th>
					</tr>

					<tr>
						<td>${sessionScope.LoggedInUser.idNumber}</td>
						<td>${sessionScope.LoggedInUser.name}</td>
						<td>${sessionScope.LoggedInUser.password}</td>
						<td>${sessionScope.LoggedInUser.firstname}</td>
						<td>${sessionScope.LoggedInUser.lastname}</td>
						<c:forEach var="type"
							items="${sessionScope.LoggedInUser.roleType}">
							<td>${type}</td>
						</c:forEach>
					<tr>
				</table>

				<br>

				<c:if test="${requestsMade !=null}">
					<b>Requests Made By You:</b>
					<br />
					<table>

						<tr>
							<td>Request ID</td>
							<td>Time Stamp</td>
							<td>Request Type</td>
							<td>User name</td>
							<td>Description</td>
							<td>Status</td>
							<td>Admin ID</td>
						</tr>
						<c:forEach var="request" items="${requestsMade}">
							<tr>
								<td>${request.request_ID}</td>
								<td>${request.timeStamp}</td>
								<td>${request.requestType}</td>
								<td>${request.user_Name}</td>
								<td>${request.description}</td>
								<td>${request.status}</td>
								<td>${request.admin_ID}</td>
							</tr>
						</c:forEach>
					</table>
					<br>
				</c:if>
				<c:if test="${requestsHandled !=null }">
					<b>Requests you are handling:</b>
					<br />
					<table>

						<tr>
							<td>Request ID</td>
							<td>Time Stamp</td>
							<td>Request Type</td>
							<td>User name</td>
							<td>Description</td>
							<td>Status</td>
							<td>Admin ID</td>
						</tr>
						<c:forEach var="request" items="${requestsHandled}">
							<tr>
								<td>${request.request_ID}</td>
								<td>${request.timeStamp}</td>
								<td>${request.requestType}</td>
								<td>${request.user_Name}</td>
								<td>${request.description}</td>
								<td>${request.status}</td>
								<td>${request.admin_ID}</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</c:when>
			<c:when test="${VIEWALLUSERS}">
				<table>
					<tr>
						<th>User ID</th>
						<th>Username</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>User Type</th>
					</tr>
					<c:forEach var="userinfo" items="${usersList}">
						<tr>
							<td>${userinfo.idNumber}</td>
							<td>${userinfo.name}</td>
							<td>${userinfo.firstname}</td>
							<td>${userinfo.lastname}</td>
							<c:forEach var="type" items="${userinfo.roleType}">
								<td>${type}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</c:when>
		</c:choose>
	</div>

	<div class="leftPanel">
		<div class="leftPanelText">
			<ul>
				<li><h2>Menu</h2></li>
				<li><a href="returnHome">Home</a></li>
				<li><c:choose>
						<c:when test='${sessionScope.LoggedInUser != null}'>
							<li><a href="account">${sessionScope.LoggedInUser.name}</a></li>
							<c:forEach var="role" items="${LoggedInUser.role}">
								<c:if test="${role.roles == 'SYSTEMADMIN'}">	
									<br/>
									<li><a href="viewAllUsers">View All Users</a></li>
									<li><a href="addUser">Add User</a></li>
									<li><a href="removeUser">Remove User</a></li>
									<li><a href="banUser">Ban User</a></li>
									<li><a href="unbanUser">Unban User</a></li>
									<li><a href="addPermissions">Add Permissions</a></li>
									<li><a href="removeUserPermissions">Remove User
											Permissions</a></li>
									<br/>
									<li><a href="viewAllRequests">View All Requests</a></li>
									<li><a href="viewOneRequest">View One Request</a></li>
									<li><a href="viewRequestsByStatus">View Request by
											Status</a></li>
									<li><a href="updateRequest">Update Request</a></li>
									<li><a href="deleteCompletedRequests">Delete Completed
											Requests</a></li>
									<br/>
									<li><a href="UserLog.html">View User Logs</a></li>
									<li><a href="AdminLog.html">View Admin Logs</a></li>
									<br/>								
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div>Not logged in</div>
						</c:otherwise>
					</c:choose></li>
				<li><a href="addrequest">Add Request</a></li>
				<c:if test='${sessionScope.LoggedInUser != null}'>
				<li><a href="logout">Log Out</a></li>
				</c:if>
			</ul>
		</div>
	</div>

	<div class="footer">Copyright © 2014, Java 8, New York, NY</div>

</body>

</html>