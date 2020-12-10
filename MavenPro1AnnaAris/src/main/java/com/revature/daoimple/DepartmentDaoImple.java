/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Postgress Implementation for receiving and adding department information to a database
 */

package com.revature.daoimple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.dao.DepartmentDao;
import com.revature.dao.EmployeeDao;
import com.revature.meta.ConnFactory;
import com.revature.meta.LogThis;
import com.revature.meta.LogThis.LevelEnum;
import com.revature.meta.RuntimeData;
import com.revature.model.Department;
import com.revature.model.Employee;

public class DepartmentDaoImple implements DepartmentDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public Department retriveDeptById(Integer departId) throws SQLException{
		EmployeeDao empDao=new EmployeeDaoImple();
		
		//check runtime data first
		if (RuntimeData.checkIfInDepartmentMap(departId)) {
			LogThis.logIt(LevelEnum.INFO, "Department Id: " + departId
					+ " information already in runtime data. Runtime Data called instead of database call");
			return RuntimeData.getDepartmentFromMap(departId);
		} 

		//run connection check
		Connection conn = cf.getConnection();
		String sql = "select * from departments where department_id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, departId);
		ResultSet rs = ps.executeQuery();
		
		Department dept = new Department();
		int deptHeadId=-1;
		while (rs.next()) {
			dept.setId(departId);
			dept.setName(rs.getString(2));
			deptHeadId=(rs.getInt(3));
		}
		
		//update runtime data
		RuntimeData.addDepartmentToMap(dept);
		
		
		Employee emp = empDao.retriveEmployeeById(deptHeadId);
		dept.setDepartmentHead(emp);
		return dept;
	}

	@Override
	public void addDepatment(Department department) throws SQLException {
		Connection conn = cf.getConnection();
		EmployeeDao empDao = new EmployeeDaoImple();
		
		//check if Employee is entered in the database already
		//if less then 1, not in database
		if(department.getDepartmentHead()!=null 
				&& !RuntimeData.checkIfInDepartmentMap(department.getDepartmentHead().getId())
				&& department.getDepartmentHead().getId()<1) {
			empDao.addNewEmployee(department.getDepartmentHead());
			
		}

		String sql ="insert into departments values(nextval('dep_id_seq'),?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, department.getName());
		ps.setInt(2, department.getDepartmentHead().getId());
		//update database
		ps.executeUpdate();
		
		
		//Update department id number

		
		//update employee id to what popped out of the database
		ResultSet keys=ps.getGeneratedKeys();
		while(keys.next()) {
			department.setId(keys.getInt(1));
		}
		
		//update runtime data
		RuntimeData.addDepartmentToMap(department);
		
		//also update department head data with generated id from database
		Employee head = department.getDepartmentHead();
		if (head!=null) {
			empDao.updateEmployeeDepartment(head.getId(),department.getId());	
		}
	}

}
