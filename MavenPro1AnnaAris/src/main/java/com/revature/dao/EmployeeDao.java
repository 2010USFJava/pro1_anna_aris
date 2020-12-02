package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.Employee;

public interface EmployeeDao {
	Employee retriveEmployeeById();
	void addNewEmployee(Employee employee);
	ArrayList<Employee> retriveSubordinates();
}
