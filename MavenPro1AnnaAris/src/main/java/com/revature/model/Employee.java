package com.revature.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;


@Data @ToString
public class Employee implements EmployeeInterface {
	private int id;
	private String firstName;
	private String lastName;
	@ToString.Exclude private Employee supervisor;
	@ToString.Exclude private List <Employee> subordinates;
	private Department department;
	
	public Employee() {super();}
	
	public Employee(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Employee(String firstName, String lastName,Employee supervisor) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.supervisor=supervisor;
	}
	
	
	//interface items
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public void setId(int id) {
		this.id=id;		
	}
	
	@Override
	public int getId() {
		return id;
	}
	@Override
	public void setSupervisor(Employee emp) {
		this.supervisor=emp;
		//defaulting to setting supervisor's department to subordinate's department
		if(department==null && supervisor!=null) {
			this.department=supervisor.department;
		}
		
	}
	@Override
	public Employee getSupervisor() {
		return supervisor;
	}
	@Override
	public void setSubordinates(List<Employee> empList) {
		this.subordinates=empList;
		
	}
	@Override
	public void addSubordinate(Employee emp) {
		subordinates.add(emp);
	}
	@Override
	public List<Employee> getSubordiates() {

		
		return subordinates;
	}
	
	@Override
	public void setDepartment(Department department) {
		this.department=department;
		
	}

	@Override
	public Department getDepartment() {
		return this.department;
	}
	
	

	public String getMinInfo() {
		return "(id: "+getId()+" Name: "+getFirstName()+" "+getLastName()+")";
	}
	
	//to string stuff
	@ToString.Include
	public String Supervisor() {
		if (supervisor!=null) {

			return supervisor.getMinInfo();
		}else
			return " None";
					
	}
		
		
	
	@ToString.Include
	public String Subordinates() {
		String output="";
		
	
		if (subordinates==null||subordinates.isEmpty()) {
			output="none";
		
		} else {
			for (Employee each: subordinates) {
				output= each.getMinInfo();}
			
		}
		return output;
	}
	
	
	
	
}
