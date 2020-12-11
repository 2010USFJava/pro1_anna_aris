package com.revature.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LoginTest {
	private Employee emp=new Employee();
	private Login login= new Login();
	
	@Before
	public void setUp() throws Exception {
		emp.setFirstName("Carl");
		emp.setLastName("Martin");
		emp.setId(30);
		login.setEmployee(emp);
		login.setUsername("green_langern");
		login.setPassword("cheeseSandwich");
	}

	@Test
	public void getLoginEmployeeTestA_empObject() {
		Employee result = login.getEmployee();
		assertEquals(result,emp);
	}

	@Test
	public void getLoginEmployeeTestB_name() {
		String result = login.getEmployee().getFirstName();
		assertEquals(result,"Carl");
	}

	@Test
	public void getUsername_normal() {
		String result = login.getUsername();
		assertEquals(result,"green_langern");
	}

	@Test
	public void getUsername_caseInsensitiveA() {
		boolean result = login.usernamePassCheck("GREEn_laNgeRN");
		assertTrue(result);
	}

	@Test
	public void getPassword_normal() {
		String result = login.getPassword();
		assertEquals(result,"cheeseSandwich");
	}

	@Test
	public void getPassword_caseSensitive() {
		boolean result =login.passwordPassCheck("chEEseSandwich");
		assertFalse(result);
	}

}
