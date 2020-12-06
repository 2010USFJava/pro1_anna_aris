package com.revature.driver;

import java.sql.SQLException;

import com.revature.dao.DepartmentDao;
import com.revature.dao.EmployeeDao;
import com.revature.daoimple.DepartmentDaoImple;
import com.revature.daoimple.EmployeeDaoImple;
import com.revature.model.Department;
import com.revature.model.Employee;

public class Driver {
	
	
	public static void main(String[] args) {
		EmployeeDao empDao = new EmployeeDaoImple();
		DepartmentDao deptDao = new DepartmentDaoImple();
		
		//		LogThis.logIt(LevelEnum.DEBUG, "Base Logger Test");
		System.out.println("Hello World");
		
		Employee emp = new Employee("Cora","Dog");
		Department dept = new Department("Blueberry",emp);

		try {
			deptDao.addDepatment(dept);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(emp.toString());
		System.out.println(dept.toString());

	}

}
