package com.revature.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Department implements DepartmentInt {
	@Getter
	@Setter
	private int id;
	@Getter
	@Setter
	private String name;
	@ToString.Exclude
	@JsonIgnore
	private Employee departmentHead;

	// constructor
	public Department(String name) {
		this.name = name;
	}

	public Department(String name, Employee departmentHead) {
		this.name = name;
		setDepartmentHead(departmentHead);
	}

	public Department(int id, String name, Employee departmentHead) {
		this.id = id;
		this.name = name;
		setDepartmentHead(departmentHead);
	}

	// getters and setters
	// department head will be in the department
	public void setDepartmentHead(Employee departmentHead) {
		this.departmentHead = departmentHead;
		departmentHead.setDepartment(this);

	}

	public Employee getDepartmentHead() {
		return this.departmentHead;
	}

	@ToString.Include
	public String Deparment_Head() {
		if(departmentHead==null) {
			return " None";
			
		}else {
		
		return "(id=" + this.departmentHead.getId() + ": " + departmentHead.getFirstName() + " "
				+ departmentHead.getLastName() + ")";
	
		}
	}
}
