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

/**
 * The Class SQLiteJDBC.
 * Holds all the methods for creating, reading,
 * updating, and deleting from the database and is meant to
 * be a single static instance.
 * 
 * @author Chaz-Rae L. Moncrieffe
 * @since 3/1/2017
 */
/*
 * Table of Contents
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
 * 		5.3 All Requests
 * 		5.4 All Technicians
 * 	6.0 Data Printing
 * 		6.1 All Requests
 * 		6.2 Open Requests
 * 		6.3 Closed Requests
 * 		6.4 All Technicians
 * 	7.0	Database Closing
 * 		7.1 Close database Connection
 * 	8.0 Private Helper Functions
 * 		8.1 All Technician IDs
 * 		8.2 Format Multiline String
 */
public class SQLiteJDBC {
	private static SQLiteJDBC database;
	private static Connection c = null;
	private static PreparedStatement preStmt = null;
	
	/**
	 * Gets the instance of the SQLiteJDBC class.
	 *
	 * @return the SQLiteJDBC
	 * @throws Exception the Exception
	 */
	public static SQLiteJDBC get() throws Exception{
		if(database == null){
			database = new SQLiteJDBC();
		}
		return database;
	}
	
	/**
	 * Instantiates a new SQLiteJDBC.
	 *
	 * @throws Exception the Exception
	 */
	private SQLiteJDBC() throws Exception{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:request.db");
	}
	
