/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Handles User attempt to login
 */

package com.revature.servlet;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.revature.dao.LoginDao;
import com.revature.daoimple.LoginDaoImple;
import com.revature.meta.JsonHelper;
import com.revature.meta.LogThis;
import com.revature.meta.LogThis.LevelEnum;
import com.revature.model.Employee;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LoginDao loginDao;
	static {

		loginDao = new LoginDaoImple();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("In get method");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("In Post method");
		
		JsonNode node=null;
		
		try {
			node =JsonHelper.toJson(loginHelper(request, response));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		

		try {
			String output=node.toString();
			response.getWriter().write(node.toString());
			HttpSession session = request.getSession();
			//expriment later to see if node can be moved without being in stream format
			session.setAttribute("employeeJson", node);
			session.setAttribute("employeeJsonString", JsonHelper.stringify(node));
//			response.getWriter().println(output);
		} catch (NullPointerException e) {
			response.getWriter().append("Null Pointer Exception.");
			e.printStackTrace();
		}


		saveJsonFile(node);
		
		RequestDispatcher rd = request.getRequestDispatcher("welcome");
		rd.forward(request, response);
		
//		RequestDispatcher rd = request.getRequestDispatcher("landingPage.html");
//		rd.forward(request, response);
		
//		try {
//			Employee emp = loginHelper(request, response);
//			ObjectMapper mapper = new ObjectMapper();
//
//			try {
//				mapper.writeValueAsString(emp);
//
//			} catch (JsonProcessingException e) {
////				 e.printStackTrace();
//				LogThis.logIt(LevelEnum.ERROR, "JsonProcessingException e In LoginServletDoPost");
//			}
//
//			response.getWriter().write(mapper.writeValueAsString(emp));
//
//			mapper.writeValue(Paths.get("employee.json").toFile(), emp);
//		} catch (SQLException e) {
//			response.getWriter().append("SQLException at: ").append(request.getContextPath());
//			e.printStackTrace();
//		} catch (IOException e) {
//			response.getWriter().append("IOException at: ").append(request.getContextPath());
//
//			e.printStackTrace();
//		}


//		

	}

	private Employee loginHelper(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		LogThis.logIt(LevelEnum.INFO, "Starting Login Process");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.getWriter().append(username + " tried to log in with the password " + password+"\n");

		Employee emp = loginDao.retrieveEmployeeByCredentials(username, password);

//		String output = "";
//		if (emp != null) {
//			output = ("Logged in user: " + emp);
//
//		} else {
//			output = "Invalid Login";
//		}
//		response.getWriter().println(output);
		return emp;

	}
	
	private void saveJsonFile(JsonNode jsonNode) {
//		JsonHelper.parse(jsonNode);
	}

}
