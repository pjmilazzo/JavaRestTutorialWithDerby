package org.paul.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONArray;
import org.paul.dao.DerbyDAO;
import org.paul.util.ToJson;

@Path("v2/inventory")
public class Inventory {
	
	static final private String query = "select * from PC_PARTS";

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPcParts() throws Exception {

		PreparedStatement ps = null;
		Connection conn = null;
		String returnString = null;
		Response rb = null;

		try {
			conn = DerbyDAO.derbyConn().getConnection();
			ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();

			// Convert resultSet to JsonArray using our homebrew converter
			JSONArray json = ToJson.toJSONArray(rs);
			
			// close the result set and  preparted statement
			rs.close();
			ps.close();
			
			//Put the Json into a string
			returnString = json.toString();
			
			// Convert returnString to a response
			rb = Response.ok(returnString).build();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return rb;

	}

}
