package application;

import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class Technician.
 * 
 * @author Chaz-Rae L. Moncrieffe
 * @since 3/1/2017
 */
public class Technician {
	private UUID uuid;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty idNumber;
	private String fullName;
	
	/**
	 * Instantiates a new technician.
	 *
	 * @param firstName the String
	 * @param lastName the String
	 * @param idNumber the String
	 */
	public Technician(String firstName, String lastName, String idNumber){
		this.uuid = UUID.randomUUID();
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.idNumber = new SimpleStringProperty(idNumber);		
		fullName = firstName + " " + lastName;
	}
	
	/**
	 * Instantiates a new technician.
	 *
	 * @param uuid the UUID
	 * @param firstName the String
	 * @param lastName the String
	 * @param idNumber the String
	 */
	public Technician(UUID uuid, String firstName, String lastName, String idNumber){
		this.uuid = uuid;
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.idNumber = new SimpleStringProperty(idNumber);		
		fullName = firstName + " " + lastName;
	}

	/**
	 * Gets the uuid.
	 *
	 * @return uuid
	 */
	public UUID getUUID(){
		return uuid;
	}

	/**
	 * Gets the first name.
	 *
	 * @return first name
	 */
	public String getFirstName() {
		return firstName.get();
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new String
	 */
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	/**
	 * Gets the last name.
	 *
	 * @return last name
	 */
	public String getLastName() {
		return lastName.get();
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new String
	 */
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	/**
	 * Gets the id number.
	 *
	 * @return id number
	 */
	public String getIdNumber() {
		return idNumber.get();
	}

	/**
	 * Sets the id number.
	 *
	 * @param idNumber the new String
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber.set(idNumber);
	}
	
	/**
	 * Sets the full name.
	 */
	public void setFullName(){
		fullName = firstName.get() + " " + lastName.get();
	}
	
	/**
	 * Name property.
	 *
	 * @return the string property
	 */
	public StringProperty nameProperty(){
		SimpleStringProperty fName = new SimpleStringProperty(fullName);
		return fName;
	}
	
	/**
	 * Id property.
	 *
	 * @return the string property
	 */
	public StringProperty idProperty(){
		return idNumber;
	}
}
