package org.paul.inventory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.paul.dao.SchemaDAO;

@Path("v3/inventory")
public class V3_Inventory {

	/**
	 * This method will return the specific brand of PC parts the user is
	 * looking for. It uses a QueryParam bring in the data to the method.
	 * 
	 * Example would be:
	 * http://localhost:8080/JavaRestTutorialWithDerby/api/v3/inventory?brand=HP
	 * 
	 * @param brand
	 *            - product brand name
	 * @return - json array results list from the database
	 * @throws Exception
	 */
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

			// Put the Json into a string
			returnString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}

		return Response.ok(returnString).build();

	}

	/*
	 * This method can be used if the method returnBrandParts is not used.
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response
	 * returnErrorOnBrand() throws Exception { return
	 * Response.status(400).entity
	 * ("Error: please specify brand for this search").build(); }
	 */

	/**
	 * This method will return the specific brand of PC parts the user is
	 * looking for. It is very similar to the method returnBrandParts except
	 * this method uses the PathParam to bring in the data.
	 * 
	 * Example would be:
	 * http://localhost:8080/JavaRestTutorialWithDerby/api/v3/inventory/HP
	 * 
	 * @param brand
	 *            - product brand name
	 * @return - json array results list from the database
	 * @throws Exception
	 */
	@Path("/{brand}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(@PathParam("brand") String brand) throws Exception {

		String returnString = null;
		JSONArray json = new JSONArray();

		try {

			SchemaDAO dao = new SchemaDAO();
			json = dao.queryReturnBrandParts(brand);

			// Put the Json into a string
			returnString = json.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}

		return Response.ok(returnString).build();

	}

	/**
	 * This method does a search on both product and the product item number. It
	 * uses PathParam to bring in both parameters.
	 * 
	 * Example:
	 * http://localhost:8080/JavaRestTutorialWithDerby/api/v3/inventory/HP/123459
	 * 
	 * @param brand - product brand name
	 * @param item_number - product item number
	 * @return - json array results list from the database
	 * @throws Exception
	 */
	@Path("/{brand}/{item_number}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnSpecificBrandItem(@PathParam("brand") String brand, @PathParam("item_number") int item_number) throws Exception {
		String returnString = null;
		JSONArray json = new JSONArray();
		try {
			SchemaDAO dao = new SchemaDAO();
			json = dao.queryReturnBrandItemNumber(brand, item_number);
			returnString = json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}

}