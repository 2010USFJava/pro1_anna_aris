package com.revature.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.dao.RequestDao;
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
			prepStmt.setDouble(4, req.getCost());
			prepStmt.setString(5, req.getStreet());
			prepStmt.setString(6, req.getCity());
			prepStmt.setString(7, req.getState());
			prepStmt.setString(8, req.getZip());
			prepStmt.setString(9, req.getEventType());
			prepStmt.setString(10, req.getEventDescription());
			prepStmt.executeUpdate();
	}
}
