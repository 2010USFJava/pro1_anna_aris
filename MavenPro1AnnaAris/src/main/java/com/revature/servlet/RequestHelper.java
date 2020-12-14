package com.revature.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.controller.GradeController;
import com.revature.controller.LoginController;
import com.revature.controller.RequestController;
import com.revature.controller.SimpleController;

public class RequestHelper {
	
	public static String process(HttpServletRequest req) throws JsonProcessingException, IOException {
		
		System.out.println(req.getRequestURI());
		
		switch(req.getRequestURI()) {
			
		case "/pro1AnnaAris/request_start.change":
			return RequestController.goToReimbursementForm(req);
			
		case "/pro1AnnaAris/view_request.change":
			return RequestController.goToViewAllReimbersmentForms(req);
			
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
		
		case "/pro1AnnaAris/grade.change":
			return GradeController.changeRequest(req);
			
		case "/pro1AnnaAris/grade_add.change":

			System.out.println("in Add Grade");
			return GradeController.addGrade(req);
		case "/pro1AnnaAris/grade_approve.change":
			return GradeController.goToGradeApprovel(req);
			
		case "/pro1AnnaAris/landing_page.change":
			return SimpleController.goHome(req);
			

			
		default: return "incomplete.html";
		}
	}

}
