package com.revature.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmployeeTest {

	@Test
	public void firstNameGetter() {
		Employee emp = new Employee("Joe","Smith");
		String result=emp.getFirstName();
		assertEquals(result,"Joe");
	}
	
	@Test
	public void lastNameGetter() {
		Employee emp = new Employee("Joe","Smith");
		String result=emp.getLastName();
		assertEquals(result,"Smith");
	}

}
