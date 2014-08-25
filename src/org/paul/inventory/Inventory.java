package org.paul.inventory;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONArray;
import org.paul.util.ToJson;

@Path("v2/inventory")
public class Inventory {

	private static List<Part> parts = new ArrayList<Part>();

	public Inventory() {
		// Add parts to if empty as if comming from a db
		if (parts.isEmpty()) {
			Part part = new Part("1", "Intel MB", 123456789, "Intel", 5, "Intel motherboard");
			parts.add(part);
			part = new Part("2", "AMD MB", 987654321, "AMD", 3, "AMD motherboard");
			parts.add(part);
			part = new Part("3", "HP Laptop", 1236, "HP", 5, "Hp Laptop with 15 inch screen");
			parts.add(part);
			part = new Part("4", "IBM Desktop", 1237, "IBM", 10, "IBM Desktop");
			parts.add(part);
			part = new Part("5", "Dell Desktop", 1238, "Dell", 14, "Dell Desktop");
			parts.add(part);
			part = new Part("3", "HP Desktop", 1239, "HP", 3, "Hp Desktop");
			parts.add(part);

		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPcParts() {

		// Normally we would hit a data base here and get a resultset back
		// toJsonArrary would be altered to convert a rs to json directly

		String returnString = null;
		Response rb = null;

		try {
			JSONArray json = ToJson.toJSONArray(parts);
			returnString = json.toString();
			rb = Response.ok(returnString).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rb;

	}

}
