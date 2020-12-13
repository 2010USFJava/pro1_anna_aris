
package com.revature.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	@JsonIgnore
	private Employee supervisor;
	private Department department;
	private double funds=1000.00;
	@JsonIgnore
	private List<Employee> subordinates;
	private String title="employee";

//	Titles
//	manager, supervisor, dept_head, ben_co
//	employee

	public Employee() {
		super();
	}


	public Employee(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Employee(String firstName, String lastName, Employee supervisor) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.supervisor = supervisor;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getId() {
		return id;
	}


	public void setSupervisor(Employee emp) {
		this.supervisor = emp;
		// defaulting to setting supervisor's department to subordinate's department
		if (department == null && supervisor != null) {
			this.department = supervisor.department;
		}

	}

	
	public Employee getSupervisor() {
		return supervisor;
	}


	public void setSubordinates(List<Employee> empList) {
		this.subordinates = empList;

	}


	public void addSubordinate(Employee emp) {
		if(subordinates==null) {
			subordinates=new ArrayList<Employee>();
		}
		subordinates.add(emp);
	}


	public List<Employee> getSubordiates() {

		return subordinates;
	}


	public void setDepartment(Department department) {
		this.department = department;

	}


	public Department getDepartment() {
		return this.department;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", department="
				+ department + ", funds=" + funds + ", title=" + title + "]";
	}


	//accounting for merged code 

	//Will need to go through and connect up methods

	//inheritited methods. Changing employeeId -> id
	public int getEmployeeId() {
		return id;
	}
	public void setEmployeeId(int employeeId) {
		this.id = employeeId;
	}

	//yearly awards was the same as funds
	//Since this version has the money stored as doubles, I'll do conversion when these methods are called
	public int getYearlyAwards() {
		return (int)funds;
	}
	public void setYearlyAwards(int yearlyAwards) {
		this.funds = yearlyAwards;
	}
	
	/*This implementation is using username and passwords at a database level.
	*Note: Retaining code in commented out version, in case integration is necessary in the future.
	*/
	
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	
	
	
//	


}
