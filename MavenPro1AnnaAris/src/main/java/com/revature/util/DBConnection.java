package com.revature.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static DBConnection db;
	
	private DBConnection() {
		
	}
	public static synchronized DBConnection getInstance() {
		if (db == null) {
			db = new DBConnection();
		}
		return db;
	}
	public Connection getConnection() {
		Connection connect = null;
		String resourceName = "database.properties"; 
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		
		try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
			props.load(resourceStream);
			Class.forName("org.postgresql.Driver");
			//When Using Aris Credentials
//			connect = DriverManager.getConnection(props.getProperty("host"), props.getProperty("user"), props.getProperty("password"));
			//When using Anna Credentials
			connect = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return connect;
	}
}
