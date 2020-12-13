/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Dao interface of The Courses that can be applied to get reimbursement from
*LastUpdate: 12/12/20
 */


package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Course;
import com.revature.model.Employee;

public interface CourseDao {
	Course retrieveCourseByCourseId (int id) throws SQLException;
	List<Course> retrieveCourseByEmployeeId (int id) throws SQLException;
	void addCourse(Course course) throws SQLException;
	void addToGradOrPresTable(Course course) throws SQLException;

}
