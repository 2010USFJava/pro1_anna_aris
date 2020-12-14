package com.revature.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.CourseDao;
import com.revature.daoImpl.CourseDaoImple;
import com.revature.model.Course;
import com.revature.model.Employee;
import com.revature.model.GradeOrPresentation;
import com.revature.util.LogThis;
import com.revature.util.LogThis.LevelEnum;

public class GradeController {
	

	public static String changeRequest(HttpServletRequest req) {
		Employee emp = (Employee) req.getSession().getAttribute("currentEmp");
		
		return "myresources/html/grade_upload.html";
	}
	public static String addGrade(HttpServletRequest req) {
		LogThis.logIt(LevelEnum.DEBUG, "IN add grade");
		Employee emp = (Employee) req.getSession().getAttribute("currentEmp");
		Course course = new Course();
		
		if(emp==null) {
			LogThis.logIt(LevelEnum.ERROR, "No employee in system at moment.");
			
		}else {
			course.setEmployee(emp);
		} 
		
		
		
		String course_type = req.getParameter("course_type");
		course.setCourseType(course_type);
		course.setName(req.getParameter("course_name"));
		String institution = req.getParameter("institution");
		course.setInstitution(institution);
		String grade_or_presentation = req.getParameter("grade_or_presentation");
		
		if(grade_or_presentation.equals("radio_grade")) {
			String type_of_grade=req.getParameter("type_of_grade");
			
			if(type_of_grade.equals("radio_num_grade")) {
				double number_grade;
				try {
					number_grade = Double.parseDouble(req.getParameter("number_grade"));
				} catch (Exception e) {
					LogThis.logIt(LevelEnum.ERROR,"Not a vaild number. Setting to 0");
					number_grade=0;
				}
				course.getGradOrPres().setNumberGrade(number_grade);
			}else if(type_of_grade.equals("radio_letter_grade")) {
				course.getGradOrPres().setLetterGrade(req.getParameter("letter_grade"));
			}else {
				//NA
			}
		} else {

			course.getGradOrPres().setDocument((req.getParameter("presentation")));
//			course.setGradOrPres(pres);
		}
		
		CourseDao courseDao= new CourseDaoImple();
		try {
			courseDao.addCourse(course);
		} catch (SQLException e) {
			LogThis.logIt(LevelEnum.ERROR, "Problem in Grade Controller: Add Grade");
			// TODO Auto-generated catch block
			return "myresources/html/incomplete.html";
//			e.printStackTrace();
		}

		return "myresources/html/landingPage.html";
	}
}
