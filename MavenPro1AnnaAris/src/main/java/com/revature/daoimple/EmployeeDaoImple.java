package com.revature.daoimple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.dao.DepartmentDao;
import com.revature.dao.EmployeeDao;
import com.revature.meta.ConnFactory;
import com.revature.meta.LogThis;
import com.revature.meta.LogThis.LevelEnum;
import com.revature.meta.RuntimeData;
import com.revature.model.Department;
import com.revature.model.Employee;

//Implementing Lazy Loading. Before calling the database, I will check if the information is already in my Runtime Database.
//Runtime database info will be lost on exiting the program.

public class EmployeeDaoImple implements EmployeeDao {

	public static ConnFactory cf = ConnFactory.getInstance();
	private static boolean gotFirstLevelSubordinates;

	@Override
	public Employee retriveEmployeeById(int id) throws SQLException {
		DepartmentDao deptDao = new DepartmentDaoImple();

		try {

			if (RuntimeData.checkIfInEmployeeMap(id)) {
				LogThis.logIt(LevelEnum.INFO, "Employee Id: " + id
						+ " information already in runtime data. Runtime Data called instead of database call");
				return RuntimeData.getEmployeeFromMap(id);
			} else {

				Connection conn = cf.getConnection();
				String sql = "select * from employees where id=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();

				// set up employee with new info
				Employee emp = new Employee();
				Integer supervisorId = null;
				Integer departId = null;
				while (rs.next()) {
					emp.setId(rs.getInt(1));
					emp.setFirstName(rs.getString(2));
					emp.setLastName(rs.getString(3));
					supervisorId = (rs.getInt(4));
					departId = (rs.getInt(5));
					break;

				}

				// put current employee in database to avoid recursion problems
				RuntimeData.addEmployeeToMap(emp);

				// make appropriate additional calls
				
				//Add Supervisor Information
				/*Currently this is set up to chain all the way up to the top. May want to stop that in future updates.
				*My fist instinct is to put a lazy loader in, but the current method I can think of isn't very efficient.
				*I'll come back to this problem later if I have time.
				*/
				LogThis.logIt(LevelEnum.INFO, "Employee: " + id + "/t Reciving Supervisor Information");
				emp.setSupervisor(retriveEmployeeById(supervisorId));
				
				
				LogThis.logIt(LevelEnum.INFO, "Employee: " + id + "/t Reciving Department Information");
				emp.setDepartment(deptDao.retriveDeptById(departId));
				
				// Add Subordinates
				/*Currently have it set up to only return the subordinates if first implementation of this method.
				* To Change this, look at the retriveSubordinatesById(id) section of code
				*/
				emp.setSubordinates(retriveSubordinatesById(id));
				

				return emp;

			}

		} catch (NullPointerException e) {
			LogThis.logIt(LevelEnum.ERROR, "Null pointer Exception occured in EmployeeDaoImple retriveEmployeeById(int id)");
			LogThis.logIt(LevelEnum.ERROR, e.toString());
			System.err.println(e);
			return null;
		}
	}

	@Override
	public void addNewEmployee(Employee employee) throws SQLException {


		Connection conn = cf.getConnection();

		String sql ="insert into employees values(nextval('emp_id_seq'),?,?,?,?);";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, employee.getFirstName());
		ps.setString(2, employee.getLastName());
		Employee sup=employee.getSupervisor();
		if(sup!=null) {
			ps.setInt(3, sup.getId());	
		}
		
		Department dept = employee.getDepartment();
		if(dept!=null) {
			ps.setInt(4, dept.getId());	
		}
		
		//update database
		ps.executeQuery();
		
		//update employee id to what popped out of the database
		ResultSet keys=ps.getGeneratedKeys();
		while(keys.next()) {
			employee.setId(keys.getInt(1));
		}
		
		//update runtime data
		RuntimeData.addEmployeeToMap(employee);
		
				

	}

	@Override
	public ArrayList<Employee> retriveSubordinatesById(int id) throws SQLException {
		// First, I want to see if this info is already in my runtime data.
		if (RuntimeData.checkIfInEmployeeMap(id)) {
			Employee sup = RuntimeData.getEmployeeFromMap(id);
			if (sup.getSubordiates() != null) {
				LogThis.logIt(LevelEnum.DEBUG, "Emp: " + id
						+ "/tRan retriveSubordinatesById(int id) on Employee who already had subordinate list");
				return (ArrayList) sup.getSubordiates();
			}
		}

		// for now I don't need the subordinat's subordinates,
		// Since I'm calling this recusrivly, I'll use a class varible to track if I'm
		// recusing or not;
		
		//Calling this after the avalialbe list check so I don't accidently overwrite an existing list
		if (gotFirstLevelSubordinates) {
			return null;
		}
		

		ArrayList<Integer> subordinateIds= new ArrayList<>();	
		
		try {
			Connection conn = cf.getConnection();
			String sql = "select * from supervisor_sub_relations where sup_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
					
			while (rs.next()) {
				subordinateIds.add(rs.getInt(1));
			}
		} catch (NullPointerException e) {
			LogThis.logIt(LevelEnum.ERROR, "Null pointer Exception occured in EmployeeDaoImple retriveSubordinatesById(int id)");
			LogThis.logIt(LevelEnum.ERROR, e.toString());
			System.err.println(e);
			return null;
		}
		
		//avoid recursivly calling this function for the duration of this search
		gotFirstLevelSubordinates=true;
		//next pull info for each subordinate.
		ArrayList<Employee> subordinates= new ArrayList<>();
		
		for (Integer subId:subordinateIds) {
			subordinates.add(retriveEmployeeById(subId));
		}
		//turn off subordinate blocker before return
		gotFirstLevelSubordinates=false;
		
		return subordinates;
	}

}
