package com.revature.driver;

import com.revature.meta.LogThis;
import com.revature.meta.LogThis.LevelEnum;
import com.revature.model.Department;
import com.revature.model.Employee;

public class Driver {

	public static void main(String[] args) {
//		LogThis.logIt(LevelEnum.DEBUG, "Base Logger Test");
		System.out.println("Hello World");
		
		Employee emp = new Employee("Test","Man");
		Department dept = new Department("Trees",emp);
		
		System.out.println(emp.toString());
		System.out.println(dept.toString());

	}

}
