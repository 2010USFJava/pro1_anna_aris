package com.revature.model;

public class Grade implements GradeOrPresentation {
	public enum LetterGrade{
		A,
		B,
		C,
		D,
		F
	}
	
	//different schools may have different grading systems
	private double numberGrade=0;
	private LetterGrade letterGrade=LetterGrade.F;
	
	public double getNumberGrade() {
		return numberGrade;
	}
	public void setNumberGrade(double numberGrade) {
		this.numberGrade = numberGrade;
		letterGrade=convertNumberGradeToLetterGrade(numberGrade);
	}
	public LetterGrade getLetterGrade() {
		return letterGrade;
	}
	public void setLetterGrade(LetterGrade letterGrade) {
		this.letterGrade = letterGrade;
		numberGrade=convertLetterGradeToNumberGrade(letterGrade);
	}

	
	private LetterGrade convertNumberGradeToLetterGrade(double numberGrade) {
		if (numberGrade>=90) {
			return LetterGrade.A;
		} else if (numberGrade>=80) {
			return LetterGrade.B;
		} else if (numberGrade>=70) {
			return LetterGrade.C;
		} else if (numberGrade>=60) {
			return LetterGrade.D;
		} else {
			return letterGrade.F;
		}
	}
	
	private double convertLetterGradeToNumberGrade(LetterGrade letterGrade) {
		if (letterGrade.equals(LetterGrade.A)) {
			return 100;
		} else if(letterGrade.equals(LetterGrade.B)) {
			return 89;
		}else if(letterGrade.equals(LetterGrade.C)) {
			return 79;
		}else if(letterGrade.equals(LetterGrade.D)) {
			return 69;
		}else {
			return 59;
		}
		
	}
	
	
}
