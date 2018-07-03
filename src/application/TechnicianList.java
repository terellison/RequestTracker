package application;

import java.util.UUID;

import application.database.SQLiteJDBC;
import javafx.collections.ObservableList;

/**
 * The Class TechnicianList.
 * 
 * Keeps track of the Technician objects.
 * Is the intermediary between Main.java and 
 * SQLiteJDBC.java
 * 
 * @author Chaz-Rae L. Moncrieffe
 * @since 3/1/2017
 */
public class TechnicianList {
	private static TechnicianList techList;
	private SQLiteJDBC database;
	
	/**
	 * Gets the instance of the TechnicianList class.
	 *
	 * @return the TechnicianList
	 * @throws Exception the Exception
	 */
	public static TechnicianList get() throws Exception{
		if(techList == null){
			techList = new TechnicianList();
		}
		return techList;
	}
	
	/**
	 * Instantiates a new TechnicianList.
	 *
	 * @see SQLiteJDBC#createTechTable()
	 * @throws Exception the Exception
	 */
	private TechnicianList() throws Exception{
		database = SQLiteJDBC.get();
		database.createTechTable();
	}
	
	/**
	 * Adds technician.
	 * 
	 * @see SQLiteJDBC#insertTechTable(Technician)
	 * @param t the Technician
	 * @throws Exception the Exception
	 */
	public void addTechnician(Technician t) throws Exception{
		database.insertTechTable(t);
	}
	
	/**
	 * Edits technician.
	 *
	 * @see SQLiteJDBC#updateTechTable(Technician)
	 * @param t the Technician
	 * @throws Exception the Exception
	 */
	public void editTechnician(Technician t) throws Exception{
		database.updateTechTable(t);
	}
	
	/**
	 * Deletes technician.
	 *
	 * @see SQLiteJDBC#deleteOneTechTable(UUID)
	 * @param t the Technician
	 * @throws Exception the Exception
	 */
	public void deleteTechnician(Technician t) throws Exception{
		database.deleteOneTechTable(t.getUUID());
	}
	
	/**
	 * Delete all technicians.
	 *
	 * @see SQLiteJDBC#deleteAllTechTable()
	 * @throws Exception the Exception
	 */
	public void deleteAll() throws Exception{
		database.deleteAllTechTable();
	}
	
	/**
	 * Gets one technician.
	 *
	 * @see SQLiteJDBC#getOneTechTable(UUID)
	 * @param uuid the UUID
	 * @return the technician
	 * @throws Exception the Exception
	 */
	public Technician getTech(UUID uuid) throws Exception{
		return database.getOneTechTable(uuid);
	}
	
	/**
	 * Gets all technicians.
	 *
	 * @see SQLiteJDBC#getAllTechTable()
	 * @return the technician list
	 * @throws Exception the Exception
	 */
	public ObservableList<Technician> getAll() throws Exception{
		return database.getAllTechTable();
	}
	
	/**
	 * Prints all technicians.
	 *
	 * @see SQLiteJDBC#printAllTechs()
	 * @return the string
	 * @throws Exception the Exception
	 */
	public String printAllTechs() throws Exception{
		return database.printAllTechs();
	}
	
	/**
	 * Search technician list.
	 * 
	 * Searches for a technician in the database and returns
	 * their position in the list.
	 *
	 * @param uuid the UUID
	 * @return the int
	 * @throws Exception the Exception
	 */
	public int searchTechList(UUID uuid) throws Exception{
		int a = -1;
		ObservableList<Technician> list = database.getAllTechTable();
		
		for(Technician t:list){
			if(t.getUUID().equals(uuid)){
				return list.indexOf(t);
			}
		}
		
		return a;
	}	
}
