/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description:  Postgress Implementation  for receiving and adding EMPLOYEE information to a database

 */

package com.revature.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.dao.EmployeeDao;
import com.revature.dao.LoginDao;
import com.revature.model.Employee;
import com.revature.util.DBConnection;
import com.revature.util.LogThis;
import com.revature.util.LogThis.LevelEnum;
import com.revature.util.RuntimeData;


//Right now I'm not utilizing the Login class for any of this,
//However, that will change if I implment RuntimeData for LoginInfo
public class LoginDaoImple implements LoginDao {

	public static DBConnection cf = DBConnection.getInstance();
	
	@Override
	public Employee retrieveEmployeeByCredentials(String username, String password) throws SQLException {
		EmployeeDao empDao=new EmployeeDaoImple();
//		Make sure username info is all lowercase
		username=username.toLowerCase();
		Connection conn = cf.getConnection();
		String sql="select employee_id from logins where username=? and password=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		int retrivedId=0;
		while(rs.next()) {
			retrivedId=rs.getInt(1);
			break;
		}
		
		//use id to get Employee
		Employee emp = null;
		//if id = 0 then there were no results in the table
		if(retrivedId==0) {
			LogThis.logIt(LevelEnum.INFO,"Failed Login Attempt. Username and Password do not match anything in database.");
		}
		//check if the employee is in the runtime database already. If so stop here.
		else if(RuntimeData.checkIfInEmployeeMap(retrivedId)) {
			emp=RuntimeData.getEmployeeFromMap(retrivedId);
		} else 
		//As far as load time goes, it would be faster to run the employee generator from an sql statment,
			//but I want to inherit some other trackers I had in this method.
		{
			emp=empDao.retriveEmployeeById(retrivedId);
		}
		
		if(emp!=null) {
			LogThis.logIt(LevelEnum.INFO, "Employee Logged In Emp#:"+emp.getId());
		}
		
		return emp;
		
	}

	@Override
	public void createNewLogin(Employee emp, String username, String password) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "insert into logins values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, emp.getId());
		ps.setString(2, username.toLowerCase());
		ps.setString(3, password);
		ps.executeUpdate();

		LogThis.logIt(LevelEnum.INFO, "Created user info(username:"+username+") for "+emp);
		
	}

	@Override
	public void updatePassword(Employee emp, String password) throws SQLException {
		Connection conn = cf.getConnection();
		String sql="update logins set password=? where employee_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,password);
		ps.setInt(2, emp.getId());
		ps.executeUpdate();
		LogThis.logIt(LevelEnum.INFO, "Updated Password for "+emp);
		
	}

	@Override
	public void checkIfUsernameExists(String username) throws SQLException {
		Connection conn = cf.getConnection();
		LogThis.logIt(LevelEnum.INFO, "Checking if username exists: "+username);
		String sql = "select * from logins where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,username);
		ps.executeUpdate();
	}

}
