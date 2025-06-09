package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	
	Connection conn;
	public void getDbconnection(String url,String username,String password) throws Throwable
	{
		try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
		 conn=DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
			
		}
	}
		
		public void getDbconnection() throws Throwable
		{
			try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			
			 conn=DriverManager.getConnection("jsbc:mysql://localhost:3306/employee_db","root","Root");
			}
			catch(Exception e) {
				
			}
		
	}
	
	public void closeDbaseconnection() throws Throwable
	{
		try {
		conn.close();
		}
		 catch(Exception e) {
				
			}
		
	}
	public ResultSet executeSelectQuery(String query) throws SQLException
	{	ResultSet result=null;
		try {
		Statement stat=conn.createStatement();
		 result=stat.executeQuery(query);
		}
        catch(Exception e) {
			
		}
		return result;
	}
	
	public int executeNonSelectQuery(String query) throws SQLException
	{	 
		int result=0;
	
		try {
		Statement stat=conn.createStatement();
		 result=stat.executeUpdate(query);
		}
        catch(Exception e) {
			
	}
		return result;
	}
	
	
}
