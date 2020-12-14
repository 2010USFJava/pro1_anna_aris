package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.*;

public interface RequestDao {
	
	public void insertNewRequest(Request request) throws SQLException;
	
	public void updateRequestStatus(Employee emp, Request request, String decision) throws SQLException;
	
	public void updateRequestAward(int requestId, int award, String reason) throws SQLException;
	
	public void cancelRequest(int requestId) throws SQLException;
	
	public List<Request> viewAllRequest() throws SQLException;
	
	public Request getRequestByRequestId(int requestId) throws SQLException;
	
}
