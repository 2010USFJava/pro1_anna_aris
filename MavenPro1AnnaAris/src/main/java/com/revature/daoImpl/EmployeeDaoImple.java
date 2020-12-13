/*Author: Anna Carlson
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description:  Postgress Implementation  for receiving and adding EMPLOYEE information to a database

 */

package com.revature.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.dao.DepartmentDao;
import com.revature.dao.EmployeeDao;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.util.DBConnection;
import com.revature.util.LogThis;
import com.revature.util.LogThis.LevelEnum;
import com.revature.util.RuntimeData;

//Implementing Lazy Loading. Before calling the database, I will check if the information is already in my Runtime Database.
//Runtime database info will be lost on exiting the program.

public class EmployeeDaoImple implements EmployeeDao {

	public static DBConnection cf = DBConnection.getInstance();
	private static boolean gotFirstLevelSubordinates;
	private static boolean checkForBadChainOfCommand = false;

	@Override
	public Employee retriveEmployeeById(int id) throws SQLException {
		DepartmentDao deptDao = new DepartmentDaoImple();

		try {

			if (RuntimeData.checkIfInEmployeeMap(id)) {
				LogThis.logIt(LevelEnum.INFO, "Employee Id: " + id
						+ " information already in runtime data. Runtime Data called instead of database call");
				return RuntimeData.getEmployeeFromMap(id);
			} else if (id == 0) {
				LogThis.logIt(LevelEnum.INFO, "Employee Id: " + id
						+ " Searching for Employee #0 . Number does not go that low in SQL. Skipping");
				return null;
			}

			else {

				Connection conn = cf.getConnection();
				String sql = "select * from employees where employee_id=?";
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
					emp.setFunds(rs.getDouble(6));
					emp.setTitle(rs.getString(7));
					break;
				}

				// put current employee in database to avoid recursion problems
				RuntimeData.addEmployeeToMap(emp);

				// make appropriate additional calls

				// Add Supervisor Information
				/*
				 * Currently this is set up to chain all the way up to the top. May want to stop
				 * that in future updates. My fist instinct is to put a lazy loader in, but the
				 * current method I can think of isn't very efficient. I'll come back to this
				 * problem later if I have time.
				 */
				if (supervisorId != null) {
					LogThis.logIt(LevelEnum.INFO, "Employee: " + id + "/t Receiving Supervisor Information");
					emp.setSupervisor(retriveEmployeeById(supervisorId));
				} else {
					LogThis.logIt(LevelEnum.INFO, "Employee: " + id + "/t No Supervisor Information");
				}

				if (departId != null) {
					LogThis.logIt(LevelEnum.INFO, "Employee: " + id + "/t receiving Department Information");
					emp.setDepartment(deptDao.retriveDeptById(departId));
				} else {
					LogThis.logIt(LevelEnum.INFO, "Employee: " + id + "/t No Department Information");
				}

				// Add Subordinates
				/*
				 * Currently have it set up to only return the subordinates if first
				 * implementation of this method. To Change this, look at the
				 * retriveSubordinatesById(id) section of code
				 */
				emp.setSubordinates(retriveSubordinatesById(id));

				return emp;

			}

		} catch (NullPointerException e) {
			LogThis.logIt(LevelEnum.ERROR,
					"Null pointer Exception occured in EmployeeDaoImple retriveEmployeeById(int id)");
			LogThis.logIt(LevelEnum.ERROR, e.toString());
			System.err.println(e);
			return null;
		}
	}

	@Override
	public void addNewEmployee(Employee employee) throws SQLException {

		// if id !=0 they are already in the system
		if (employee.getId() != 0) {
			LogThis.logIt(LevelEnum.INFO, "Attempted to add an Employee who already had an ID. Skipping");
		} else {

			//in order to avoid bad references in my database, I want to make sure the supervisor information is entered 
			//before the subbordinates 
			if (employee.getSupervisor() != null) {
				// in the event a supervisor hasn't been created yet, do that now
				if (employee.getSupervisor().getId() == 0) {
					// Logically there won't be a loop here, but
					// it could be entered that way.

					// for now I'll just check if each employee is each other's supervisor
					// or that the employee is not their own supervisor.
					if (employee.getSupervisor() != employee || employee.getSupervisor().getSupervisor() != employee) {
						addNewEmployee(employee.getSupervisor());
					}
				}
			}

			Connection conn = cf.getConnection();

			String sql = "insert into employees values(nextval('emp_id_seq'),?,?,?,?,?,?);";

			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			Employee sup = employee.getSupervisor();
			if (sup != null) {
				ps.setInt(3, sup.getId());
			} else {
				ps.setNull(3, java.sql.Types.INTEGER);
			}
			Department dept = employee.getDepartment();
			if (dept.getId() != 0) {

				ps.setInt(4, dept.getId());
			} else {
				ps.setNull(4, java.sql.Types.INTEGER);
			}
			
			ps.setDouble(5, employee.getFunds());
			ps.setString(6,employee.getTitle());
			// update database
			ps.executeUpdate();

			// update employee id to what popped out of the database
			ResultSet keys = ps.getGeneratedKeys();
			while (keys.next()) {
				employee.setId(keys.getInt(1));
			}

			// update runtime data
			RuntimeData.addEmployeeToMap(employee);
		}

	}

	@Override
	public ArrayList<Employee> retriveSubordinatesById(int id) throws SQLException {
		LogThis.logIt(LevelEnum.INFO, "Checking for subordinate for " + id);
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

		// Calling this after the avalialbe list check so I don't accidently overwrite
		// an existing list
		if (gotFirstLevelSubordinates) {
			LogThis.logIt(LevelEnum.DEBUG, "Stopping Recursion for " + id);
			return null;
		}

		ArrayList<Integer> subordinateIds = new ArrayList<>();

		try {
			Connection conn = cf.getConnection();
			String sql = "select sub_id from supervisor_sub_relations where sup_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				subordinateIds.add(rs.getInt(1));
			}
		} catch (NullPointerException e) {
			LogThis.logIt(LevelEnum.ERROR,
					"Null pointer Exception occured in EmployeeDaoImple retriveSubordinatesById(int id)");
			LogThis.logIt(LevelEnum.ERROR, e.toString());
			System.err.println(e);
			return null;
		}

		// avoid recursivly calling this function for the duration of this search
		gotFirstLevelSubordinates = true;
		// next pull info for each subordinate.
		ArrayList<Employee> subordinates = new ArrayList<>();

		for (Integer subId : subordinateIds) {
			subordinates.add(retriveEmployeeById(subId));
		}
		// turn off subordinate blocker before return
		gotFirstLevelSubordinates = false;

		return subordinates;
	}

	@Override
	public void updateEmployeeDepartment(int empId, int deptId) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "update employees set department_id=? where employee_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, deptId);
		ps.setInt(2, empId);

//		//update database
		ps.executeUpdate();
//		
		// and make sure this update has happened internally as well
		RuntimeData.getEmployeeFromMap(empId).setDepartment(RuntimeData.getDepartmentFromMap(deptId));
	}

}
