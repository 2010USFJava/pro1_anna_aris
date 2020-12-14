package com.revature.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.daoImpl.RequestDaoImpl;
import com.revature.model.*;

public class Service {
	public static RequestDaoImpl rdi = new RequestDaoImpl();
	
	public static int calculateEstimateAward(Request reqForm) {
		String eventType = reqForm.getEventType();
		switch (eventType.toLowerCase()) {
		case "university courses":
			return (int)(reqForm.getCost() * .8);
		case "seminars":
			return (int)(reqForm.getCost() * .6);
		case "certification preparation classes":
			return (int)(reqForm.getCost() * .75);
		case "certification":
			return reqForm.getCost();
		case "technical training":
			return (int)(reqForm.getCost() * .9);
		default: return (int)(reqForm.getCost() * .3);
		}
	}
	
	public static String addRequest(Request reqForm) {
		reqForm.setEstimatedAward(calculateEstimateAward(reqForm));
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
			rdi.updateRequestStatus(emp, reqForm, decision);
			return "myresources/html/successful.html";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "myresources/html/incomplete.html";
	}
	
	public static String addAward(int reqId, int award, String reason) {
		try {
			rdi.updateRequestAward(reqId, award, reason);
			return "myresources/html/successful.html";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "myresources/html/incomplete.html";
	}
	
	public static String cancelRequest(int reqId) {
		try {
			rdi.cancelRequest(reqId);
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
	
	public static Request getRequest(int reqId) {
		Request req = null;
		try {
			req = rdi.getRequestByRequestId(reqId);
			return req;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return req;
	}
	public static Employee loginGetEmployee(String username, String password) {
		Employee emp = null;
		try {
			//emp = loginDao.retrieveEmployeeByCredentials(username, password);
			emp = rdi.retrieveEmployeeByCredentials(username, password);
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emp;
	}
}
