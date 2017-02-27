package application;

import application.database.SQLiteJDBC;
import javafx.collections.ObservableList;

public class RequestList {	
	private static RequestList requestList;
	private SQLiteJDBC database;
	
	public static RequestList get() throws Exception{
		if(requestList == null){
			requestList = new RequestList();
		}
		return requestList;
	}
	
	/* Constructor */
	private RequestList() throws Exception{
		database = SQLiteJDBC.get();
		database.createRequestTable();
	}

	/* Add, edit, delete */
	public void addRequest(Request r) throws Exception{
		database.insertRequestTable(r);
	}
		
	public void editRequest(Request r) throws Exception{
		database.updateRequestTable(r);
	}
	
	public void deleteRequest(Request r) throws Exception{
		database.deleteOneRequestTable(r.getUUID());
	}
	
	/* Getters */
	public ObservableList<Request> getAll() throws Exception{
		return database.getAllRequestTable();
	}

	public String printAllRequests() throws Exception{
		return database.printAllRequests();
	}
	
	public String printOpenRequests() throws Exception{
		return database.printOpenRequests();
	}
	
	public String printClosedRequests() throws Exception{
		return database.printClosedRequests();
	}
}
