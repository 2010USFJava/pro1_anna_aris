package com.revature.model;

public class Employee {
	
	private int employeeId;
	private String firstName;
	private String lastName;
	private String title;
	private String username;
	private String password;
	private int yearlyAwards;
	
	public Employee(String firstName, String lastName) {
		
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Employee(int employeeId, String firstName, String lastName, String title, String username, String password, int yearlyAwards) {
		
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.username = username;
		this.password = password;
		this.yearlyAwards = yearlyAwards;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getYearlyAwards() {
		return yearlyAwards;
	}
	public void setYearlyAwards(int yearlyAwards) {
		this.yearlyAwards = yearlyAwards;
	}
	
	

	
	
}
