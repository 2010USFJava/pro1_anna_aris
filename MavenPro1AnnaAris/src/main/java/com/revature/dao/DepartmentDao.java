package com.revature.dao;

import java.sql.SQLException;

import com.revature.model.Department;

public interface DepartmentDao {

	Department retriveDeptById(Integer departId) throws SQLException;
	void addDepatment(Department department) throws SQLException;
	

}
