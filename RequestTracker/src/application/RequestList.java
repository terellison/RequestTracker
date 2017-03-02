package application;

import java.util.ArrayList;
import java.util.List;

import application.database.SQLiteJDBC;
import javafx.collections.ObservableList;

/**
 * The Class RequestList.
 * 
 * Keeps track of the Request objects and
 * their request numbers. Is the intermediary
 * between Main.java and SQLiteJDBC.java.
 * 
 * @author Chaz-Rae L. Moncrieffe
 * @since 3/1/2017
 */
public class RequestList {	
	private static RequestList requestList;
	private static List<Integer> requestIds;
	private static int count;
	private SQLiteJDBC database;
	
	/**
	 * Gets the instance of the RequestList class.
	 *
	 * @return the RequestList
	 * @throws Exception the Exception
	 */
	public static RequestList get() throws Exception{
		if(requestList == null){
			requestList = new RequestList();
		}
		return requestList;
	}
	
	/**
	 * Instantiates a new RequestList.
	 * 
	 * @see SQLiteJDBC#createRequestTable()
	 * @throws Exception the Exception
	 */
	private RequestList() throws Exception{
		database = SQLiteJDBC.get();
		database.createRequestTable();
		requestIds = new ArrayList<>();
		count = 0;
	}

	/**
	 * Adds request.
	 * 
	 * Searches the requestIds list for the value
	 * of i. If it is not found, the id of the request
	 * is set to i and the request is inserted into the
	 * database.
	 * 
	 * If all values of i up to, and including count, are
	 * found in the requestIds list, then ++count is set
	 * as the request id. count is then added to the 
	 * requestIds list and the request is inserted into
	 * the database.
	 *
	 * @see SQLiteJDBC#insertRequestTable(Request)
	 * @param r the Request
	 * @throws Exception the Exception
	 */
	public void addRequest(Request r) throws Exception{
		for(int i = 0; i <= count; i++){
			if(requestIds.indexOf(i) == -1){
				r.setId(i);
				requestIds.add(i);
				database.insertRequestTable(r);
				return;
			}
		}
		r.setId(++count);
		requestIds.add(count);
		database.insertRequestTable(r);
	}
		
	/**
	 * Edits request.
	 *
	 * @see SQLiteJDBC#updateRequestTable(Request)
	 * @param r the Request
	 * @throws Exception the Exception
	 */
	public void editRequest(Request r) throws Exception{
		database.updateRequestTable(r);
	}
	
	/**
	 * Deletes request.
	 * 
	 * Deletes the Integer object of the request id
	 * from the requestIds list.
	 *
	 * @see SQLiteJDBC#deleteOneRequestTable(java.util.UUID)
	 * @param r the Request
	 * @throws Exception the Exception
	 */
	public void deleteRequest(Request r) throws Exception{
		requestIds.remove((Integer)r.getId());
		database.deleteOneRequestTable(r.getUUID());
	}
	
	/**
	 * Deletes all requests.
	 *
	 * @see SQLiteJDBC#deleteAllRequestTable()
	 * @throws Exception the Exception
	 */
	public void deleteAll() throws Exception{
		database.deleteAllRequestTable();
	}
	
	/**
	 * Gets all requests.
	 *
	 * Gets the request ids of the requests in the
	 * database and adds them to the instance list.
	 * 
	 * Sets the instance count variable to the highest
	 * request id value.
	 *
	 * @see SQLiteJDBC#getAllRequestTable()
	 * @return the request list
	 * @throws Exception the Exception
	 */
	public ObservableList<Request> getAll() throws Exception{
		ObservableList<Request> rList = database.getAllRequestTable();
		requestIds = new ArrayList<>();
		
		if(rList.size() > 0){
			for(Request r:rList){
				if(r.getId() >= count){
					count = r.getId();
				}
				requestIds.add(r.getId());
			}
		}
		
		return rList;
	}

	/**
	 * Prints all requests.
	 *
	 * @see SQLiteJDBC#printAllRequests()
	 * @return the string
	 * @throws Exception the Exception
	 */
	public String printAllRequests() throws Exception{
		return database.printAllRequests();
	}
	
	/**
	 * Prints open requests.
	 *
	 * @see SQLiteJDBC#printOpenRequests()
	 * @return the string
	 * @throws Exception the Exception
	 */
	public String printOpenRequests() throws Exception{
		return database.printOpenRequests();
	}
	
	/**
	 * Prints closed requests.
	 *
	 * @see SQLiteJDBC#printClosedRequests()
	 * @return the string
	 * @throws Exception the Exception
	 */
	public String printClosedRequests() throws Exception{
		return database.printClosedRequests();
	}
	
	/**
	 * Prints table schema and insert statements as SQL commands
	 * 
	 * @see SQLiteJDBC#exportTableData()
	 * @return the string
	 * @throws Exception the Exception
	 */
	
	public String exportTableData() throws Exception{
		return database.exportTableData();
	}
}
