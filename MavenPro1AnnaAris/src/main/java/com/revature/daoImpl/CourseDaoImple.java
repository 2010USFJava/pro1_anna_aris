/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Dao Implementation of The Courses that can be applied to get reimbursement from
*LastUpdate: 12/12/20
 */

package com.revature.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.CourseDao;
import com.revature.dao.EmployeeDao;
import com.revature.model.Course;
import com.revature.model.Course.CourseType;
import com.revature.model.Employee;
import com.revature.util.DBConnection;
import com.revature.util.LogThis;
import com.revature.util.LogThis.LevelEnum;
import com.revature.util.RuntimeData;

public class CourseDaoImple implements CourseDao {

	public static DBConnection cf = DBConnection.getInstance();

	@Override
	public void addCourse(Course course) throws SQLException {

		Connection conn = cf.getConnection();
		String sql = "insert into course values(nextval('course_id_seq'),?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		if (course.getEmployee() != null) {
			ps.setInt(1, course.getEmployee().getId());
		} else {
			ps.setNull(1, java.sql.Types.INTEGER);
		}

		// convert endum
		int courseTypeNum = 0;
		if (course.getCourseType() == null) {
			courseTypeNum = CourseType.OTHER.ordinal();
		} else {
			courseTypeNum = course.getCourseType().ordinal();
		}
		ps.setInt(2, courseTypeNum);
		ps.setString(3, course.getName());
		ps.setString(4, course.getInstitution());
		ps.setBoolean(5, course.isPass());
		ps.setString(6, course.getGradeLetter().toString());
		ps.setDouble(7, course.getGradeNumber());
		ps.setString(8, course.getPresentationDocument());

		// update database
		ps.executeUpdate();
		// update department id
		ResultSet keys = ps.getGeneratedKeys();
		while (keys.next()) {
			course.setId(keys.getInt(1));
		}
		// update runtime data
		RuntimeData.addCourseToMap(course);

	}

	private int checkEmployeeId(Course course) {
		return course.getEmployee().getId();
	}

	private void addEmployeeToDatabase(Employee emp) throws SQLException {
		EmployeeDao empDao = new EmployeeDaoImple();
		empDao.addNewEmployee(emp);
	}


	@Override
	public Course retrieveCourseByCourseId(int id) throws SQLException {
		EmployeeDao empDao = new EmployeeDaoImple();

		// check runtime data first
		if (RuntimeData.checkIfInCourseMap(id)) {
			LogThis.logIt(LevelEnum.INFO, "CourseId Id: " + id
					+ " information already in runtime data. Runtime Data called instead of database call");
			return RuntimeData.getCourseFromMap(id);
		}

		// run connection check
		Connection conn = cf.getConnection();
		String sql = "select * from course where course_id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		Course course = new Course();
		int empId = -1;
		while (rs.next()) {
			course.setId(rs.getInt(1));
			empId = (rs.getInt(2));
			course.setCourseType(CourseType.values()[rs.getInt(3)]);
			course.setName(rs.getString(rs.getString(4)));
			course.setInstitution(rs.getString(5));
			course.setPass(rs.getBoolean(6));
			course.getGradOrPres().setLetterGrade(rs.getString(7));
			course.getGradOrPres().setNumberGrade(rs.getDouble(8));
			course.getGradOrPres().setDocument(rs.getString(9));
		}

		course.setEmployee(empDao.retriveEmployeeById(empId));

		// update runtime data
		RuntimeData.addCourseToMap(course);

		return course;
	}

	@Override
	public List<Course> retriveAllCourses() throws SQLException {
		EmployeeDao empDao = new EmployeeDaoImple();
		Connection conn = cf.getConnection();
		List<Course> courseList = new ArrayList<>();

		// graded
		String sql_graded = "select * from course";
		PreparedStatement ps = conn.prepareStatement(sql_graded);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int empId = -1;
			Course course = new Course();
			course.setId(rs.getInt(1));
			empId = (rs.getInt(2));
			course.setCourseType(CourseType.values()[rs.getInt(3)]);
			course.setName(rs.getString(rs.getString(4)));
			course.setInstitution(rs.getString(5));
			course.setPass(rs.getBoolean(6));
			course.setEmployee(empDao.retriveEmployeeById(empId));
			course.getGradOrPres().setLetterGrade(rs.getString(7));
			course.getGradOrPres().setNumberGrade(rs.getDouble(8));
			course.getGradOrPres().setDocument(rs.getString(9));
			courseList.add(course);
		}


		return courseList;

	}

	@Override
	public List<Course> retrieveCourseByEmployeeId(int id) throws SQLException {
		EmployeeDao empDao = new EmployeeDaoImple();
		Connection conn = cf.getConnection();
		List<Course> courseList = new ArrayList<>();

		// graded
		String sql_graded = "select * from course where employee_id=?";
		
		PreparedStatement ps = conn.prepareStatement(sql_graded);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int empId = -1;
			Course course = new Course();
			course.setId(rs.getInt(1));
			empId = (rs.getInt(2));
			course.setCourseType(CourseType.values()[rs.getInt(3)]);
			course.setName(rs.getString(rs.getString(4)));
			course.setInstitution(rs.getString(5));
			course.setPass(rs.getBoolean(6));
			course.setEmployee(empDao.retriveEmployeeById(empId));
			course.getGradOrPres().setLetterGrade(rs.getString(7));
			course.getGradOrPres().setNumberGrade(rs.getDouble(8));
			course.getGradOrPres().setDocument(rs.getString(9));
			courseList.add(course);
		}


		return courseList;
	}


}
