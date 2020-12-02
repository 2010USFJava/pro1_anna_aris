package com.revature.model;

public class Department implements DepartmentInt {
	private String name;
	private int id;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String string) {
		this.name=string;

	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id=id;

	}

}
