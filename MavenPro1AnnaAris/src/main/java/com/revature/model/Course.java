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
	enum CourseType{
		UNIVERSITY,
		SEMINAR,
		CERT_PREP,
		CERTIFICATION,
		TECHINICAL,
		OTHER
	}
	
	private Employee employee;
	private CourseType courseType;
	private String name;
	private String institution;
	private GradeOrPresentation gradOrPres;
	private boolean pass;
	
}
