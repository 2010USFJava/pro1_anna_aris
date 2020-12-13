package com.revature.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.daoImpl.RequestDaoImpl;
import com.revature.model.*;

public class Service {
	public static RequestDaoImpl rdi = new RequestDaoImpl();
	
	public static String addRequest(Request reqForm) {
		try {
			rdi.insertNewRequest(reqForm);
			return "myresources/html/successful.html";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "myresources/html/incomplete.html";
	}
	
	public static String insertReviewStatus(Employee emp, Request reqForm, String decision) {
		try {
			rdi.updateRequest(emp, reqForm, decision);
			return "myresources/html/successful.html";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "myresources/html/incomplete.html";
	}
	
	public static List<Request> getAllRequests() {
		List<Request> reqList = new ArrayList<>();
		try {
			reqList = rdi.viewAllRequest();
			return reqList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reqList;
	}
	public static Employee loginGetEmployee(String username, String password) {
		Employee emp = null;
		try {
			emp = rdi.retrieveEmployeeByCredentials(username, password);
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emp;
	}
}
