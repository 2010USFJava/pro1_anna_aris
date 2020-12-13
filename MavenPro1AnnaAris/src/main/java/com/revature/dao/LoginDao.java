/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 *  Description: Interface for receiving and adding Login information to a database

 */

package com.revature.dao;

import java.sql.SQLException;

import com.revature.model.Employee;
import com.revature.model.Login;

public interface LoginDao {
	
	public Employee retrieveEmployeeByCredentials(String username, String password) throws SQLException;
	public void createNewLogin(Employee emp, String username, String password) throws SQLException;
	public void updatePassword(Employee emp, String password) throws SQLException;
	public void checkIfUsernameExists(String username) throws SQLException;
	
	//Add if extra time:
	//it may make sense to check if employee is in the system to avoid dulpicate entries

	
}
