package org.paul.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DerbyDAO {
	private static DataSource derbyDataSource = null;
	private static Context context = null;
	
	public static DataSource derbyConn() throws Exception {
		if (derbyDataSource != null) {
			
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

}
