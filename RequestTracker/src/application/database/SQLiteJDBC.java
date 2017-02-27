package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import application.Request;
import application.Technician;
import application.database.DbSchema.RequestTable;
import application.database.DbSchema.TechnicianTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * Table of Contents
 * 
 * This class holds all the methods for creating, reading,
 * updating, and deleting from the database and is meant to
 * be a single static instance.
 * 
 * 	1.0	Table Creation
 * 		1.1 Request Table
 * 		1.2 Technician Table
 * 	2.0 Table Insertion
 * 		2.1 Request Insert
 * 		2.2 Technician Insert
 * 	3.0	Table Updating
 * 		3.1 Request Update
 * 		3.2 Technician Update
 * 	4.0 Data Retrieval
 * 		4.1 All Requests
 * 		4.2 All Technicians
 * 		4.3 One Technician
 * 	5.0 Data Deletion
 * 		5.1 One Request
 * 		5.2 One Technician
 * 	6.0 Data Printing
 * 		6.1 All Requests
 * 		6.2 Open Requests
 * 		6.3 Closed Requests
 * 		6.4 All Technicians
 * 	7.0	Database Closing
 * 		7.1 Closes the database connection to the .db file
 * 	8.0 Private Helper Functions
 * 		8.1 Get All Technician IDs
 * 		8.2 Format Multiline String for Report
 */
public class SQLiteJDBC {
	private static SQLiteJDBC database;
	private static Connection c = null;
	private static PreparedStatement preStmt = null;
	
	// Instantiates the class to a static variable
	public static SQLiteJDBC get() throws Exception{
		if(database == null){
			database = new SQLiteJDBC();
		}
		return database;
	}
	
	// Creates the database file if not already created and creates a connection to it
	private SQLiteJDBC() throws Exception{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:request.db");
	}
	
	/************ 1.0 Table Creation ************/
	// 1.1 Request Table: Creates the request table with its columns 
	public void createRequestTable() throws Exception{
		String sql = "CREATE TABLE IF NOT EXISTS " + RequestTable.NAME +
                  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                  RequestTable.Cols.UUID + " TEXT NOT NULL, " +
                  RequestTable.Cols.REQUESTED + " TEXT NOT NULL, " + 
                  RequestTable.Cols.COMPLETED + " TEXT NOT NULL, " +
                  RequestTable.Cols.DESCRIPTION + " TEXT NOT NULL, " + 
                  RequestTable.Cols.TECH + " TEXT NOT NULL, " +
                  RequestTable.Cols.NOTES + " TEXT NOT NULL, "  +
                  RequestTable.Cols.ISCOMPLETED + " BIT NOT NULL)";
		
		preStmt = c.prepareStatement(sql);
		preStmt.executeUpdate();
	    preStmt.close();
	}
	
	// 1.2 Technician Table: Creates the technician table with its columns 
	public void createTechTable() throws Exception{
		String sql = "CREATE TABLE IF NOT EXISTS " + TechnicianTable.NAME +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TechnicianTable.Cols.UUID + " TEXT NOT NULL, " +
                TechnicianTable.Cols.FIRSTNAME + " TEXT NOT NULL, " + 
                TechnicianTable.Cols.LASTNAME + " TEXT NOT NULL, " + 
                TechnicianTable.Cols.IDNUMBER + " TEXT NOT NULL)";
		preStmt = c.prepareStatement(sql);
		preStmt.executeUpdate();
	    preStmt.close();
	}
	
