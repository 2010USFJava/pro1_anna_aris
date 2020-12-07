package com.revature.meta;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {
	
	private static ConnFactory cf;
	
	private ConnFactory() {
		super();
	}
	
	public static synchronized ConnFactory getInstance() {
		if(cf==null) {
			cf=new ConnFactory();
		}
		return cf;
		
	}
	
	public Connection getConnection() {
		Connection conn=null;
		Properties prop = new Properties();
		
		//issues loading "src/main/resources/database.properties"
		//for now I'll hard code the data
		
//		private String url= "jdbc:postgresql://database-1.c3q1kvrmwjgp.us-east-2.rds.amazonaws.com:5432/supervillaindb";
//		private String username = "vill";
//		private String password = "p4ssw0rd";
//		
		
		String url="jdbc:postgresql://java2010usf.cfqfijvzidkq.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=employee_reimbursement";
		String username="annacarl";
		String password="hrMVnvuh9RETnA6";
		
		try {
//			prop.load(new FileReader("src/main/resources/database.properties"));
//			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
			conn = DriverManager.getConnection(url, username, password);

		} catch(SQLException e) {
			e.printStackTrace();
		}
	
//		catch(FileNotFoundException e) {
//			e.printStackTrace();
//		} catch(IOException e) {
//			e.printStackTrace();
//		} 
		
		return conn;
		
		
	}

}
