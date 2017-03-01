package application;

import java.util.UUID;

import application.database.SQLiteJDBC;
import javafx.collections.ObservableList;

/**
 * The Class TechnicianList.
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
	 * @throws Exception the Exception
	 */
	private TechnicianList() throws Exception{
		database = SQLiteJDBC.get();
		database.createTechTable();
	}
	
	/**
	 * Adds technician.
	 *
	 * @param t the Technician
	 * @throws Exception the Exception
	 */
	public void addTechnician(Technician t) throws Exception{
		database.insertTechTable(t);
	}
	
	/**
	 * Edits technician.
	 *
	 * @param t the Technician
	 * @throws Exception the Exception
	 */
	public void editTechnician(Technician t) throws Exception{
		database.updateTechTable(t);
	}
	
	/**
	 * Deletes technician.
	 *
	 * @param t the Technician
	 * @throws Exception the Exception
	 */
	public void deleteTechnician(Technician t) throws Exception{
		database.deleteOneTechTable(t.getUUID());
	}
	
	/**
	 * Delete all technicians.
	 *
	 * @throws Exception the Exception
	 */
	public void deleteAll() throws Exception{
		database.deleteAllTechTable();
	}
	
	/**
	 * Gets one tech.
	 *
	 * @param uuid the UUID
	 * @return the tech
	 * @throws Exception the Exception
	 */
	/* Getters */	
	public Technician getTech(UUID uuid) throws Exception{
		return database.getOneTechTable(uuid);
	}
	
	/**
	 * Gets all technicians.
	 *
	 * @return the technician list
	 * @throws Exception the Exception
	 */
	public ObservableList<Technician> getAll() throws Exception{
		return database.getAllTechTable();
	}
	
	/**
	 * Prints all techs.
	 *
	 * @return the string
	 * @throws Exception the Exception
	 */
	public String printAllTechs() throws Exception{
		return database.printAllTechs();
	}
	
	/**
	 * Search tech list.
	 *
	 * @param uuid the uuid
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
