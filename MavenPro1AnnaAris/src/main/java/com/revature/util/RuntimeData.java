/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Holds search results made during runtime. Make to save trips to database
 * that would cost time. Transiant. 
 * Note: Partly an experiment in Cashing and lazy loading. 
 * Updates and modifications are recommended with future skill set.
 */

package com.revature.util;

import java.util.HashMap;
import java.util.Map;

import com.revature.model.Course;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.util.LogThis.LevelEnum;

public class RuntimeData {

	private static Map<Integer, Employee> EmployeeMap = new HashMap<>();
	private static Map<Integer, Department> DepartmentMap = new HashMap<>();
	private static Map<Integer, Course> CourseMap = new HashMap<>();

	// check if items in runtime data cashe
	public static boolean checkIfInEmployeeMap(int id) {
		if (EmployeeMap.containsKey(id)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean checkIfInDepartmentMap(int id) {
		if (DepartmentMap.containsKey(id)) {
			return true;
		}
		return false;

	}
	
	public static boolean checkIfInCourseMap(int id) {
		if (CourseMap.containsKey(id)) {
			return true;
		}
		return false;

	}

	// pull items from map
	public static Employee getEmployeeFromMap(int id) {
		Employee emp = null;
		if (checkIfInEmployeeMap(id)) {
			emp = EmployeeMap.get(id);
		}
		return emp;
	}

	public static Department getDepartmentFromMap(int id) {
		Department dept = null;
		if (checkIfInDepartmentMap(id)) {
			dept = DepartmentMap.get(id);
		}
		return dept;
	}

	public static Course getCourseFromMap(int id) {
		Course c = null;
		if (checkIfInCourseMap(id)) {
			c = CourseMap.get(id);
		}
		return c;
	}

	// put items in Map
	public static void addEmployeeToMap(Employee emp) {
		try {
			EmployeeMap.put(emp.getId(), emp);
		} catch (Exception e) {
			LogThis.logIt(LevelEnum.ERROR, "Failure Adding Employee To Map: " + emp.getId());
		}

	}

	public static void addDepartmentToMap(Department dept) {
		try {
			DepartmentMap.put(dept.getId(), dept);
		} catch (Exception e) {
			LogThis.logIt(LevelEnum.ERROR, "Failure Adding Department To Map: " + dept.getId());
		}
	}
	
	public static void addCourseToMap(Course course) {
		try {
			CourseMap.put(course.getId(), course);
		} catch (Exception e) {
			LogThis.logIt(LevelEnum.ERROR, "Failure Adding Department To Map: " + course.getId());
		}
	}
}
