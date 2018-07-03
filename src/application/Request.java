package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * The Class Request.
 * 
 * @author Chaz-Rae L. Moncrieffe
 * @since 3/1/2017
 */
public class Request {
	private UUID uuid;
	private SimpleIntegerProperty id;
	private SimpleStringProperty requested;
	private SimpleStringProperty completed;
	private SimpleStringProperty description;
	private SimpleStringProperty techId;
	private SimpleStringProperty notes;
	private SimpleBooleanProperty isCompleted;
	
	/**
	 * Instantiates a new request.
	 *
	 * @param requested the String
	 * @param completed the String
	 * @param description the String
	 * @param techId the UUID
	 * @param notes the String
	 * @param isCompleted the boolean
	 */
	public Request(String requested, String completed, String description,  UUID techId, String notes, boolean isCompleted){
		this(UUID.randomUUID(), 0, requested, completed, description, techId, notes, isCompleted);
	}
	
	/**
	 * Instantiates a new request.
	 *
	 * @param uuid the UUID
	 * @param id the int
	 * @param requested the String
	 * @param completed the String
	 * @param description the String
	 * @param techId the UUID
	 * @param notes the String
	 * @param isCompleted the boolean
	 */
	public Request(UUID uuid, int id, String requested, String completed, String description, UUID techId, String notes, boolean isCompleted){
		this.uuid = uuid;
		this.id = new SimpleIntegerProperty(id);
		this.requested = new SimpleStringProperty(requested);
		this.completed = new SimpleStringProperty(completed);
		this.description = new SimpleStringProperty(description);
		this.techId = new SimpleStringProperty(techId.toString());
		this.notes = new SimpleStringProperty(notes);
		this.isCompleted = new SimpleBooleanProperty(isCompleted);
	}
	
	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	public UUID getUUID(){
		return uuid;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return id
	 */
	public int getId(){
		return id.get();
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new int
	 */
	public void setId(int id){
		this.id.set(id);
	}

	/**
	 * Gets the requested date.
	 *
	 * @return requested date
	 */
	public String getRequested() {
		return requested.get();
	}

	/**
	 * Sets the requested date.
	 *
	 * @param requested the new String
	 */
	public void setRequested(String requested) {
		this.requested.set(requested);
	}

	/**
	 * Gets the completed date.
	 *
	 * @return completed date
	 */
	public String getCompleted(){
		return completed.get();
	}
	
	/**
	 * Sets the completed date.
	 *
	 * @param completed the new String
	 */
	public void setCompleted(String completed){
		this.completed.set(completed);
	}
	
	/**
	 * Gets the description.
	 *
	 * @return description
	 */
	public String getDescription(){
		return description.get();
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new String
	 */
	public void setDescription(String description){
		this.description.set(description);
	}
	
	/**
	 * Gets the tech id.
	 *
	 * @return tech id
	 */
	public String getTechId(){
		return this.techId.get();
	}
	
	/**
	 * Sets the tech id.
	 *
	 * @param techId the new UUID
	 */
	public void setTechId(UUID techId){
		this.techId.set(techId.toString());
	}
		
	/**
	 * Gets the notes.
	 *
	 * @return notes
	 */
	public String getNotes(){
		return notes.get();
	}
	
	/**
	 * Sets the notes.
	 *
	 * @param notes the new String
	 */
	public void setNotes(String notes){
		this.notes.set(notes);
	}
	
	/**
	 * Checks if is completed.
	 *
	 * @return is Completed
	 */
	public boolean isCompleted(){
		return isCompleted.get();
		
	}
	
	/**
	 * Sets the checks if is completed.
	 *
	 * @param isCompleted the new boolean
	 */
	public void setIsCompleted(boolean isCompleted){
		this.isCompleted.set(isCompleted);
	}
	
	/**
	 * Gets the requested local date.
	 *
	 * @return requested local date
	 */
	public LocalDate getRequestedLocalDate(){
		String dateString = this.requested.get().replaceAll("/", "-");
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}
	
	/**
	 * Gets the completed local date.
	 *
	 * @return completed local date
	 */
	public LocalDate getCompletedLocalDate(){
		String dateString = this.completed.get().replaceAll("/", "-");
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}
}
