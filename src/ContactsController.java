import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class ContactsController {
	
	
	ContactView contactView;
	ArrayList<Contacts> list;
	
	public ContactsController(ContactView contactView, ArrayList<Contacts> list) {
		this.contactView = contactView;
		this.list = list;
		
	}
	
	
	//for adding...
	public void addContact(Contacts contacts) {
		list.add(contacts);
	}
	
	//for removing...
	public void removeContact(Contacts contacts) {
		list.remove(contacts);
	}
	
	
	
	public String listContacts() {
		return this.contactView.printContactDetails(this.list).toString();
	}
	
	
	public void saveContactList(String fileName) throws ClassNotFoundException {
		
		
		try {
			
			File f = new File(fileName);
			
			if(!f.exists()) {
				f.createNewFile();
			}
				
			FileOutputStream fos = new FileOutputStream(fileName);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    for (int i = 0; i < this.list.size(); i++) {
		    	oos.writeObject(this.list.get(i));
			}
		    
		    oos.close();
		
				
		} catch (IOException error) {
		     error.printStackTrace();
		}
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public void readContactList(String fileName) throws ClassNotFoundException, IOException {
		
	try {
		
		   FileInputStream fin = new FileInputStream(fileName);
		   ObjectInputStream ois = new ObjectInputStream(fin);
		  
		   for (;;) {
		   Contacts c = (Contacts) ois.readObject();
		   
		   this.list.add(c);
		   //System.out.println(c.getFirstName());
		   
		   }
		   
	} catch (EOFException  error) {
	    // error.printStackTrace();
	}
	
	
		
	}
	
	@SuppressWarnings("unchecked")
	public void readCloseContactList(String fileName, ArrayList<CloseContacts> list) throws ClassNotFoundException, IOException {
		
	try {
		
		   FileInputStream fin = new FileInputStream(fileName);
		   ObjectInputStream ois = new ObjectInputStream(fin);
		  
		   for (;;) {
			   
		   CloseContacts c = (CloseContacts) ois.readObject();
		   
		   list.add(c);
		   //System.out.println(c.getFirstName());
		   
		   }
		   
	} catch (EOFException  error) {
	    // error.printStackTrace();
	}
	
	
		
	}
	
	public void saveCloseContactList(String fileName, ArrayList<CloseContacts> list) throws ClassNotFoundException {
		
		
		try {
			
			File f = new File(fileName);
			
			if(!f.exists()) {
				f.createNewFile();
			}
				
			FileOutputStream fos = new FileOutputStream(fileName);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    for (int i = 0; i < list.size(); i++) {
		    	oos.writeObject(list.get(i));
			}
		    
		    oos.close();
		
				
		} catch (IOException error) {
		     error.printStackTrace();
		}
		
		
		
	}
	
	

}
