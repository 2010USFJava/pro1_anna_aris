package com.revature.dao;

import java.sql.SQLException;

import com.revature.model.Request;

public interface RequestDao {
	
	public void insertNewRequest(Request request) throws SQLException;

}
