/*Author: Anna Carlson
 * Source: Followed Tutorial from  J. "Matt" Knighten For base code
 * Original Project: Revature BootCamp Project 1: Employee Reimbursement Application
 * Description: Creates a connection with a database from information stored in the database.properties item,
 * which is itself stored in src/main/resources
 */

package com.revature.meta;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {

	private static ConnFactory cf;

//	
	private ConnFactory() {
		super();
	}

	public static synchronized ConnFactory getInstance() {
		if (cf == null) {
			cf = new ConnFactory();
		}
		return cf;

	}

	public Connection getConnection() {
		Connection conn = null;

		Properties prop = new Properties();

		try {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("database.properties");
			prop.load(input);
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
					prop.getProperty("password"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conn;

	}

}
