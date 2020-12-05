package com.revature.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DepartmentTest {
	
	Department testDept;
	Employee testEmp;
	
	
	@Before
	public void setUp() {

		testEmp = new Employee();
		testEmp.setFirstName("Joe");
		testEmp.setLastName("Smith");
		testEmp.setDepartment(testDept);
		testEmp.setId(404);
		testDept = new Department(66,"Green Tree",testEmp);	
	}
	
	
	//getter tests
	@Test
	public void getterCheckId() {
		int result = testDept.getId();
		assertEquals(result,66);
	}
	
	@Test
	public void getterCheckName() {
		String result = testDept.getName();
		assertEquals(result,"Green Tree");
	}
	
	@Test
	public void getterCheckHeadEmployee() {
		Employee result = testDept.getDepartmentHead();
		assertEquals(result,testEmp);
	}
	
	//just to make sure that the employee was being properly updated
	@Test
	public void getterCheckHeadEmployee_extraFirstName() {
		String result = testDept.getDepartmentHead().getFirstName();
		assertEquals(result,"Joe");
	}
	
	//setter tests
	
	@Test
	public void setterCheckId() {
		testDept.setId(800);
		int result = testDept.getId();
		assertEquals(result,800);
	}
	
	public void setterCheckName() {
		testDept.setName("Golden Ham");
		String result = testDept.getName();
		assertEquals(result,"Golden Ham");
	}
	
	public void setterCheckHeadEmployee() {
		Employee emp = new Employee();
		emp.setFirstName("Yoyo");
		testDept.setDepartmentHead(emp);
		
		Employee result = testDept.getDepartmentHead();
		assertEquals(result,emp);
	}
	
	public void setterCheckHeadEmployee_extraFirstName() {
		Employee emp = new Employee();
		emp.setFirstName("Yoyo");
		testDept.setDepartmentHead(emp);
		
		String result = testDept.getDepartmentHead().getFirstName();
		assertEquals(result,"Yoyo");
		
	}

}