	/************ 1.0 Table Creation ************/
	/**
	 * 1.1 Request Table
	 * Creates the request table.
	 *
	 * @throws Exception the Exception
	 */
	public void createRequestTable() throws Exception{
		String sql = "CREATE TABLE IF NOT EXISTS " + RequestTable.NAME +
                  "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                  RequestTable.Cols.UUID + " TEXT NOT NULL, " +
                  RequestTable.Cols.ID + " INTEGER NOT NULL, " +
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
	
	/**
	 * 1.2 Technician Table
	 * Creates the technician table.
	 *
	 * @throws Exception the Exception
	 */
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
	/**
	 * 2.1 Request Insert
	 * Insert into request table.
	 *
	 * @param r the request
	 * @throws Exception the Exception
	 */
	public void insertRequestTable(Request r) throws Exception{
		String sql = "INSERT INTO " + RequestTable.NAME +
				"(" + RequestTable.Cols.UUID + ", " +
                RequestTable.Cols.REQUESTED + ", " + 
				RequestTable.Cols.COMPLETED + ", " +
                RequestTable.Cols.DESCRIPTION + ", " + 
                RequestTable.Cols.TECH + ", " +
                RequestTable.Cols.NOTES + ", " +
                RequestTable.Cols.ISCOMPLETED + "," +
                RequestTable.Cols.ID + ")" +
                "VALUES(?,?,?,?,?,?,?,?)";
		
		preStmt = c.prepareStatement(sql);
		preStmt.setString(1, r.getUUID().toString());
		preStmt.setString(2, r.getRequested());
		preStmt.setString(3, r.getCompleted());
		preStmt.setString(4, r.getDescription());
		preStmt.setString(5, r.getTechId());
		preStmt.setString(6, r.getNotes());
		preStmt.setBoolean(7, r.isCompleted());
		preStmt.setInt(8, r.getId());
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	/**
	 * 2.2 Technician Insert
	 * Insert into tech table.
	 *
	 * @param t the technician
	 * @throws Exception the Exception
	 */
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
	/**
	 * 3.1 Request Update
	 * Update request table.
	 *
	 * @param r the request
	 * @throws Exception the Exception
	 */
	public void updateRequestTable(Request r) throws Exception{
		String sql = "UPDATE " + RequestTable.NAME + " SET " +
				RequestTable.Cols.REQUESTED + " = ?, " +
				RequestTable.Cols.COMPLETED + " = ?, " +
				RequestTable.Cols.DESCRIPTION + " = ?, " +
				RequestTable.Cols.TECH + " = ?, " +
				RequestTable.Cols.NOTES + " = ?, " +
				RequestTable.Cols.ISCOMPLETED + " = ?, " +
				RequestTable.Cols.ID + " = ? WHERE " +
				RequestTable.Cols.UUID + " = ?";
		
		preStmt = c.prepareStatement(sql);
		preStmt.setString(1, r.getRequested());
		preStmt.setString(2, r.getCompleted());
		preStmt.setString(3, r.getDescription());
		preStmt.setString(4, r.getTechId());
		preStmt.setString(5, r.getNotes());
		preStmt.setBoolean(6, r.isCompleted());
		preStmt.setInt(7, r.getId());
		preStmt.setString(8, r.getUUID().toString());
		
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	/**
	 * 3.2 Technician Update
	 * Update tech table.
	 *
	 * @param t the technician
	 * @throws Exception the Exception
	 */
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
	/**
	 * 4.1 All Requests
	 * Gets all requests from table.
	 *
	 * @return the request list
	 * @throws Exception the Exception
	 */
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
			boolean isCompleted = rs.getBoolean(RequestTable.Cols.ISCOMPLETED);
			int id = rs.getInt(RequestTable.Cols.ID); 
			
			Request r = new Request(uuid, id, requested, completed, description, UUID.fromString(techUUID), notes, isCompleted);
			list.add(r);
		}
		rs.close();
		preStmt.close();
		return list;
	}
	
	/**
	 * 4.2 All Technicians
	 * Gets all techs from table.
	 *
	 * @return the tech list
	 * @throws Exception the Exception
	 */
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
	
	/**
	 * 4.3 One Technician
	 * Gets one tech from table.
	 *
	 * @param techUUID the tech UUID
	 * @return one tech from table
	 * @throws Exception the Exception
	 */
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
	/**
	 * 5.1 One Request
	 * Deletes one request from table.
	 *
	 * @param requestUUID the request UUID
	 * @throws Exception the Exception
	 */
	public void deleteOneRequestTable(UUID requestUUID) throws Exception{
		String sql = "DELETE FROM " + RequestTable.NAME +
				" WHERE " + RequestTable.Cols.UUID + " = ?";
		
		preStmt = c.prepareStatement(sql);
		preStmt.setString(1, requestUUID.toString());
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	/**
	 * 5.2 One Technician
	 * Deletes one technician from table.
	 *
	 * @param techUUID the tech UUID
	 * @throws Exception the Exception
	 */
	public void deleteOneTechTable(UUID techUUID) throws Exception{
		String sql = "DELETE FROM " + TechnicianTable.NAME +
				" WHERE " + TechnicianTable.Cols.UUID + " = ?";
		
		preStmt = c.prepareStatement(sql);
		preStmt.setString(1, techUUID.toString());
		preStmt.executeUpdate();
		preStmt.close();
	}
	
	/**
	 * 5.3 All Requests
	 * Deletes all requests from table.
	 *
	 * @throws Exception the Exception
	 */
	public void deleteAllRequestTable() throws Exception{
	    String sql = "DELETE FROM " + RequestTable.NAME;
		preStmt = c.prepareStatement(sql);
		preStmt.executeUpdate();
	}
	
	/**
	 * 5.4 All Technicians
	 * Deletes all technicians from table.
	 *
	 * @throws Exception the exception
	 */
	public void deleteAllTechTable() throws Exception{
	    String sql = "DELETE FROM " + TechnicianTable.NAME;
		preStmt = c.prepareStatement(sql);
		preStmt.executeUpdate();
	}
	
	/************ 6.0 Data Printing ************/
	/**
	 * 6.1 All Requests
	 * Prints all requests by technician.
	 *
	 * @return the string
	 * @throws Exception
	 */
	public String printAllRequests() throws Exception{
		StringBuilder report = new StringBuilder();
		List<String> techIDs = getAllTechIDs();
		
		report.append("############ All Requests ############");
		for(String id:techIDs){
			String sql = "SELECT " + 
				RequestTable.Cols.ID + ", " + 
				RequestTable.Cols.REQUESTED +", " + 
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
				String reqId = Integer.toString(rs.getInt(RequestTable.Cols.ID));
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
				report.append("request id:\t" + reqId + "\n");
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
		
	/**
	 * 6.2 Open Requests
	 * Prints the open requests by technician.
	 *
	 * @return the string
	 * @throws Exception
	 */
	public String printOpenRequests() throws Exception{
		StringBuilder report = new StringBuilder();
		List<String> techIDs = getAllTechIDs();
		
		report.append("############ Open Requests ############");
		for(String id:techIDs){
			String sql = "SELECT " +
				RequestTable.Cols.ID + ", " + 
				RequestTable.Cols.REQUESTED +", " + 
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
				String reqId = Integer.toString(rs.getInt(RequestTable.Cols.ID));
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
				report.append("request id:\t" + reqId + "\n");
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
	
	/**
	 * 6.3 Closed Requests
	 * Prints the closed requests by technician.
	 *
	 * @return the string
	 * @throws Exception
	 */
	public String printClosedRequests() throws Exception{
		StringBuilder report = new StringBuilder();
		List<String> techIDs = getAllTechIDs();
		
		report.append("############ Completed Requests ############");
		for(String id:techIDs){
			String sql = "SELECT " + 
					RequestTable.Cols.ID + ", " + 
					RequestTable.Cols.REQUESTED +", " + 
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
				String reqId = Integer.toString(rs.getInt(RequestTable.Cols.ID));
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
				report.append("request id:\t" + reqId + "\n");
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
	
	/**
	 * 6.4 All Technicians
	 * Prints all techs.
	 *
	 * @return the string
	 * @throws Exception
	 */
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
	/**
	 * 7.1 Close database connection.
	 *
	 * @throws Exception
	 */
	public void closeDbConnection() throws Exception{
		c.close();
	} 
	
	/************ 8.0 Private Helper Functions ************/
	/**
	 * 8.1 All Technician IDs
	 * Gets all the tech IDs.
	 *
	 * @return list of tech IDs
	 * @throws Exception
	 */
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
	
	/**
	 * 8.2 Format Multiline String
	 * Formats a multiline string for the 
	 * data printing functions.
	 *
	 * @param multiline the multiline string
	 * @return the string
	 */
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