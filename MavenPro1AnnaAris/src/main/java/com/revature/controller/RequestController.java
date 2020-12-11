package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.model.Request;
import com.revature.util.Service;

public class RequestController {

	public static String submitRequest(HttpServletRequest req) throws JsonProcessingException, IOException {
		//Employee emp = (Employee) req.getSession().getAttribute("currentEmp");
		System.out.println(req.getParameter("cost") + req.getParameter("date"));
		Request reqForm = new Request(1,req.getParameter("date"),req.getParameter("type"),req.getParameter("time"),Double.valueOf(req.getParameter("cost")),
				req.getParameter("street"),req.getParameter("city"),req.getParameter("state"),req.getParameter("zip"),req.getParameter("description"));
		return Service.addRequest(reqForm);
	}
}
