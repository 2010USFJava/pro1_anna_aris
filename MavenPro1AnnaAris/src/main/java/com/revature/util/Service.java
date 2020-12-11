package com.revature.util;

import java.sql.SQLException;

import com.revature.daoImpl.RequestDaoImpl;
import com.revature.model.Request;

public class Service {
	public static RequestDaoImpl rdi = new RequestDaoImpl();
	
	public static String addRequest(Request reqForm) {
		try {
			rdi.insertNewRequest(reqForm);
			return "formSubmitted.html";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "incomplete.html";
	}
}
