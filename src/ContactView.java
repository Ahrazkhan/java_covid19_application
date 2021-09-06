

import java.util.ArrayList;

public class ContactView {
	
	
	public StringBuilder printContactDetails(ArrayList<Contacts> list) {
		
		
		StringBuilder st = new StringBuilder();
		
		st.append("People in the Application: \n");
		st.append("FIRST NAME  \t LAST NAME \t UID \t PHONE \n");
		for (int i = 0; i < list.size(); i++) {
			st.append(list.get(i).getFirstName()+" \t\t "+list.get(i).getLastName()+" \t\t "+
					list.get(i).getUniqueID()+"  \t "+list.get(i).getPhoneNumber()+"\n");
		}
		
		return st;
	}
	
	
	public StringBuilder printCloseContactDetails(ArrayList<CloseContacts> list, String text) {
		
		boolean found = false;
		
		StringBuilder st = new StringBuilder();
		
		st.append("People in Close Contacts: \n");
		st.append("PERSON#1 \t PERSON#2 \t\t DATE \t\t TIME \n");
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getPerson1().equals(text) || list.get(i).getPerson2().equals(text)) {
			st.append(list.get(i).getPerson1()+" \t "+list.get(i).getPerson2()+" \t "+
					list.get(i).getDate()+"  \t "+list.get(i).getTime()+"\n");
				found = true;
			} else {
				continue;
			}
		}
		if(!found) {
			st.append("STATUS : CLOSE CONTACT NOT FOUND ! ");
		}
		
		return st;
	}
	

}
