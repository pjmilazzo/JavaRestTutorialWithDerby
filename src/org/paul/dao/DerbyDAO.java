package org.paul.dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DerbyDAO {
	private static DataSource derbyDataSource = null;
	private static Context context = null;
	
	//TODO change derbyConn() to private and fix broken methods after change
	public static DataSource derbyConn() throws Exception {
		if (derbyDataSource != null) {
			return derbyDataSource;
		}
		
		try {
			if (context == null) {
				context = new InitialContext();
			}
			derbyDataSource = (DataSource) context.lookup("java:comp/env/jdbc/testdb");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return derbyDataSource;
	}
	
	protected static Connection derbyPcPartsConnection() {
		Connection conn = null;
		
		try {
			conn = derbyConn().getConnection();
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}
