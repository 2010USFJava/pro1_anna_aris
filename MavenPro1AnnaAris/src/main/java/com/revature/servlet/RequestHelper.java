package com.revature.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.controller.LoginController;
import com.revature.controller.RequestController;

public class RequestHelper {
	
	public static String process(HttpServletRequest req) throws JsonProcessingException, IOException {
		
		System.out.println(req.getRequestURI());
		
		switch(req.getRequestURI()) {
			
		case "/pro1AnnaAris/request.change":
			return RequestController.submitRequest(req);
			
		case "/pro1AnnaAris/reviewRequest.change":
			System.out.println("in reviewRequest.change rhelper");
			return RequestController.reviewRequest(req);
			
		case "/pro1AnnaAris/changeRequest.change":
			return RequestController.changeRequest(req);
			
		case "/pro1AnnaAris/login.change":
			System.out.println("in login.change rhelper");
			return LoginController.login(req);
			
		default: return "incomplete.html";
		}
	}

}
