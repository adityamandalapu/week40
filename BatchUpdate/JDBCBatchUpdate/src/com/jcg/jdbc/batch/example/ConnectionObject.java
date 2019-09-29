package com.jcg.jdbc.batch.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionObject {
	static String DB_DRIVER="com.mysql.jdbc.Driver";
	static String DB_CONNECTION="jdbc:mysql://localhost:3306/test";
	static String DB_USER="userName";
	static String DB_PASSWORD="password";	

	public static Connection getConnection(){
		Connection connection =  null;		
		try{
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;		
	}
}
