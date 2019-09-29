package com.jcg.jdbc.batch.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	Connection conn = ConnectionObject.getConnection();
	
	public static void main(String[] args) {
		App app = new App();
		try {
			app.batchUpdateUsingPreparedStatement();
			//app.batchUpdateUsingStatement();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
	
	public void batchUpdateUsingStatement() throws SQLException{		
		int[] result=null;
		try {
			Statement stmt = conn.createStatement();
			conn.setAutoCommit(false);
			String SQL="update person set firstName='New First Name',lastName='New Last Name' where id=1";
			stmt.addBatch(SQL);
			SQL="update person set firstName='New First Name',lastName='New Last Name' where id=2";
			stmt.addBatch(SQL);
			result = stmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}finally{
			if(conn!=null)
				conn.close();
		}
		System.out.println("Number of rows affected: "+ result.length);
		
	}
	
	public void batchUpdateUsingPreparedStatement() throws SQLException{
		int[] result=null;
		String SQL="update person set firstName=?,lastName=? where id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(SQL);
			conn.setAutoCommit(false);	
			stmt.setString(1, "Abc");
			stmt.setString(2, "Def");
			stmt.setInt(3, 1);
			stmt.addBatch();
			
			stmt.setString(1, "Xyz");
			stmt.setString(2, "Uvw");
			stmt.setInt(3, 2);
			stmt.addBatch();
			result = stmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}finally{
			if(conn!=null)
				conn.close();
		}
		System.out.println("Number of rows affected: "+ result.length);
	}

}
