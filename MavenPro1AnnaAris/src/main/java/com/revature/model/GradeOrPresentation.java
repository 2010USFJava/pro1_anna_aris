package com.revature.model;

import lombok.Data;

@Data
public class GradeOrPresentation {

	public enum LetterGrade {
		A, B, C, D, F
	}

	// different schools may have different grading systems
	private double numberGrade = 0;
	private LetterGrade letterGrade;
	private String document;

	public double getNumberGrade() {
		return numberGrade;
	}

	public void setNumberGrade(double numberGrade) {
		this.numberGrade = numberGrade;
		letterGrade = convertNumberGradeToLetterGrade(numberGrade);
	}

	public LetterGrade getLetterGrade() {
		if (letterGrade == null) {
			letterGrade = convertNumberGradeToLetterGrade(numberGrade);
		}
		return letterGrade;
	}

	public void setLetterGrade(LetterGrade letterGrade) {
		this.letterGrade = letterGrade;
//		numberGrade=convertLetterGradeToNumberGrade(letterGrade);
	}

	private LetterGrade convertNumberGradeToLetterGrade(double numberGrade) {
		if (numberGrade >= 90) {
			return LetterGrade.A;
		} else if (numberGrade >= 80) {
			return LetterGrade.B;
		} else if (numberGrade >= 70) {
			return LetterGrade.C;
		} else if (numberGrade >= 60) {
			return LetterGrade.D;
		} else {
			return letterGrade.F;
		}
	}

	public void setLetterGrade(String parameter) {
		if (parameter.toLowerCase().equals("a")) {
			letterGrade = LetterGrade.A;
		} else if (parameter.toLowerCase().equals("b")) {
			letterGrade = LetterGrade.B;
		} else if (parameter.toLowerCase().equals("c")) {
			letterGrade = LetterGrade.C;
		} else if (parameter.toLowerCase().equals("d")) {
			letterGrade = LetterGrade.D;
		} else {
			letterGrade = letterGrade.F;
		}

	}
}
