package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Servlet implementation class LandingPageServlet
 */
public class LandingPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("In get at: ").append(request.getContextPath());
//		HttpSession session = request.getSession();
//		
//		JsonNode node = (JsonNode)session.getAttribute("employeeJson");
//		String output =node.get("firstName").asText();
//		response.getWriter().print(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.getWriter().append("In post at landing servlet: ").append(request.getContextPath());
		HttpSession session = request.getSession();
//		String output = session.getAttribute("employeeJson").toString();
//		response.getWriter().print(output);

//
//		JsonNode node = (JsonNode)session.getAttribute("employeeJson");
//		//testing to make sure json code is coming through
//		JsonNode fName =node.get("firstName");
//		JsonNode lName=node.get("lastName");
//		response.getWriter().print("\nFirst Name "+fName);
//		response.getWriter().print(" Last Name "+lName);
//		response.getWriter().print(node.toString());
		
		//redirecting to landing page
		RequestDispatcher rd = request.getRequestDispatcher("landingPage.html");
		rd.forward(request, response);
		
		
	}

}
