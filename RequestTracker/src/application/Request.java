package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Request {
	private UUID uuid;
	private SimpleStringProperty requested;
	private SimpleStringProperty completed;
	private SimpleStringProperty description;
	private SimpleStringProperty techId;
	private SimpleStringProperty notes;
	private SimpleBooleanProperty isCompleted;
	
	public Request(String requested, String completed, String description,  UUID techId, String notes, Boolean isCompleted){
		this.uuid = UUID.randomUUID();
		this.requested = new SimpleStringProperty(requested);
		this.completed = new SimpleStringProperty(completed);
		this.description = new SimpleStringProperty(description);
		this.techId = new SimpleStringProperty(techId.toString());
		this.notes = new SimpleStringProperty(notes);
		this.isCompleted = new SimpleBooleanProperty(isCompleted);
	}
	
	public Request(UUID uuid, String requested, String completed, String description, UUID techId, String notes, Boolean isCompleted){
		this.uuid = uuid;
		this.requested = new SimpleStringProperty(requested);
		this.completed = new SimpleStringProperty(completed);
		this.description = new SimpleStringProperty(description);
		this.techId = new SimpleStringProperty(techId.toString());
		this.notes = new SimpleStringProperty(notes);
		this.isCompleted = new SimpleBooleanProperty(isCompleted);
	}
	
	public UUID getUUID(){
		return uuid;
	}

	public String getRequested() {
		return requested.get();
	}

	public void setRequested(String requested) {
		this.requested.set(requested);
	}

	public String getCompleted(){
		return completed.get();
	}
	
	public void setCompleted(String completed){
		this.completed.set(completed);
	}
	
	public String getDescription(){
		return description.get();
	}
	
	public void setDescription(String description){
		this.description.set(description);
	}
	
	public String getTechId(){
		return this.techId.get();
	}
	
	public void setTechId(UUID techId){
		this.techId.set(techId.toString());
	}
		
	public String getNotes(){
		return notes.get();
	}
	
	public void setNotes(String notes){
		this.notes.set(notes);
	}
	
	public Boolean isCompleted(){
		return isCompleted.get();
		
	}
	
	public void setIsCompleted(Boolean isCompleted){
		this.isCompleted.set(isCompleted);
	}
	
	public LocalDate getRequestedLocalDate(){
		String dateString = this.requested.get().replaceAll("/", "-");
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}
	
	public LocalDate getCompletedLocalDate(){
		String dateString = this.completed.get().replaceAll("/", "-");
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}
}
