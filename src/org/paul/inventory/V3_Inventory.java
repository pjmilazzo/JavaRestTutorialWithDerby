package org.paul.inventory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.paul.dao.SchemaDAO;

@Path("v3/inventory")
public class V3_Inventory {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrandParts(@QueryParam("brand") String brand) throws Exception {

		String returnString = null;
		JSONArray json = new JSONArray();

		try {
			if (brand == null) {
				// Bad Request
				return Response.status(400).entity("Error: please specify brand for this serach").build();
			}
			
			SchemaDAO dao = new SchemaDAO();
			json = dao.queryReturnBrandParts(brand);
			
			//Put the Json into a string
			returnString = json.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}

		return Response.ok(returnString).build();

	}

}