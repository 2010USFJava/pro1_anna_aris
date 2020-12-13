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
import com.revature.model.Grade;
import com.revature.model.Presentation;
import com.revature.util.DBConnection;
import com.revature.util.LogThis;
import com.revature.util.LogThis.LevelEnum;
import com.revature.util.RuntimeData;

public class CourseDaoImple implements CourseDao {

	public static DBConnection cf = DBConnection.getInstance();

	@Override
	public void addCourse(Course course) throws SQLException {
		if (!course.getInstitution().isEmpty() && !course.getName().isEmpty()) {
			Connection conn = cf.getConnection();
			String sql = "insert into course(nextval('course_id_seq'),?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, course.getEmployee().getId());
			// convert endum
			int courseTypeNum = course.getCourseType().ordinal();
			ps.setInt(1, course.getEmployee().getId());
			ps.setInt(2, courseTypeNum);
			ps.setString(3, course.getName());
			ps.setString(4, course.getInstitution());
			ps.setBoolean(5, course.isPass());
			// update database
			ps.executeUpdate();
			// update department id
			ResultSet keys = ps.getGeneratedKeys();
			while (keys.next()) {
				course.setId(keys.getInt(1));
			}
			// if gradeOrPresentation already filled otu
			addToGradOrPresTable(course);
			// update runtime data
			RuntimeData.addCourseToMap(course);
		} else {
			LogThis.logIt(LevelEnum.ERROR, "Did not add course. As either name or institutaion name were missing");
		}

	}

	private int checkEmployeeId(Course course) {
		return course.getEmployee().getId();
	}

	private void addEmployeeToDatabase(Employee emp) throws SQLException {
		EmployeeDao empDao = new EmployeeDaoImple();
		empDao.addNewEmployee(emp);
	}

	@Override
	public void addToGradOrPresTable(Course course) throws SQLException {
//		 if the employee has not been added to the database yet, that needs to happen 
		ArrayList<String> problems=new ArrayList();
		if(course.getEmployee()!=null) {
			//and
			if(checkEmployeeId(course)==0) {
				addEmployeeToDatabase(course.getEmployee());
			

				//If this course has an id of zero, it had not yet been added to database and 
				//that needs to be done first
				if(course.getId()==0) {
					addCourse(course);
					if(course.getId()==0) {
						problems.add("Course could not be added. Could not update information");
					}
				}
				
			}

		} else {

			problems.add("No Employee Added To This Course. Could not add update information");
		}
		
		
		
		

		Connection conn = cf.getConnection();
		//check if already in table, in which case update
		
		
		
		if (course.getId()!=0 && problems.size()==0) {
			//else add to table;
			if (course.getGradOrPres() == null) {
				//if no grade or presentation has been assigned. Nothing needs to be added

			} else if (course.getGradOrPres().getClass().isInstance(Grade.class)) {
				//if grade has been assigned
				String sql="insert into grade values(?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, course.getId());
				ps.setString(2, course.getGradeLetter().toString());
				ps.setDouble(3, course.getGradeNumber());
				ps.executeUpdate();
				
				LogThis.logIt(LevelEnum.INFO, "Grade added to course database ");
			} else if (course.getGradOrPres().getClass().isInstance(Presentation.class)) {
				String sql="insert into presentation values(?,?)";
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, course.getId());
				ps.setString(2, course.getPresentationDocument());
				ps.executeUpdate();
				
				LogThis.logIt(LevelEnum.INFO, "Presentation added to course database ");
			} else {
				problems.add("Attempted to add course that contained something other then a grade or presentation");
			} 
		} 

		
		if(problems.size()>0) {
			LogThis.logIt(LevelEnum.ERROR, "Problem with Updating Course Information");
			for(String each:problems) {
				LogThis.logIt(LevelEnum.ERROR, each);
			}
		} else {
			if(course.getGradOrPres() != null) {

				LogThis.logIt(LevelEnum.INFO, "Added Course Grade: "+course.getId()+" to database");	
			}
		}

	}

	@Override
	public Course retrieveCourseByCourseId(int id) throws SQLException {
		EmployeeDao empDao=new EmployeeDaoImple();
		
		//check runtime data first
		if (RuntimeData.checkIfInCourseMap(id)) {
			LogThis.logIt(LevelEnum.INFO, "CourseId Id: " + id
					+ " information already in runtime data. Runtime Data called instead of database call");
			return RuntimeData.getCourseFromMap(id);
		} 

		//run connection check
		Connection conn = cf.getConnection();
		String sql = "select * from course where course_id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		Course course = new Course();
		int empId=-1;
		while (rs.next()) {
			course.setId(rs.getInt(1));
			empId=(rs.getInt(2));
			course.setCourseType(CourseType.values()[rs.getInt(3)]);
			course.setName(rs.getString(rs.getString(4)));
			course.setInstitution(rs.getString(5));
			course.setPass(rs.getBoolean(6));
		}

		course.setEmployee(empDao.retriveEmployeeById(empId));

		//update runtime data
		RuntimeData.addCourseToMap(course);
		
	
		return course;
	}

	@Override
	public List<Course> retrieveCourseByEmployeeId(int id) throws SQLException {
		EmployeeDao empDao=new EmployeeDaoImple();
		
		
		//I don't think it makes sense to call this here, but I'll just tab it out for now
		//check runtime data first
//		if (RuntimeData.checkIfInCourseMap(id)) {
//			LogThis.logIt(LevelEnum.INFO, "CourseId Id: " + id
//					+ " information already in runtime data. Runtime Data called instead of database call");
//			return RuntimeData.getCourseFromMap(id);
//		} 

		//run connection check
		Connection conn = cf.getConnection();
		String sql = "select * from course where employee_id =1;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		
		List<Course> courseList = new ArrayList<>();
		while (rs.next()) {
			int empId=-1;
			Course course = new Course();
			course.setId(rs.getInt(1));
			empId=(rs.getInt(2));
			course.setCourseType(CourseType.values()[rs.getInt(3)]);
			course.setName(rs.getString(rs.getString(4)));
			course.setInstitution(rs.getString(5));
			course.setPass(rs.getBoolean(6));
			course.setEmployee(empDao.retriveEmployeeById(empId));
			
			//add to Course List
			courseList.add(course);
			//update runtime data
			RuntimeData.addCourseToMap(course);
		}

		


		
	
		return courseList;
	}

}
