package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.Employee;
import com.revature.util.Service;

public class LoginController {
	
	public static String login(HttpServletRequest req) {
		if(!req.getMethod().equals("POST")) {
			return "myresources/html/home.html";
		}
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Employee emp = Service.loginGetEmployee(username, password);
		if(emp==null) {
			return "myresources/html/incomplete.html";
		}else {
			req.getSession().setAttribute("employee", emp);
			return "myresources/html/landingPage.html";
		}
		
	}
}
