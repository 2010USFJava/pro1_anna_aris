package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
		//Properties prop = new Properties();
		String resourceName = "database.properties"; // could also be a constant
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		
		try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
			props.load(resourceStream);
			Class.forName("org.postgresql.Driver");
			//prop.load(new FileReader("src/main/resources/database.properties"));
			connect = DriverManager.getConnection(props.getProperty("host"), props.getProperty("user"), props.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return connect;
	}
}
