/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Model of an Employee with one supervisor and an unspecified number of subordinates.
 * 
 */

package com.revature.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	@JsonIgnore
	private Employee supervisor;
	@JsonIgnore
	private List<Employee> subordinates;
	@JsonIgnore
	private Department department;
	private double funds=10000.00;

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
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", supervisor="
				+ toStringSuperier() + ", subordinates=" + toStringSubordinate() + ", department=" + department +", funds=" +funds + "]";
	}
	
	public String toStringSubordinate() {
		String output="";
		if(subordinates!=null) {
		for (Employee emp:subordinates) {
			output+="Employee [id=" + emp.getId() + ", firstName=" + emp.getFirstName() + ", lastName=" + emp.getLastName()  +
					", department=" + emp.getDepartment() +", funds=" +emp.getFunds() +"] , ";
		}
		}
		return output;
		
	}
	
	public String toStringSuperier() {
		String output="";
		
		//I don't think I need the department info for the supervisor
		if(supervisor!=null) {
		Employee emp =supervisor;
			output+="Employee [id=" + emp.getId() + ", firstName=" + emp.getFirstName() + ", lastName=" + emp.getLastName()  +"] , ";
		} else {
			output = "None";
		}
		return output;
		
	}

	public String toStringDepartmentHead() {

		String output="";
		Employee emp =this;
			output+="Employee [id=" + emp.getId() + ", firstName=" + emp.getFirstName() + ", lastName=" + emp.getLastName()+"] , ";
			
		return output;
	}
	

//	public String getMinInfo() {
//		return "(id: " + getId() + " Name: " + getFirstName() + " " + getLastName() + ")";
//	}

	// to string stuff
//	@ToString.Include
//	public String Supervisor() {
//		String output = "";
//
//		if (supervisor == null) {
//			output = "none";
//
//		} else {
//			output=supervisor.getMinInfo();
//
//		}
//		return output;
//	}



//	@ToString.Include
//	public String Subordinates() {
//		String output = "";
//
//		if (subordinates == null || subordinates.isEmpty()) {
//			output = "none";
//
//		} else {
//			for (Employee each : subordinates) {
////				output = each.getMinInfo();
//			}
//
//		}
//		return output;
//	}

}
