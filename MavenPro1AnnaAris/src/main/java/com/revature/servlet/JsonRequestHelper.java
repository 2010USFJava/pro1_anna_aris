package com.revature.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.controller.GetInfoController;

public class JsonRequestHelper {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		System.out.println(req.getRequestURI());
		
		switch(req.getRequestURI()) {
		case "/pro1AnnaAris/requests.json":
			GetInfoController.getAllRequests(req, res);
			break;
		case "/pro1AnnaAris/currentEmployee.json":
//		case "/pro1AnnaAris/currentEmployee.json":
			System.out.println("in rs.json");
			GetInfoController.getCurrentEmployee(req, res);
			break;
//		default:
//			SuperVillain vill = new SuperVillain("?","?", 0);
//			res.getWriter().write(new ObjectMapper().writeValueAsString(vill));
		}
		
	}

}
