package com.revature.daoimple;

import java.util.ArrayList;

import com.revature.meta.ConnFactory;
import com.revature.dao.EmployeeDao;
import com.revature.model.Employee;

public class EmployeeDaoImple implements EmployeeDao {
	
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public Employee retriveEmployeeById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Employee> retriveSubordinates() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
