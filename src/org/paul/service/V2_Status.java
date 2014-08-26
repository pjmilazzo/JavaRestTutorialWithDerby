package org.paul.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.paul.dao.DerbyDAO;

/**
 * This is a the root path for our restful api service
 * In the web.xml file, we specified that /api/* needs to be in the URL to
 * get to this class.
 * 
 * We are versioning the class in the url path .
 * 
 * Example how to get to this root of this api resource:
 * http://localhost:8080/JavaRestTutorial/api/v1/status
 * 
 * @author paul.j.milazzo
 *
 */

@Path("/v2/status")
public class V2_Status {
	
	private static final String api_version = "2.0";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}

	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:  " + api_version + "</p>";
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		String query = "SELECT CURRENT_TIMESTAMP FROM SYSIBM.SYSDUMMY1";

		String returnString = null;
		PreparedStatement ps = null;
		Connection conn = null;
		String myString = null;

		try {
			conn = DerbyDAO.derbyConn().getConnection();
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				myString = rs.getTimestamp(1).toString();
			}
			
			rs.close();
			ps.close();
			
			returnString = "<p>Database Status</p> " + "<p>Database Date/Time return: " + myString + "</p>";
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return returnString;
	}
	


}
