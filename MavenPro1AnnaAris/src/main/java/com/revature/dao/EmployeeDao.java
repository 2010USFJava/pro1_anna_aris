package com.revature.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.Employee;

public interface EmployeeDao {
	Employee retriveEmployeeById(int id)  throws SQLException;
	void addNewEmployee(Employee employee)  throws SQLException;
	ArrayList<Employee> retriveSubordinatesById(int id)  throws SQLException;
	void updateEmployeeDepartment(int empId, int deptId) throws SQLException;
}
