package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {

	// A common method to connect to the DB
	private Connection connect() 
	{
		Connection con = null;
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/buyer2", "root", "lakmi");

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return con;
	}

	// Insert Project Details
	public String insertConnection(String connectionID, String connectionName, String connectionType,
			String description, String price, String date) 
	{
		String output = "";
		
		try 
		{
			Connection con = connect();
			
			if (con == null) 
			{
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = "INSERT INTO `connection`(`connectionID`, `connectionName`, `connectionType`, `description`, `price`, `date`) VALUES (?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, connectionName);
			preparedStmt.setString(3, connectionType);
			preparedStmt.setString(4, description);
			preparedStmt.setDouble(5, Float.parseFloat(price));
			preparedStmt.setString(6, date);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newConnection = readConnection();
			output = "{\"status\":\"success\", \"data\": \"" + newConnection + "\"}";

		} 
		catch (Exception e) 
		{

			output = "{\"status\":\"error\", \"data\":\"Error while inserting the connection to system.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	public String readConnection() 
	{
		String output = "";
		
		try 
		{
			
			Connection con = connect();
			
			if (con == null) 
			{
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1' ><tr ><th >Connection Name</th><th >Connection Type</th>"
					+ "<th>Description</th><th>Price</th><th>Date</th><th>Update</th>"
					+ "<th>Remove</th></tr>";

			String query = "select * from `connection`";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) 
			{
				String connectionId = Integer.toString(rs.getInt("connectionId"));
				String connectionName = rs.getString("connectionName");
				String connectionType = rs.getString("connectionType");
				String description = rs.getString("description");
				String price = Float.toString(rs.getFloat("price"));
				String date = rs.getString("date");

				// Add into the html table

				// output += "<tr><td>" + connectionId + "</td>";
				output += "<td>" + connectionName + "</td>";
				output += "<td>" + connectionType + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + price + "</td>";
				output += "<td>" + date + "</td>";

				// buttons

				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-userid='" + connectionId + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-userid='" + connectionId + "'></td></tr>";
			}
			con.close();

			// Complete the html table
			output += "</table>";

		} catch (Exception e) 
		{
			output = "Error while reading the connections.";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public String updateConnection(String connectionId, String connectionName, String connectionType,
			String description, String price, String date) 
	{
		String output = "";
		
		try 
		{
			Connection con = connect();
			
			if (con == null) 
			{
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE `connection` SET `connectionName`=?,`connectionType`=?,`description`=?,`price`=?,`date`=? WHERE `connectionId`=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, connectionName);
			preparedStmt.setString(2, connectionType);
			preparedStmt.setString(3, description);
			preparedStmt.setDouble(4, Float.parseFloat(price));
			preparedStmt.setString(5, date);
			preparedStmt.setString(6, connectionId);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newConnection = readConnection();
			output = "{\"status\":\"success\", \"data\": \"" + newConnection + "\"}";

		} 
		catch (Exception e) 
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the connection.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	public String deleteConnection(String connectionId) 
	{
		String output = "";
		
		try 
		{
			Connection con = connect();
			
			if (con == null) 
			{
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "DELETE FROM `connection` WHERE connectionId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(connectionId));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newConnection = readConnection();
			output = "{\"status\":\"success\", \"data\": \"" + newConnection + "\"}";

		} 
		catch (Exception e) 
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the connection.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}