	/************ 2.0 Table Insertion ************/
	// 2.1 Request Insert: Inserts a request into the request table
	public void insertRequestTable(Request r) throws Exception{
		String sql = "INSERT INTO " + RequestTable.NAME +
				"(" + RequestTable.Cols.UUID + ", " +
                RequestTable.Cols.REQUESTED + ", " + 
				RequestTable.Cols.COMPLETED + ", " +
                RequestTable.Cols.DESCRIPTION + ", " + 
                RequestTable.Cols.TECH + ", " +
                RequestTable.Cols.NOTES + ", " +
                RequestTable.Cols.ISCOMPLETED + ")" +
                "VALUES(?,?,?,?,?,?,?)";
		
		preStmt = c.prepareStatement(sql);
		preStmt.setString(1, r.getUUID().toString());
		preStmt.setString(2, r.getRequested());
		preStmt.setString(3, r.getCompleted());
		preStmt.setString(4, r.getDescription());
		preStmt.setString(5, r.getTechId());
		preStmt.setString(6, r.getNotes());
		preStmt.setBoolean(7, r.isCompleted());
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	// 2.2 Technician Insert: Inserts a technician into the technician table
	public void insertTechTable(Technician t) throws Exception{
		String sql = "INSERT INTO " + TechnicianTable.NAME +
				"(" + TechnicianTable.Cols.UUID + ", " +
				TechnicianTable.Cols.FIRSTNAME + ", " + 
				TechnicianTable.Cols.LASTNAME + ", " + 
				TechnicianTable.Cols.IDNUMBER + ") " +
                "VALUES(?,?,?,?)";
		
		preStmt = c.prepareStatement(sql);
		preStmt.setString(1, t.getUUID().toString());
		preStmt.setString(2, t.getFirstName());
		preStmt.setString(3, t.getLastName());
		preStmt.setString(4, t.getIdNumber());
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	/************ 3.0 Table Updating ************/
	// 3.1 Request Update: Updates a request in the request table when it finds a UUID match
	public void updateRequestTable(Request r) throws Exception{
		String sql = "UPDATE " + RequestTable.NAME + " SET " +
				RequestTable.Cols.REQUESTED + " = ?, " +
				RequestTable.Cols.COMPLETED + " = ?, " +
				RequestTable.Cols.DESCRIPTION + " = ?, " +
				RequestTable.Cols.TECH + " = ?, " +
				RequestTable.Cols.NOTES + " = ?, " +
				RequestTable.Cols.ISCOMPLETED + " = ? WHERE " +
				RequestTable.Cols.UUID + " = ?";
		
		preStmt = c.prepareStatement(sql);
		preStmt.setString(1, r.getRequested());
		preStmt.setString(2, r.getCompleted());
		preStmt.setString(3, r.getDescription());
		preStmt.setString(4, r.getTechId());
		preStmt.setString(5, r.getNotes());
		preStmt.setBoolean(6, r.isCompleted());
		preStmt.setString(7, r.getUUID().toString());
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	// 3.2 Technician Update: Updates a technician in the technician table when it finds a UUID match
	public void updateTechTable(Technician t) throws Exception{
		String sql = "UPDATE " + TechnicianTable.NAME + " SET " + 
				TechnicianTable.Cols.FIRSTNAME + " = ?, " + 
				TechnicianTable.Cols.LASTNAME + " = ?, " +
				TechnicianTable.Cols.IDNUMBER + " = ? WHERE " +
				TechnicianTable.Cols.UUID + " = ?";
		
		preStmt = c.prepareStatement(sql);
		preStmt.setString(1, t.getFirstName());
		preStmt.setString(2, t.getLastName());
		preStmt.setString(3, t.getIdNumber());
		preStmt.setString(4, t.getUUID().toString());
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	/************ 4.0 Data Retrieval ************/
	// 4.1 All Requests: Gets all the requests from the request table
	public ObservableList<Request> getAllRequestTable() throws Exception{
		ObservableList<Request> list = FXCollections.observableArrayList();
		String sql = "SELECT * FROM " + RequestTable.NAME;
		
		preStmt = c.prepareStatement(sql);
		
		ResultSet rs = preStmt.executeQuery();
		while(rs.next()){
			UUID uuid = UUID.fromString(rs.getString(RequestTable.Cols.UUID));
			String requested = rs.getString(RequestTable.Cols.REQUESTED);
			String completed = rs.getString(RequestTable.Cols.COMPLETED);
			String description = rs.getString(RequestTable.Cols.DESCRIPTION);
			String techUUID = rs.getString(RequestTable.Cols.TECH);
			String notes = rs.getString(RequestTable.Cols.NOTES);
			Boolean isCompleted = rs.getBoolean(RequestTable.Cols.ISCOMPLETED);
			
			Request r = new Request(uuid, requested, completed, description, UUID.fromString(techUUID), notes, isCompleted);
			list.add(r);
		}
		rs.close();
		preStmt.close();
		return list;
	}
	
	// 4.2 All Technicians: Gets all the technicians from the technician table
	public ObservableList<Technician> getAllTechTable() throws Exception{
		ObservableList<Technician> list = FXCollections.observableArrayList();
		String sql = "SELECT * FROM " + TechnicianTable.NAME;
		
		preStmt = c.prepareStatement(sql);
		ResultSet rs = preStmt.executeQuery();
		while(rs.next()){
			UUID uuid = UUID.fromString(rs.getString(TechnicianTable.Cols.UUID));
			String firstName = rs.getString(TechnicianTable.Cols.FIRSTNAME);
			String lastName = rs.getString(TechnicianTable.Cols.LASTNAME);
			String idNumber = rs.getString(TechnicianTable.Cols.IDNUMBER);
			
			Technician t = new Technician(uuid, firstName, lastName, idNumber);
			list.add(t);
		}
		rs.close();
		preStmt.close();
		
		return list;
	}
	
	// 4.3 One Technician: Gets one technician from the technician table
	public Technician getOneTechTable(UUID techUUID) throws Exception{
		String sql = "SELECT * FROM " + TechnicianTable.NAME +
				" WHERE " + TechnicianTable.Cols.UUID + " = ?";
				
		preStmt = c.prepareStatement(sql);
		preStmt.setString(1, techUUID.toString());
		
		ResultSet rs = preStmt.executeQuery();
		
		while(rs.next()){
			UUID uuid = UUID.fromString(rs.getString(TechnicianTable.Cols.UUID));
			String firstName = rs.getString(TechnicianTable.Cols.FIRSTNAME);
			String lastName = rs.getString(TechnicianTable.Cols.LASTNAME);
			String idNumber = rs.getString(TechnicianTable.Cols.IDNUMBER);
			
			Technician t = new Technician(uuid, firstName, lastName, idNumber);
			rs.close();
			preStmt.close();
			return t;
		}
		
		rs.close();
		preStmt.close();
		
		return null;
	}
	
	/************ 5.0 Data Deletion ************/
	// 5.1 One Request: Deletes one request from the request table
	public void deleteOneRequestTable(UUID requestUUID) throws Exception{
		String sql = "DELETE FROM " + RequestTable.NAME +
				" WHERE " + RequestTable.Cols.UUID + " = ?";
		
		preStmt = c.prepareStatement(sql);
		preStmt.setString(1, requestUUID.toString());
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	// 5.2 One Technician: Deletes one technician from the technician table
	public void deleteOneTechTable(UUID techUUID) throws Exception{
		String sql = "DELETE FROM " + TechnicianTable.NAME +
				" WHERE " + TechnicianTable.Cols.UUID + " = ?";
		
		preStmt = c.prepareStatement(sql);
		preStmt.setString(1, techUUID.toString());
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	/************ 6.0 Data Printing ************/
	// 6.1 All Requests: Gets all requests by technician and outputs a string
	public String printAllRequests() throws Exception{
		StringBuilder report = new StringBuilder();
		List<String> techIDs = getAllTechIDs();
		
		report.append("############ All Requests ############");
		for(String id:techIDs){
			String sql = "SELECT " + RequestTable.Cols.REQUESTED +", " + 
				RequestTable.Cols.COMPLETED + ", " +
				RequestTable.Cols.DESCRIPTION + ", " + 
				RequestTable.Cols.NOTES + ", " +
				TechnicianTable.Cols.FIRSTNAME + ", " + 
				TechnicianTable.Cols.LASTNAME + ", " + 
				TechnicianTable.Cols.IDNUMBER + " FROM " + 
				RequestTable.NAME + ", " + TechnicianTable.NAME + " WHERE " +
				TechnicianTable.NAME + "." + TechnicianTable.Cols.UUID + " = ? AND " +
				RequestTable.Cols.TECH + " = " + TechnicianTable.NAME + "." + TechnicianTable.Cols.UUID +
				" ORDER BY " + TechnicianTable.Cols.IDNUMBER;
			
			preStmt = c.prepareStatement(sql);
			preStmt.setString(1, id);
			ResultSet rs = preStmt.executeQuery();
			
			int count = 0;
			while(rs.next()){
				String requested = rs.getString(RequestTable.Cols.REQUESTED);
				String completed = rs.getString(RequestTable.Cols.COMPLETED).equals("") ? "n/a" : rs.getString(RequestTable.Cols.COMPLETED);
				String description = rs.getString(RequestTable.Cols.DESCRIPTION);
				String notes = rs.getString(RequestTable.Cols.NOTES);
				String techName = rs.getString(TechnicianTable.Cols.FIRSTNAME) + " " + rs.getString(TechnicianTable.Cols.LASTNAME);
				String techId = rs.getString(TechnicianTable.Cols.IDNUMBER);
				
				if(count == 0){
					report.append("\n/************ " + techName + " " + techId + " ************/\n");
					count++;
				}
				report.append("requested:\t" + requested + "\n");
				report.append("completed:\t" + completed + "\n");
				report.append("description:\n");
				report.append(formatMultilineForReport(description));
				report.append("notes:\n");
				report.append(formatMultilineForReport(notes));				
				report.append("/------------------------------/\n");
			}
			rs.close();
			preStmt.close();
		}				
		return report.toString();
	}
		
	// 6.2 Open Requests: Gets non-completed requests by technician and outputs a string
	public String printOpenRequests() throws Exception{
		StringBuilder report = new StringBuilder();
		List<String> techIDs = getAllTechIDs();
		
		report.append("############ Open Requests ############");
		for(String id:techIDs){
			String sql = "SELECT " + RequestTable.Cols.REQUESTED +", " + 
				RequestTable.Cols.COMPLETED + ", " +
				RequestTable.Cols.DESCRIPTION + ", " + 
				RequestTable.Cols.NOTES + ", " + 
				TechnicianTable.Cols.FIRSTNAME + ", " + 
				TechnicianTable.Cols.LASTNAME + ", " + 
				TechnicianTable.Cols.IDNUMBER + " FROM " + 
				RequestTable.NAME + ", " + TechnicianTable.NAME + " WHERE " +
				TechnicianTable.NAME + "." + TechnicianTable.Cols.UUID + " = ? AND " +
				RequestTable.Cols.TECH + " = " + TechnicianTable.NAME + "." + TechnicianTable.Cols.UUID + " AND " +
				RequestTable.Cols.ISCOMPLETED + " = ? ORDER BY " + TechnicianTable.Cols.IDNUMBER;
		
			preStmt = c.prepareStatement(sql);
			preStmt.setString(1, id);
			preStmt.setBoolean(2, false);
			ResultSet rs = preStmt.executeQuery();
		
			int count = 0;
			while(rs.next()){
				String requested = rs.getString(RequestTable.Cols.REQUESTED);
				String completed = rs.getString(RequestTable.Cols.COMPLETED).equals("") ? "n/a" : rs.getString(RequestTable.Cols.COMPLETED);
				String description = rs.getString(RequestTable.Cols.DESCRIPTION);
				String notes = rs.getString(RequestTable.Cols.NOTES);
				String techName = rs.getString(TechnicianTable.Cols.FIRSTNAME) + " " + rs.getString(TechnicianTable.Cols.LASTNAME);
				String techId = rs.getString(TechnicianTable.Cols.IDNUMBER);
				
				if(count == 0){
					report.append("\n/************ " + techName + " " + techId + " ************/\n");
					count++;
				}
				report.append("requested:\t" + requested + "\n");
				report.append("completed:\t" + completed + "\n");
				report.append("description:\n");
				report.append(formatMultilineForReport(description));
				report.append("notes:\n");
				report.append(formatMultilineForReport(notes));				
				report.append("/------------------------------/\n");
			}
			rs.close();
			preStmt.close();
		}
		return report.toString();
	}
	
	// 6.3 Closed Requests: Gets all completed requests and outputs a string
	public String printClosedRequests() throws Exception{
		StringBuilder report = new StringBuilder();
		List<String> techIDs = getAllTechIDs();
		
		report.append("############ Completed Requests ############");
		for(String id:techIDs){
			String sql = "SELECT " + RequestTable.Cols.REQUESTED +", " + 
					RequestTable.Cols.COMPLETED + ", " +
					RequestTable.Cols.DESCRIPTION + ", " + 
					RequestTable.Cols.NOTES + ", " + 
					TechnicianTable.Cols.FIRSTNAME + ", " + 
					TechnicianTable.Cols.LASTNAME + ", " + 
					TechnicianTable.Cols.IDNUMBER + " FROM " + 
					RequestTable.NAME + ", " + TechnicianTable.NAME + " WHERE " +
					TechnicianTable.NAME + "." + TechnicianTable.Cols.UUID + " = ? AND " +
					RequestTable.Cols.TECH + " = " + TechnicianTable.NAME + "." + TechnicianTable.Cols.UUID + " AND " +
					RequestTable.Cols.ISCOMPLETED + " = ? ORDER BY " + TechnicianTable.Cols.IDNUMBER;
			
			preStmt = c.prepareStatement(sql);
			preStmt.setString(1, id);
			preStmt.setBoolean(2, true);
			ResultSet rs = preStmt.executeQuery();
				
			int count = 0;
			while(rs.next()){
				String requested = rs.getString(RequestTable.Cols.REQUESTED);
				String completed = rs.getString(RequestTable.Cols.COMPLETED).equals("") ? "n/a" : rs.getString(RequestTable.Cols.COMPLETED);
				String description = rs.getString(RequestTable.Cols.DESCRIPTION);
				String notes = rs.getString(RequestTable.Cols.NOTES);
				String techName = rs.getString(TechnicianTable.Cols.FIRSTNAME) + " " + rs.getString(TechnicianTable.Cols.LASTNAME);
				String techId = rs.getString(TechnicianTable.Cols.IDNUMBER);
				
				if(count == 0){
					report.append("\n/************ " + techName + " " + techId + " ************/\n");
					count++;
				}
				report.append("requested:\t" + requested + "\n");
				report.append("completed:\t" + completed + "\n");
				report.append("description:\n");
				report.append(formatMultilineForReport(description));
				report.append("notes:\n");
				report.append(formatMultilineForReport(notes));				
				report.append("/------------------------------/\n");
			}
			rs.close();
			preStmt.close();	
		}		
		return report.toString();
	}
	
	// 6.4 All Technicians: Gets all technicians and outputs a string
	public String printAllTechs() throws Exception{
		StringBuilder report = new StringBuilder();		
		report.append("############ All Technicians ############");
		
		String sql = "SELECT " + TechnicianTable.Cols.FIRSTNAME + ", " +
				TechnicianTable.Cols.LASTNAME + ", " +
				TechnicianTable.Cols.IDNUMBER + " FROM " +
				TechnicianTable.NAME;
		
		preStmt = c.prepareStatement(sql);
		
		ResultSet rs = preStmt.executeQuery();
		while(rs.next()){
			String fName = rs.getString(TechnicianTable.Cols.FIRSTNAME);
			String lName = rs.getString(TechnicianTable.Cols.LASTNAME);
			String idNum = rs.getString(TechnicianTable.Cols.IDNUMBER);
			
			report.append("\n"+ idNum + "\t" + fName + "\t" + lName);
		}
		
		rs.close();
		preStmt.close();
		
		return report.toString();
	}
	
	/************ 7.0 Database Closing ************/
	// 7.1 Closes the database connection to the .db file
	public void closeDbConnection() throws Exception{
		c.close();
	} 
	
	/************ 8.0 Private Helper Functions ************/
	// 8.1 Get All Technician IDs: Gets all the technician UUIDs for use in other functions
	private List<String> getAllTechIDs() throws Exception{
		List<String> techIDs= new ArrayList<>();
		String sql = "SELECT " + TechnicianTable.Cols.UUID + " FROM " +
				TechnicianTable.NAME;
		
		preStmt = c.prepareStatement(sql);
		ResultSet rs = preStmt.executeQuery();
		while(rs.next()){
			String idNum = rs.getString(TechnicianTable.Cols.UUID);
			techIDs.add(idNum);			
		}
		
		rs.close();
		preStmt.close();
		
		return techIDs;
	}
	
	// 8.2 Format Multiline String for Report: Takes in a string and formats it for use in other functions
	private String formatMultilineForReport(String multiline){
		StringBuilder singleLine = new StringBuilder();
		StringBuilder finalMultiline = new StringBuilder();
		
		for(int a = 0; a < multiline.length(); a++){
			if(multiline.charAt(a) == '\n'){
				finalMultiline.append("\t" + singleLine.toString() + "\n");
				singleLine = new StringBuilder();
			}
			else if(a == multiline.length() - 1){
				finalMultiline.append("\t" + singleLine.append(multiline.charAt(a)).toString() + "\n");
			}
			else{
				singleLine.append(multiline.charAt(a));
			}
		}
		
		return finalMultiline.toString();
	}
}