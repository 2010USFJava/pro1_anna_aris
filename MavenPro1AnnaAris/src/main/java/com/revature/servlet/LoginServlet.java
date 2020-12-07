package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.LoginDao;
import com.revature.daoimple.LoginDaoImple;
import com.revature.model.Employee;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao=new LoginDaoImple();
	
       

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
			loginHelper(request,response);
		} catch (SQLException e) {
			
			response.getWriter().append("SQLException at: ").append(request.getContextPath());

			e.printStackTrace();
//			doGet(request, response);
		} catch (IOException e) {
			response.getWriter().append("IOException at: ").append(request.getContextPath());

			e.printStackTrace();
//			doGet(request, response);
//			e.printStackTrace();
		}
	}
	
	private void loginHelper(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		response.getWriter().append(username+" tried to log in");
		
		Employee emp=loginDao.retrieveEmployeeByCredentials(username, password);
		
		String output="";
		if(emp!=null) {
			output=("Logged in user: "+emp);
			
		} else {
			output="Invalid Login";
		}
		response.getWriter().println(output);
		
	}

}
