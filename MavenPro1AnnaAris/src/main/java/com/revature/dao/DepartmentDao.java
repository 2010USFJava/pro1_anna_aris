/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Interface for reciving and adding department information to a database
 */

package com.revature.dao;

import java.sql.SQLException;

import com.revature.model.Department;

public interface DepartmentDao {

	Department retriveDeptById(Integer departId) throws SQLException;
	void addDepatment(Department department) throws SQLException;
	

}
