package com.revature.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.*;
import com.revature.util.Service;

public class GetInfoController {
	
	public static void getAllRequests(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		List<Request> reqList = Service.getAllRequests();
		res.getWriter().write(new ObjectMapper().writeValueAsString(reqList));
	}
	
	public static void getCurrentEmployee(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		Employee emp = (Employee) req.getSession().getAttribute("currentemp");
		res.getWriter().write(new ObjectMapper().writeValueAsString(emp));
	}
}
