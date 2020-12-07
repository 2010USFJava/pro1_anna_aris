package com.revature.driver;

import java.sql.SQLException;

import com.revature.dao.DepartmentDao;
import com.revature.dao.EmployeeDao;
import com.revature.dao.LoginDao;
import com.revature.daoimple.DepartmentDaoImple;
import com.revature.daoimple.EmployeeDaoImple;
import com.revature.daoimple.LoginDaoImple;
import com.revature.meta.RuntimeData;
import com.revature.model.Employee;

public class Driver {
	
	
	public static void main(String[] args) {
		EmployeeDao empDao = new EmployeeDaoImple();
		DepartmentDao deptDao = new DepartmentDaoImple();
		LoginDao loginDao = new LoginDaoImple();
//		
//		//		LogThis.logIt(LevelEnum.DEBUG, "Base Logger Test");
//		System.out.println("Hello World");
//		

//		Department dept = new Department("Blueberry",emp);
//
//		try {
//			deptDao.addDepatment(dept);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
////		System.out.println(emp.toString());
//		System.out.println(dept.toString());
		Employee testEmp;
		try {
			testEmp=loginDao.retrieveEmployeeByCredentials("greenonion", "strAwberry");
//			loginDao.createNewLogin(testEmp, "greenonion", "strAwberry");
//			Employee emp = new Employee("Lord","Underfoot");
//			emp.setSupervisor(testEmp);
//			empDao.addNewEmployee(emp);
//			loginDao.createNewLogin(emp, "tiNycat", "meow");
//			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		
		System.out.println("*~*~*~*~*~*~*");
		System.out.println(RuntimeData.getEmployeeFromMap(9));
		System.out.println(RuntimeData.getDepartmentFromMap(207));
		
	}

}
