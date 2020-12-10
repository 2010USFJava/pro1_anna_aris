/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Model of a department in an office.
 */

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
//@ToString(of = {"id", "name","departmentHead.getId()"})
public class Department {
	@Getter
	@Setter
	private int id;
	@Getter
	@Setter
	private String name;
//	@ToString.Exclude
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

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", departmentHead=" + departmentHead.toStringDepartmentHead()+ "]";
	}


}
