package application.database;

/**
 * The Class DbSchema.
 * 
 * Holds the names of the tables and columns used for the
 * database in the SQLiteJDBC class.
 * 
 * @author Chaz-Rae L. Moncrieffe
 * @since 3/1/2017
 */

public class DbSchema {
	
	/**
	 * The Class RequestTable.
	 * 
	 * Holds the names of the request table and the columns.
	 */
	public static final class RequestTable{
		
		/** The Constant NAME of request table. */
		public static final String NAME = "request";		
		
		/**
		 * The Class Cols.
		 * 
		 * Holds the names of the columns.
		 */
		public static final class Cols{
			public static final String UUID = "uuid";
			public static final String ID = "requestId";
			public static final String REQUESTED = "requested";
			public static final String COMPLETED = "completed";
			public static final String DESCRIPTION = "description";
			public static final String TECH = "tech";
			public static final String NOTES = "notes";
			public static final String ISCOMPLETED = "isCompleted";
		}
	}
	
	/**
	 * The Class TechnicianTable.
	 * 
	 * Holds the names of the technician table and the columns.
	 */
	public static final class TechnicianTable{
		
		/** The Constant NAME of technician table. */
		public static final String NAME = "technician";	
		
		/**
		 * The Class Cols.
		 * 
		 * Holds the names of the columns.
		 */
		public static final class Cols{
			public static final String UUID = "uuid";
			public static final String FIRSTNAME = "firstName";
			public static final String LASTNAME = "lastName";
			public static final String IDNUMBER = "idNumber"; 
		}
	}
}
