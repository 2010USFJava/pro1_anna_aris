package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.model.*;
import com.revature.util.Service;

public class RequestController {

	public static String submitRequest(HttpServletRequest req) throws JsonProcessingException, IOException {
		Employee emp = (Employee) req.getSession().getAttribute("currentEmp");
		Request reqForm = new Request(emp.getEmployeeId(),req.getParameter("date"),req.getParameter("time"),Integer.valueOf(req.getParameter("cost")),0,
				req.getParameter("street"),req.getParameter("city"),req.getParameter("state"),req.getParameter("zip"),req.getParameter("type"),req.getParameter("description"));
		return Service.addRequest(reqForm);
	}
	public static String reviewRequest(HttpServletRequest req) throws JsonProcessingException, IOException {
		Employee emp = (Employee) req.getSession().getAttribute("currentEmp");
		Request reqForm = Service.getRequest(Integer.valueOf(req.getParameter("cost")));
		String decision = req.getParameter("?");
		if (decision=="Cancel Request") {
			return Service.cancelRequest(reqForm.getRequestId());
		}
		return Service.insertReviewStatus(emp, reqForm, decision);
	}
	public static String changeRequest(HttpServletRequest req) throws JsonProcessingException, IOException {
		int reqId = Integer.valueOf(req.getParameter("id"));
		int award = Integer.valueOf(req.getParameter("award"));
		String reason = req.getParameter("comment");
		return Service.addAward(reqId, award, reason);
	}
}
