package com.revature.driver;

import java.sql.SQLException;

import com.revature.dao.DepartmentDao;
import com.revature.dao.EmployeeDao;
import com.revature.dao.LoginDao;
import com.revature.daoimple.DepartmentDaoImple;
import com.revature.daoimple.EmployeeDaoImple;
import com.revature.daoimple.LoginDaoImple;
import com.revature.meta.RuntimeData;
import com.revature.model.Department;
import com.revature.model.Employee;

public class Driver {
	private enum TestEmployee{
		CORA,
		UNDERFOOT,
		RUFAS
		
	}
	
	
	private static EmployeeDao empDao = new EmployeeDaoImple();
	private static DepartmentDao deptDao = new DepartmentDaoImple();
	private static LoginDao loginDao = new LoginDaoImple();
	
	public static void loadTestData() {
		Employee empList[] = {
				new Employee("Lord","Underfoot"),
				new Employee("Cora","Dog"),
				new Employee("Rufas","OldCat"),
				new Employee("Harry","Potter"),
				new Employee("Ron","Weasley"),
				new Employee("Hermonie","Granger"),
				new Employee("Percy","Weasley")
		};
		empList[1].setSupervisor(empList[0]); //Underfoot is Cora's Boss
		empList[0].setSupervisor(empList[2]);//Rufas is Underfoot's boss
		
		empList[4].setSupervisor(empList[5]);//Hermine is Ron's Boss
		empList[5].setSupervisor(empList[6]);//Percy is Herminies Boss
		

		Department deptA = new Department("Pet Food",empList[2]); //Rufas in in chare of the Pet Food Department
		empList[0].setDepartment(deptA);
		empList[1].setDepartment(deptA);
		
		Department deptB = new Department("Mysteries",empList[3]);//Harry has no registered superier and is alone in the department
		Department deptC = new Department("Prefects",empList[6]);//Percy is in charge of the Prefects Department
		empList[4].setDepartment(deptC);
		empList[5].setDepartment(deptC);
		
		Department[] deptList = {deptA,deptB,deptC};
		
		try {
			for (Department each:deptList) {
				deptDao.addDepatment(each);
			}
			
			for(Employee each:empList) {

				empDao.addNewEmployee(each);
			}
			


			loginDao.createNewLogin(empList[0], "tiNycat", "meow");
			loginDao.createNewLogin(empList[1], "greenonion", "strAwberry");
			loginDao.createNewLogin(empList[2], "grumpy_old_man", "meow");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Employee testEmployeeLogin(TestEmployee testEmployee) {
		Employee testEmp=null;
		String username="";
		String password="";

		switch(testEmployee) {
			case CORA:
				username="greenonion";
				password="strAwberry";
				break;
			case UNDERFOOT:
				username="tiNycat";
				password="meow";
				break;
			case RUFAS:
				username="grumpy_old_man";
				password="meow";
		}
		
		
		
		try {
			testEmp=loginDao.retrieveEmployeeByCredentials("tiNycat", "meow");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return testEmp;
	}
	
	public static void main(String[] args) {
		loadTestData();
		
//		Employee testEmp=testEmployeeLogin(TestEmployee.UNDERFOOT);
//		System.out.println(testEmp);

		
		System.out.println("*~*~*~*~*~*~*");
		System.out.println(RuntimeData.getEmployeeFromMap(9));
		System.out.println(RuntimeData.getDepartmentFromMap(207));
		
	}

}
