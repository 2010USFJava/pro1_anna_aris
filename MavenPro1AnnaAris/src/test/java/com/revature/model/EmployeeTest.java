package com.revature.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {
	
	private Employee sup = new Employee("Adam","Zoo");

	private Employee empA = new Employee("Tommy", "Cornelius");
	private Employee empB = new Employee("Gregory", "Torrance");
	private List<Employee> empListEmpty = new ArrayList<>();
	private List<Employee> empListLoaded = new ArrayList<>();
	
	@Before
	public void setUp() {
		empListLoaded.add(empA);
		empListLoaded.add(empB);
		
	}
	
	@Test
	public void firstNameGetter() {
		Employee emp = new Employee("Joe","Smith",sup);
		String result=emp.getFirstName();
		assertEquals(result,"Joe");
	}
	
	@Test
	public void fNameSet() {
		Employee emp = new Employee("Joe","Smith",sup);
		emp.setFirstName("Martin");
		assertEquals("Martin",emp.getFirstName());
	}
	
	
	@Test
	public void lastNameGetter() {
		Employee emp = new Employee("Joe","Smith",sup);
		String result=emp.getLastName();
		assertEquals(result,"Smith");
	}
	
	@Test
	public void lNameSet() {
		Employee emp = new Employee("Joe","Smith",sup);
		emp.setLastName("Martin");
		assertEquals("Martin",emp.getLastName());
	}
	
	@Test
	public void getId0AtStart() {
		//defaulting to id 0 at this time
		Employee emp = new Employee("Joe","Smith",sup);
		int id = emp.getId();
		boolean result=false;
		
		if(id==0) {
			result=true;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void setId() {
		//defaulting to no id at this time
		Employee emp = new Employee("Joe","Smith",sup);
		emp.setId(100);
		assertEquals(100,emp.getId());
	}
	
	@Test
	public void getSupervisor() {
		Employee emp = new Employee("Joe","Smith",sup);
		assertEquals("Adam",emp.getSupervisor().getFirstName());
		
	}
	
	@Test
	public void setSupervisor() {
		Employee emp = new Employee("Joe","Smith",sup);
		Employee newSup = new Employee("Zoe","Magic");
		emp.setSupervisor(newSup);
		assertEquals("Zoe",emp.getSupervisor().getFirstName());
	}
	
	
	@Test
	public void getEmptySubordinateList() {
		Employee emp = new Employee("Joe","Smith",sup);
		emp.setSubordinates(empListEmpty);
		List<Employee> subList = emp.getSubordiates();
		assertEquals(0,subList.size());
	}
	
	@Test
	public void getFilledSubordinateList() {
		Employee emp = new Employee("Joe","Smith",sup);
		emp.setSubordinates(empListLoaded);
		List<Employee> subList = emp.getSubordiates();
		assertEquals(2,subList.size());
	}
	
	@Test
	public void getDefaultEmptySubordinateList() {
		Employee emp = new Employee("Joe","Smith",sup);
		List<Employee> subList = emp.getSubordiates();
		assertEquals(0,subList.size());
	}
	
	@Test
	public void addToSubordinateList() {
		Employee emp = new Employee("Joe","Smith",sup);
		emp.addSubordinate(empA);
		List<Employee> subList = emp.getSubordiates();
		assertEquals(empA,subList.get(0));
		
		
	}

	
	
	
	
	
	

}
