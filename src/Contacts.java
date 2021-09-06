import java.io.Serializable;

@SuppressWarnings("serial")
public class Contacts implements Serializable{
	
	
	//attributes
	private String firstName,lastName,uniqueID,phoneNumber;
	
	
	//constructor
	public Contacts(String firstName, String lastName, String uniqueID, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.uniqueID = uniqueID;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	

}
