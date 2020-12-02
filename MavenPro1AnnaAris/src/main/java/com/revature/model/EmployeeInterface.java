package com.revature.model;

import java.util.List;

public interface EmployeeInterface {
	
	void setFirstName(String firstName);
	String getFirstName();
	
	void setLastName(String lastName);
	String getLastName();
	
	void setId(int id);
	int getId();
	
	void setSupervisor(Employee emp);
	Employee getSupervisor();
	
	void setSubordinates(List<Employee> empList); //used for populating list all at once
	void addSubordinate(Employee emp); //used for populating list 1 at a time
	List<Employee> getSubordiates();
	
	void setDepartment(DepartmentInt department);
	DepartmentInt getDepartment();
	

}
