import java.io.Serializable;

public class CloseContacts implements Serializable{
	
	
	private String person1 , person2, date, time;

	
	
	public CloseContacts(String person1, String person2, String date, String time) {
		super();
		this.person1 = person1;
		this.person2 = person2;
		this.date = date;
		this.time = time;
	}
	

	public String getPerson1() {
		return person1;
	}

	public void setPerson1(String person1) {
		this.person1 = person1;
	}

	public String getPerson2() {
		return person2;
	}

	public void setPerson2(String person2) {
		this.person2 = person2;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
	

}
