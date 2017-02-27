package application;

import java.util.UUID;

import application.database.SQLiteJDBC;
import javafx.collections.ObservableList;

public class TechnicianList {
	private static TechnicianList techList;
	private SQLiteJDBC database;
	
	public static TechnicianList get() throws Exception{
		if(techList == null){
			techList = new TechnicianList();
		}
		return techList;
	}
	
	/* Constructor */
	private TechnicianList() throws Exception{
		database = SQLiteJDBC.get();
		database.createTechTable();
	}
	
	/* Add, edit, delete */
	public void addTechnician(Technician t) throws Exception{
		database.insertTechTable(t);
	}
	
	public void editTechnician(Technician t) throws Exception{
		database.updateTechTable(t);
	}
	
	public void deleteTechnician(Technician t) throws Exception{
		database.deleteOneTechTable(t.getUUID());
	}
	
	/* Getters */	
	public Technician getTech(UUID uuid) throws Exception{
		return database.getOneTechTable(uuid);
	}
	
	public ObservableList<Technician> getAll() throws Exception{
		return database.getAllTechTable();
	}
	
	public String printAllTechs() throws Exception{
		return database.printAllTechs();
	}
	
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
