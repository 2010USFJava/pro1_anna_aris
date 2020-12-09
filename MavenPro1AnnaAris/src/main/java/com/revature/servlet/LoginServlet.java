package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.LoginDao;
import com.revature.daoimple.LoginDaoImple;
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

		loginDao=new LoginDaoImple();
		
		
	}
       

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("In get method");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("In Post method");
		
		try {
			Employee emp = loginHelper(request,response);
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				mapper.writeValueAsString(emp);

			} catch(JsonProcessingException e) {
//				 e.printStackTrace();
				LogThis.logIt(LevelEnum.ERROR, "JsonProcessingException e In LoginServletDoPost");
			}
			
			response.getWriter().write(mapper.writeValueAsString(emp));
			
			
//			mapper.writeValue(Paths.get("employee.json").toFile(), emp);
		} catch (SQLException e) {
//			doGet(request, response);
			response.getWriter().append("SQLException at: ").append(request.getContextPath());

			e.printStackTrace();
//			doGet(request, response);
		} catch (IOException e) {
//			doGet(request, response);
			response.getWriter().append("IOException at: ").append(request.getContextPath());

			e.printStackTrace();
//			doGet(request, response);
//			e.printStackTrace();
		}
		
		
		
		
	}
	
	private Employee loginHelper(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		response.getWriter().append(username+" tried to log in with the password "+password);
		
		Employee emp=loginDao.retrieveEmployeeByCredentials(username, password);
		
		String output="";
		if(emp!=null) {
			output=("Logged in user: "+emp);
			
		} else {
			output="Invalid Login";
		}
		response.getWriter().println(output);
		return emp;
		
	}

}
