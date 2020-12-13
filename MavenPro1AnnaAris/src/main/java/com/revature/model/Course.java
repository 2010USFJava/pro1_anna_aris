/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Modleing The Courses that can be applied to get reimbursement from
*LastUpdate: 12/12/20
 */


package com.revature.model;

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
	private GradeOrPresentation gradOrPres;
	private boolean pass;
	
	public Grade.LetterGrade getGradeLetter() {
		if((gradOrPres.getClass().isInstance(Grade.class))) {
			Grade grade = (Grade)gradOrPres;
			return grade.getLetterGrade();
		} else {

			return null;
				
		}
	}
	
	public Double getGradeNumber() {
		if((gradOrPres.getClass().isInstance(Grade.class))) {
			Grade grade = (Grade)gradOrPres;
			return grade.getNumberGrade();
		} else {

			return null;
				
		}
	}
	
	public String getPresentationDocument() {
		if((gradOrPres.getClass().isInstance(Presentation.class))) {
			Presentation pres = (Presentation)gradOrPres;
			return pres.getDocument();
		} else {

			return null;
				
		}		
	}
	
	
}
