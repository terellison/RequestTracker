package application.database;

/*
 * These are the names of the tables and columns used for the
 * database in the SQLiteJDBC class.
 */
public class DbSchema {
	public static final class RequestTable{
		public static final String NAME = "request";		
		public static final class Cols{
			public static final String UUID = "uuid";
			public static final String REQUESTED = "requested";
			public static final String COMPLETED = "completed";
			public static final String DESCRIPTION = "description";
			public static final String TECH = "tech";
			public static final String NOTES = "notes";
			public static final String ISCOMPLETED = "isCompleted";
		}
	}
	public static final class TechnicianTable{
		public static final String NAME = "technician";	
		public static final class Cols{
			public static final String UUID = "uuid";
			public static final String FIRSTNAME = "firstName";
			public static final String LASTNAME = "lastName";
			public static final String IDNUMBER = "idNumber"; 
		}
	}
}
