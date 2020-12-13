package com.revature.meta;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.util.JsonHelper;
import com.revature.util.LogThis;
import com.revature.util.LogThis.LevelEnum;

public class JsonParserRevPro1Tests {
	private Employee [] testEmployees;
	private Department [] testDepratment;
	
	
	@Before
	public void setUp() throws Exception {
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
		

		Department deptList[]= {deptA,deptB,deptC};
		
		testEmployees=empList;
		testDepratment= deptList;
	}

	@Test
	public void employeeToJsonFirstName() {
		Employee emp = testEmployees[0];
		JsonNode node = JsonHelper.toJson(emp);
		LogThis.logIt(LevelEnum.DEBUG,"Employee to JSON test: "+node);
		LogThis.logIt(LevelEnum.DEBUG,"Employee to JSON First Name: "+node.get("firstName").asText());
		
		System.out.println(node);
		assertEquals(node.get("firstName").asText(),"Lord");
	}
	
	@Test
	public void employeeToJsonSupervisorFirstName() {
		Employee emp = testEmployees[0];
		JsonNode node = JsonHelper.toJson(emp);
//		System.out.println(node);
		LogThis.logIt(LevelEnum.DEBUG,"Employee to JSON Supervisor First Name: "+node.get("firstName").asText());
		assertEquals(node.get("supervisor").get("firstName").asText(),"Rufas");
	}
}
