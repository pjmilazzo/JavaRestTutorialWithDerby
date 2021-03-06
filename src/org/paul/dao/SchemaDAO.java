package org.paul.dao;

import java.sql.*;
import org.codehaus.jettison.json.JSONArray;
import org.paul.util.ToJson;

/**
 * This java class will hold all the sql queries from episode 5 and onward.
 * V1_inventory.java and V1_status.java will not use this class for its sql code
 * since they were created before episode 5.
 * 
 * Having all sql/database code in one package makes it easier to maintain and
 * audit but increase complexity.
 * 
 * Note: we also used the extends Oracle308tube on this java class to inherit
 * all the methods in Oracle308tube.java
 * 
 */

public class SchemaDAO extends DerbyDAO {
	/**
	 * This method allows you to delete a row from PC_PARTS table
	 * 
	 * If you need to do a delete, consider moving the data to a archive table,
	 * then delete. Or just make the data invisible to the user. Delete data can
	 * be very dangerous.
	 * 
	 * @param pk
	 * @return
	 * @throws Exception
	 */

	public int deletePC_PARTS(int pk) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			/*
			 * If this was a real application, you should do data validation
			 * here before deleting data.
			 */
			conn = derbyPcPartsConnection();
			query = conn.prepareStatement("delete from PC_PARTS " + "where PC_PARTS_PK = ? ");
			query.setInt(1, pk);
			query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return 500;
		} finally {
			if (conn != null)
				conn.close();
		}
		return 200;
	}

	/**
	 * This method allows you to update PC_PARTS table
	 * 
	 * Note: there is no validation being done... if this was a real project you
	 * must do validation here!
	 * 
	 * @param pk
	 * @param avail
	 * @return
	 * @throws Exception
	 */
	public int updatePC_PARTS(int pk, int avail) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		try {
			/*
			 * If this was a real application, you should do data validation
			 * here before updating data.
			 */
			conn = derbyPcPartsConnection();
			query = conn.prepareStatement("update PC_PARTS " + "set PC_PARTS_AVAIL = ? " + "where PC_PARTS_PK = ? ");
			query.setInt(1, avail);
			query.setInt(2, pk);
			query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return 500;
		} finally {
			if (conn != null)
				conn.close();
		}
		return 200;
	}

	/**
	 * This method will insert a record into the PC_PARTS table.
	 * 
	 * Note: there is no validation being done... if this was a real project you
	 * must do validation here!
	 * 
	 * @param pcPartsTitle
	 * @param pcPartsCode  - integer column
	 * @param pcPartsMaker
	 * @param pcPartsAvail - integer column
	 * @param pcPartsDesc
	 * @return integer 200 for success, 500 for error
	 * @throws Exception
	 */
	public int insertIntoPC_PARTS(String pcPartsTitle, String pcPartsCode, String pcPartsMaker, String pcPartsAvail, String pcPartsDesc) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			/*
			 * If this was a real application, you should do data validation
			 * here before starting to insert data into the database.
			 * 
			 * Important: The primary key on PC_PARTS table will auto increment.
			 * That means the PC_PARTS_PK column does not need to be apart of
			 * the SQL insert query below.
			 */
			conn = derbyPcPartsConnection();
			query = conn.prepareStatement("insert into PC_PARTS "
					+ "(PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC) " + "VALUES ( ?, ?, ?, ?, ? ) ");
			query.setString(1, pcPartsTitle);
			
			// PC_PARTS_CODE is a number column, so we need to convert the String into a integer
			int codeInt = Integer.parseInt(pcPartsCode);
			query.setInt(2, codeInt);
			
			query.setString(3, pcPartsMaker);
			
			// PC_PARTS_AVAIL is a number column, so we need to convert the String into a integer
			int availInt = Integer.parseInt(pcPartsAvail);
			query.setInt(4, availInt);
			
			query.setString(5, pcPartsDesc);
			query.executeUpdate(); // note the new command for insert statement
			
		} catch (Exception e) {
			e.printStackTrace();
			return 500; // if a error occurs, return a 500
		} finally {
			if (conn != null)
				conn.close();
		}
		return 200;
	}

	/**
	 * This method will search for a specific brand from the PC_PARTS table. By
	 * using prepareStatement and the ?, we are protecting against sql injection
	 * 
	 * Never add parameter straight into the prepareStatement
	 * 
	 * @param brand
	 *            - product brand
	 * @return - json array of the results from the database
	 * @throws Exception
	 */
	public JSONArray queryReturnBrandParts(String brand) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		JSONArray json = new JSONArray();

		try {
			conn = derbyPcPartsConnection();
			query = conn.prepareStatement("select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC "
							+ "from PC_PARTS " + "where UPPER(PC_PARTS_MAKER) = ? ");
			
			query.setString(1, brand.toUpperCase()); // protect against sql injection
			ResultSet rs = query.executeQuery();
			
			json = ToJson.toJSONArray(rs);
			
			rs.close(); // Close result set
			query.close(); // close connection
			
		} catch (SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		} finally {
			if (conn != null)
				conn.close();
		}
		return json;
	}

	/**
	 * This method will search for the specific brand and the brands item number
	 * in the PC_PARTS table.
	 * 
	 * By using prepareStatement and the ?, we are protecting against sql
	 * injection
	 * 
	 * Never add parameter straight into the prepareStatement
	 * 
	 * @param brand - product brand
	 * @param item_number - product item number
	 * @return - json array of the results from the database
	 * @throws Exception
	 */
	public JSONArray queryReturnBrandItemNumber(String brand, int item_number) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		JSONArray json = new JSONArray();

		try {
			conn = derbyPcPartsConnection();
			query = conn.prepareStatement("select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC "
							+ "from PC_PARTS " + "where UPPER(PC_PARTS_MAKER) = ? " + "and PC_PARTS_CODE = ?");
			/*
			 * protect against sql injection when you have more than one ?, it
			 * will go in chronological order.
			 */
			query.setString(1, brand.toUpperCase()); // first ?
			query.setInt(2, item_number); // second ?
			ResultSet rs = query.executeQuery();
			json = ToJson.toJSONArray(rs);
			rs.close(); // Close result set
			query.close(); // close connection
			
		} catch (SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		} finally {
			if (conn != null)
				conn.close();
		}
		return json;
	}

	/**
	 * This method will return all PC parts. Done pre-episode 6
	 * 
	 * @return - all PC parts in json format
	 * @throws Exception
	 */
	public JSONArray queryAllPcParts() throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		JSONArray json = new JSONArray();
		
		try {
			conn = derbyPcPartsConnection();
			query = conn.prepareStatement("select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC "
							+ "from PC_PARTS");
			ResultSet rs = query.executeQuery();
			json = ToJson.toJSONArray(rs);
			rs.close(); // Close result set
			query.close(); // close connection
			
		} catch (SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		} finally {
			if (conn != null)
				conn.close();
		}
		return json;
	}

	/**
	 * This method will return a time/stamp from database. Done pre-episode 6
	 * 
	 * @return time/stamp in json format
	 * @throws Exception
	 */
	public JSONArray queryCheckDbConnection() throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		JSONArray json = new JSONArray();
		
		try {
			conn = derbyPcPartsConnection();
			query = conn.prepareStatement("SELECT CURRENT_TIMESTAMP FROM SYSIBM.SYSDUMMY1");
			ResultSet rs = query.executeQuery();
			json = ToJson.toJSONArray(rs);
			rs.close(); // Close result set
			query.close(); // close connection
			
		} catch (SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
		} finally {
			if (conn != null)
				conn.close();
		}
		return json;
	}
}
