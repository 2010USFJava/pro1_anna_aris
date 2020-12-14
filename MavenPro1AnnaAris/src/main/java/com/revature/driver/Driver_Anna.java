/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Runs the program in purely back-end terms.
 * Note: Currently no UI in place for command-line work
 */

package com.revature.driver;

import java.sql.SQLException;

import com.revature.dao.CourseDao;
import com.revature.daoImpl.CourseDaoImple;
import com.revature.driver.EmployeeTestData.TestEmployee;
import com.revature.model.Course;
import com.revature.model.Employee;
import com.revature.model.GradeOrPresentation.LetterGrade;

public class Driver_Anna {
	
	
	public static void main(String[] args) {
		
//		String jsonSource= "{ \"title\": \"Coder From Scratch\"}";
//		try {
//			JsonNode node = JsonParserAnna.parse(jsonSource);
//			node.get("title");
//			System.out.println(node);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		

		EmployeeTestData testData= new EmployeeTestData();
		testData.loadTestData();
//		
		Employee testEmp=EmployeeTestData.testEmployeeLogin(TestEmployee.RUFAS);
		System.out.println(testEmp.getDepartment());
		Course course = new Course();
		course.setEmployee(testEmp);
		course.getGradOrPres().setLetterGrade(LetterGrade.A);
		CourseDao courseDao = new CourseDaoImple();
		try {
			courseDao.addCourse(course);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("*~*~*~*~*~*~*");
//		System.out.println(RuntimeData.getEmployeeFromMap(9));
//		System.out.println(RuntimeData.getDepartmentFromMap(207));
//		Employee testEmp=testData.testEmployeeLogin(TestEmployee.HARRY_POTTER);
//		System.out.println(testEmp);

		
	}

}
