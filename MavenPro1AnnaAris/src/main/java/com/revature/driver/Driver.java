/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Runs the program in purely back-end terms.
 * Note: Currently no UI in place for command-line work
 */

package com.revature.driver;

import java.sql.SQLException;

import com.revature.dao.DepartmentDao;
import com.revature.dao.EmployeeDao;
import com.revature.dao.LoginDao;
import com.revature.daoimple.DepartmentDaoImple;
import com.revature.daoimple.EmployeeDaoImple;
import com.revature.daoimple.LoginDaoImple;
import com.revature.driver.EmployeeTestData.TestEmployee;
import com.revature.meta.RuntimeData;
import com.revature.model.Department;
import com.revature.model.Employee;

public class Driver {
	
	
	public static void main(String[] args) {
		EmployeeTestData testData= new EmployeeTestData();
//		loadTestData();
//		
//		Employee testEmp=testEmployeeLogin(TestEmployee.RUFAS);
//		System.out.println(testEmp);

		
		System.out.println("*~*~*~*~*~*~*");
//		System.out.println(RuntimeData.getEmployeeFromMap(9));
//		System.out.println(RuntimeData.getDepartmentFromMap(207));
		Employee testEmp=testData.testEmployeeLogin(TestEmployee.HARRY_POTTER);
		System.out.println(testEmp);

		
	}

}
