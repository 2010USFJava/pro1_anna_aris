package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.*;

public interface RequestDao {
	
	public void insertNewRequest(Request request) throws SQLException;
	
	public void updateRequest(Employee emp, Request request, String decision) throws SQLException;
	
	public List<Request> viewAllRequest() throws SQLException;

}
