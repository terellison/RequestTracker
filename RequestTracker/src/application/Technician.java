package application;

import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Technician {
	private UUID uuid;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty idNumber;
	private String fullName;
	
	public Technician(String firstName, String lastName, String idNumber){
		this.uuid = UUID.randomUUID();
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.idNumber = new SimpleStringProperty(idNumber);		
		fullName = firstName + " " + lastName;
	}
	
	public Technician(UUID uuid, String firstName, String lastName, String idNumber){
		this.uuid = uuid;
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.idNumber = new SimpleStringProperty(idNumber);		
		fullName = firstName + " " + lastName;
	}

	public UUID getUUID(){
		return uuid;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getIdNumber() {
		return idNumber.get();
	}

	public void setIdNumber(String idNumber) {
		this.idNumber.set(idNumber);
	}
	
	public void setFullName(){
		fullName = firstName.get() + " " + lastName.get();
	}
	
	public StringProperty nameProperty(){
		SimpleStringProperty fName = new SimpleStringProperty(fullName);
		return fName;
	}
	
	public StringProperty idProperty(){
		return idNumber;
	}
}
