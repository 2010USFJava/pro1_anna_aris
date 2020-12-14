/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Modleing The Courses that can be applied to get reimbursement from
*LastUpdate: 12/12/20
 */


package com.revature.model;

import com.revature.model.GradeOrPresentation.LetterGrade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	public enum CourseType{
		UNIVERSITY,
		SEMINAR,
		CERT_PREP,
		CERTIFICATION,
		TECHINICAL,
		OTHER
	}
	
	private int id=0;
	private Employee employee;
	private CourseType courseType;
	private String name;
	private String institution;
	private GradeOrPresentation gradOrPres=new GradeOrPresentation();
	private boolean pass;
	
	public void setCourseType(CourseType courseType) {
		this.courseType=courseType;
	}
	public void setCourseType(String string) {
		if(string.toLowerCase().equals("university")) {
			courseType= CourseType.UNIVERSITY;
		} else if(string.toLowerCase().equals("seminar")) {
			courseType= CourseType.SEMINAR;
		} else if(string.toLowerCase().equals("cert_prep")) {
			courseType= CourseType.CERT_PREP;
		} else if(string.toLowerCase().equals("cert")) {
			courseType= CourseType.CERTIFICATION;
		} else if(string.toLowerCase().equals("technical")) {
			courseType=CourseType.TECHINICAL;
		} else {
			courseType=CourseType.OTHER;
		}
	}
	
	public LetterGrade getGradeLetter() {
			return gradOrPres.getLetterGrade();
	}
	
	public Double getGradeNumber() {

			return gradOrPres.getNumberGrade();
		}
	
	
	public String getPresentationDocument() {
			return gradOrPres.getDocument();
	}
	
	
}
