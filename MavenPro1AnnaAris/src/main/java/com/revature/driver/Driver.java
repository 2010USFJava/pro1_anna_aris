/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Runs the program in purely back-end terms.
 * Note: Currently no UI in place for command-line work
 */

package com.revature.driver;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.revature.meta.JsonHelper;

public class Driver {
	
	
	public static void main(String[] args) {
		EmployeeTestData testData= new EmployeeTestData();
		
		String jsonSource= "{ \"title\": \"Coder From Scratch\"}";
		try {
			JsonNode node = JsonHelper.parse(jsonSource);
			node.get("title");
			System.out.println(node);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		loadTestData();
//		
//		Employee testEmp=testEmployeeLogin(TestEmployee.RUFAS);
//		System.out.println(testEmp);

		
//		System.out.println("*~*~*~*~*~*~*");
//		System.out.println(RuntimeData.getEmployeeFromMap(9));
//		System.out.println(RuntimeData.getDepartmentFromMap(207));
//		Employee testEmp=testData.testEmployeeLogin(TestEmployee.HARRY_POTTER);
//		System.out.println(testEmp);

		
	}

}
