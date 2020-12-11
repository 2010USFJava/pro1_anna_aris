package com.revature.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.revature.meta.LogThis;
import com.revature.meta.LogThis.LevelEnum;

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
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connect = null;
		Properties prop = new Properties();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("database.properties");
			prop.load(input);
//			prop.load(new FileReader("database.properties"));
			LogThis.logIt(LevelEnum.DEBUG, prop.getProperty("url"));
			connect = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return connect;
	}
}
