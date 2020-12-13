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

//import com.example.model.SuperVillain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.LoginDao;
import com.revature.daoimple.LoginDaoImple;
import com.revature.meta.JsonHelper;
import com.revature.meta.LogThis;
import com.revature.meta.LogThis.LevelEnum;
import com.revature.model.Employee;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LoginDao loginDao;
	static {
		loginDao = new LoginDaoImple();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("In get method");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("In Post method");
		
//		JsonNode node=null;
		Employee emp=null;
		try {
			emp = loginHelper(request, response);
//			node =JsonHelper.toJson(loginHelper(request, response));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			
//			
//			String output=node.toString();
//			response.getWriter().write(node.toString());
//			
//			
			HttpSession session = request.getSession();
			session.setAttribute("employee", emp);
//			
//			
//			session.setAttribute("employeeJsonString", JsonHelper.stringify(node));
//			saveJsonFile(node, "loggedInEmployee");
		} catch (NullPointerException e) {
			response.getWriter().append("Null Pointer Exception.");
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("welcome");
		rd.forward(request, response);
	}

	private Employee loginHelper(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		LogThis.logIt(LevelEnum.INFO, "Starting Login Process");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.getWriter().append(username + " tried to log in with the password " + password+"\n");
		Employee emp = loginDao.retrieveEmployeeByCredentials(username, password);
		return emp;

	}
	
//	public static void getSessionVillain(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
//		SuperVillain vill =  (SuperVillain) req.getSession().getAttribute("currentvill");
//		res.getWriter().write(new ObjectMapper().writeValueAsString(vill));
//	}
//	
//	public static void g(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
//		SuperVillain vill =  (SuperVillain) req.getSession().getAttribute("currentvill");
//		res.getWriter().write(new ObjectMapper().writeValueAsString(vill));
//	}
//	private void saveJsonFile(JsonNode jsonNode) {      
//		try {
//        FileWriter file = new FileWriter("./loggedInEmployee.json");
//        file.write(JsonHelper.stringify(jsonNode));
//        file.close();
//     } catch (IOException e) {
//        e.printStackTrace();
//     }
//     System.out.println("JSON file created: "+jsonNode);
//     
//     
//  }

}
