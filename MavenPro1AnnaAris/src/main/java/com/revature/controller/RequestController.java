package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.model.*;
import com.revature.util.Service;

public class RequestController {

	public static String submitRequest(HttpServletRequest req) throws JsonProcessingException, IOException {
		Employee emp = (Employee) req.getSession().getAttribute("currentEmp");
		Request reqForm = new Request(emp.getEmployeeId(),req.getParameter("date"),req.getParameter("time"),Integer.valueOf(req.getParameter("cost")),req.getParameter("street"),
				req.getParameter("city"),req.getParameter("state"),req.getParameter("zip"),req.getParameter("type"),req.getParameter("description"));
		return Service.addRequest(reqForm);
	}
	public static String reviewRequest(HttpServletRequest req) throws JsonProcessingException, IOException {
		Employee emp = (Employee) req.getSession().getAttribute("currentEmp");
		Request reqForm = (Request) req.getSession().getAttribute("currentReqForm");
		String decision = req.getParameter("answer");
		return Service.insertReviewStatus(emp, reqForm, decision);
	}
	public static String changeRequest(HttpServletRequest req) throws JsonProcessingException, IOException {
		int yearlyAwards = Integer.valueOf(req.getParameter("estimate"));
		return null;
	}
}
