/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Test Employees used to database generated/stored that leaves this programs control.
 * Note: Proper mocking of Objects should allow this to be moved to the test section in the future
 */

package com.revature.driver;

import java.sql.SQLException;

import com.revature.dao.DepartmentDao;
import com.revature.dao.EmployeeDao;
import com.revature.dao.LoginDao;
import com.revature.daoImpl.DepartmentDaoImple;
import com.revature.daoImpl.EmployeeDaoImple;
import com.revature.daoImpl.LoginDaoImple;
import com.revature.model.Department;
import com.revature.model.Employee;
//Titles
//manager, supervisor, dept_head, ben_co
//employee


public class EmployeeTestData {
	public enum TestEmployee{
		CORA, //title=employee
		UNDERFOOT, //title=manager
		RUFAS, //title =supervisor
		HARRY_POTTER, //title = dept_head
		RON_WEASLY //title ben_co
		
		
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
		
//		CORA, //title=employee
//		UNDERFOOT, //title=manager
//		RUFAS, //title =supervisor
//		HARRY_POTTER, //title = dept_head
//		RON_WEASLY //title ben_co
		empList[0].setTitle("manager");
		empList[2].setTitle("supervisor");
		empList[3].setTitle("dept_head");
		empList[3].setTitle("ben_co");
		
		
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
			


			//testing that username will always be translated to lower case
			loginDao.createNewLogin(empList[0], "tiNycat", "meow");
			//testing that password is, in fact, case sensitive
			loginDao.createNewLogin(empList[1], "greenonion", "strAwberry");
			loginDao.createNewLogin(empList[2], "grumpy_old_man", "meow");
			loginDao.createNewLogin(empList[3], "potter_harry4", "theOneAndOnly");
			loginDao.createNewLogin(empList[4], "admin", "pass");
			
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
		
//		CORA, //title=employee
//		UNDERFOOT, //title=manager
//		RUFAS, //title =supervisor
//		HARRY_POTTER, //title = dept_head
//		RON_WEASLY //title ben_co
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
				break;
			case HARRY_POTTER:
				username="potter_harry4";
				password="theOneAndOnly";
				break;
			case RON_WEASLY:
				username="admin";
				password="pass";
				break;
				
		}
		
		
		
		try {
			testEmp=loginDao.retrieveEmployeeByCredentials(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return testEmp;
	}
}
