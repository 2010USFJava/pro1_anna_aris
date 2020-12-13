package com.revature.daoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.LoginDao;
import com.revature.dao.RequestDao;
import com.revature.model.Employee;
import com.revature.model.Request;
import com.revature.util.DBConnection;

public class RequestDaoImpl implements RequestDao{
	public static DBConnection db = DBConnection.getInstance();
	
	@Override
	public void insertNewRequest(Request req) throws SQLException {
		Connection connect = db.getConnection();
		String insertQuery = "insert into request values(default, ?, now(), ?,?,?,?,?,?,?,?,?)";
		PreparedStatement prepStmt = connect.prepareStatement(insertQuery);
 
			prepStmt.setInt(1, req.getEmployeeId());
			prepStmt.setString(2, req.getEventDate());
			prepStmt.setString(3, req.getEventTime());
			prepStmt.setInt(4, req.getCost());
			prepStmt.setString(5, req.getStreet());
			prepStmt.setString(6, req.getCity());
			prepStmt.setString(7, req.getState());
			prepStmt.setString(8, req.getZip());
			prepStmt.setString(9, req.getEventType());
			prepStmt.setString(10, req.getEventDescription());
			prepStmt.executeUpdate();
	}

	@Override
	public void updateRequest(Employee emp, Request reqForm, String decision) throws SQLException {
		Connection connect = db.getConnection();

		switch (emp.getTitle()) {
		case "superviser": 
			String updateQuery = "update request set sup_status=? where id=?";
			PreparedStatement prepStmt = connect.prepareStatement(updateQuery);
			prepStmt.setString(1, decision);
			prepStmt.setInt(2, reqForm.getRequestId());
			prepStmt.executeUpdate(); 
			break;
		case "head": 
			updateQuery = "update request set head_status=? where id=?";
			prepStmt = connect.prepareStatement(updateQuery);
			prepStmt.setString(1, decision);
			prepStmt.setInt(2, reqForm.getRequestId());
			prepStmt.executeUpdate(); 
			break;
		case "benCo": 
			updateQuery = "update request set ben_status=? where id=?";
			prepStmt = connect.prepareStatement(updateQuery);
			prepStmt.setString(1, decision);
			prepStmt.setInt(2, reqForm.getRequestId());
			prepStmt.executeUpdate(); 
			break;
		}
	}

	@Override
	public List<Request> viewAllRequest() throws SQLException {
		Connection connect = db.getConnection();
		Statement stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery("select * from request");
		List<Request> reqList = new ArrayList<>();
		Request req = null;
		while (rs.next()) {
			req = new Request(rs.getInt(1), rs.getInt(2),rs.getTimestamp(3),rs.getString(4),rs.getString(5), rs.getInt(6),rs.getString(7),rs.getString(8),  
					rs.getString(9), rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getBoolean(16));
			reqList.add(req);
		}
		return reqList;
	}
	
	public Employee retrieveEmployeeByCredentials(String username, String password) throws SQLException {
		LoginDao loginDao = new LoginDaoImple();
		
		Employee emp=null;
		emp = loginDao.retrieveEmployeeByCredentials(username, password);
		return emp;


//		String selectQuery = "select * from users where username=?";
//		PreparedStatement prepStmt = connect.prepareStatement(selectQuery);
//		prepStmt.setString(1, username);
//		ResultSet rs = prepStmt.executeQuery();
//		Employee emp = null;
//		while (rs.next()) {
//			emp = new Employee(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6), 
//				rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10),rs.getString(11));
//		}
//		return emp;
	}
}
