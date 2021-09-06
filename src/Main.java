
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import components.*;

public class Main extends Application{

	public static final int WIDTH = 900, HEIGHT = 600;
	public static final String TITLE = "COVID - 19 - Application";
	public static String[] ButtonArray = {"LOAD","SAVE","EXIT","ADD","REMOVE","LIST","ADD CLOSE CONTACT","SEARCH CLOSE CONTACT"};
	public static String[] labelArray = {"Enter First Name: ","Enter Last Name",
			"Enter Unique ID: ","Enter Phone Number: ", "Select Person #1 : ", "Select Person #2 : ",
			"Select Date and Time : "};
	
	public static final String FILENAME = "data.dat";
	public static final String CLOSE_FILENAME = "data1.dat";
	
	
	//textfields and labels;
	TextLabel titlecontacts,lblfname,lbllastname,lblid,lblphone,lblp1,lblp2,lbldateandtime;
	InputBox fname,lastname,uniqureId,phone,searchbox;
	
	
	DatePicker datepicker;
	
	
	
	
	//area
	TextArea area, searchArea;
	
	//Controller
	ContactsController contactsController;
	ContactView contactView;
	
	
	//ArrayList to store the data...
	static ArrayList<Contacts> arraylist = new ArrayList<Contacts>();
	//for close contacts
	static ArrayList<CloseContacts> closearraylist = new ArrayList<CloseContacts>();
	
	
	//buttons
	MyButton load,save,exit,add,remove,list, addclosecontact, searchclosecontact;
	
	BorderPane root;
	Scene sc;
	Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

	void initialize() {
		root = new BorderPane();
		sc  = new Scene(root,WIDTH,HEIGHT);
		
		
		//labels
		lblfname = new TextLabel(labelArray[0],"#f9f9f9", "#000",15);
		lbllastname = new TextLabel(labelArray[1],"#f9f9f9", "#000",15);
		lblid = new TextLabel(labelArray[2],"#f9f9f9", "#000",15);
		lblphone = new TextLabel(labelArray[3],"#f9f9f9", "#000",15);

		lblp1 = new TextLabel(labelArray[4],"#f9f9f9", "#000",15);
		lblp2 = new TextLabel(labelArray[5],"#f9f9f9", "#000",15);
		lbldateandtime = new TextLabel(labelArray[5],"#f9f9f9", "#000",15);
		
		//instances
		titlecontacts = new TextLabel("*** CONTACTS ***","#6699ff", "#fff",20);
		titlecontacts.setAlignment(Pos.BASELINE_CENTER);
		titlecontacts.setPrefWidth(1500);
		fname = new InputBox("Enter First Name", "#f1f1f1", "#000");fname.setPrefWidth(300);
		lastname = new InputBox("Enter Last Name", "#f1f1f1", "#000");
		uniqureId = new InputBox("Enter Unique ID", "#f1f1f1", "#000");
		phone = new InputBox("Enter Phone", "#f1f1f1", "#000");
		searchbox = new InputBox("Enter Name...", "#f1f1f1", "#000");
		
		//buttons
		load = new MyButton(ButtonArray[0],"#6699ff", 15);load.setPrefWidth(200);
		save = new MyButton(ButtonArray[1],"#4CAF50", 15);save.setPrefWidth(200);
		exit = new MyButton(ButtonArray[2],"tomato", 15);exit.setPrefWidth(200);
		add = new MyButton(ButtonArray[3],"#6699ff", 15);add.setPrefWidth(100);
		remove = new MyButton(ButtonArray[4],"tomato", 15);
		list = new MyButton(ButtonArray[5],"#4CAF50", 15);list.setPrefWidth(100);
		addclosecontact = new MyButton(ButtonArray[6],"#6699ff", 15);addclosecontact.setPrefWidth(100);
		searchclosecontact = new MyButton(ButtonArray[7],"#6699ff", 15);searchclosecontact.setPrefWidth(100);

	}

	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		initialize();
		stage = new Stage();
		this.stage = stage;
		stage.setTitle(TITLE);
		
		
		//controller and view...
		contactView = new ContactView();
		contactsController = new ContactsController(contactView, arraylist);
		
		
		//reading contact list
		if(arraylist.size() == 0) {
			
			File f = new File(FILENAME);
			
			if(f.exists()) {
			
			try {
				contactsController.readContactList(FILENAME);
				System.out.println("loadded contacts : " + arraylist.size());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			}
		}
		
		//reading close contact list
		if(closearraylist.size() == 0) {
			
			File f1 = new File(CLOSE_FILENAME);
			if(!f1.exists()) {f1.createNewFile();}
			
			if(f1.exists()) {
			
			try {
				contactsController.readCloseContactList(CLOSE_FILENAME,closearraylist);
				System.out.println("loadded close contacts : " + closearraylist.size());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			}
		}
		
		
		//adding to bottom
		root.setBottom(Footer());
		root.setCenter(centerlist());
		root.setRight(areaView());
		
		
		area.setText(""+contactsController.listContacts());
		
		
		
		
		stage.setScene(sc);
		stage.show();
		
	}
	
	
	 TabPane centerlist() {
		 
		TabPane t = new TabPane();
		t.setStyle("-fx-background-color:transparent;");
		 
	 	Tab tab1 = new Tab("CONTACTS", ContactForm());tab1.setClosable(false);
        Tab tab2 = new Tab("CLOSE CONTACTS"  , CloseContactForm());tab2.setClosable(false);
        Tab tab3 = new Tab("FIND CONTACTS" , findForm());tab3.setClosable(false);

        
        t.getTabs().add(tab1);
        t.getTabs().add(tab2);
        t.getTabs().add(tab3);
		 
		 
		 
		 return t;
	 }
	
	
	 public VBox CloseContactForm() {
			
			VBox vb  = new VBox();
			vb.setPadding(new Insets(20));
			vb.setSpacing(10);
			vb.setStyle("-fx-background-color:#fff;-fx-border-color:#000;");
			
			TextLabel lbl3 = new TextLabel(" *** CLOSE CONTACTS *** ","#74a3ed", "#fff",15);
			lbl3.setMaxWidth(500);
			vb.getChildren().add(lbl3);
			
			ArrayList<String> nameslist = new ArrayList<String>();
			ArrayList<String> timelist = new ArrayList<String>();
			
			
			
			for (int i = 0; i < arraylist.size(); i++) {
				nameslist.add(arraylist.get(i).getFirstName()+" "+arraylist.get(i).getLastName());
			}
			
			
			ComboBox<String> person1 = new ComboBox<String>(FXCollections.observableArrayList(nameslist));
			person1.getSelectionModel().select(0);
			person1.setMaxWidth(500);
			ComboBox<String> person2 = new ComboBox<String>(FXCollections.observableArrayList(nameslist));
			person2.setMaxWidth(500);
			person2.getSelectionModel().select(1);
			
			
			InputBox timepciker = new InputBox("Enter Time Like : (HH:MM)", "#000", "#fff");
			timepciker.setMaxWidth(500);
			
			
			
			datepicker = new DatePicker();
			datepicker.setMaxWidth(500);
			
			vb.getChildren().addAll(lblp1,person1,lblp2,person2,lbldateandtime,datepicker,timepciker, addclosecontact);
			
			
			addclosecontact.setOnAction(e -> {
				
				try {
				
				String p1  = person1.getSelectionModel().getSelectedItem();
				String p2  = person2.getSelectionModel().getSelectedItem();
				String datep = datepicker.getValue().toString();
				String time = timepciker.getText().toString();
				
				if(datep.length() == 0 || time.length() == 0) {
					new Alert(AlertType.INFORMATION,"Kindly select date and time....").show();
				}else {
				
				closearraylist.add(new CloseContacts(p1, p2, datep, time));
				
				new Alert(AlertType.INFORMATION,"Close Connection added success").show();
				
				
				}
				
				}catch (Exception e1) {
					// TODO: handle exception
					new Alert(AlertType.INFORMATION,"Kindly select date and time....").show();
				}
				
				
			});
			
	
			return vb;
	 }
	 
	 public VBox findForm() {
			
			VBox vb  = new VBox();
			vb.setPadding(new Insets(20));
			vb.setSpacing(10);
			vb.setStyle("-fx-background-color:#fff;-fx-border-color:#555;");
			
			
			TextLabel lbl3 = new TextLabel(" *** FIND CLOSE CONTACTS *** ","#74a3ed", "#fff",15);
			lbl3.setMaxWidth(500);
			vb.getChildren().add(lbl3);
			
			searchArea = new TextArea();
			searchArea.setPrefWidth(400);
			searchArea.setPrefHeight(600);
			searchbox.setPromptText("Enter Full Name...");
			
		
			vb.getChildren().addAll(searchbox,searchclosecontact,searchArea);
			
			
			searchclosecontact.setOnAction(e ->{
				
				String searchText = searchbox.getText().toString();
				if(searchText.length() == 0) {
					new Alert(AlertType.INFORMATION,"Please enter name to search....").show();
				}else {
					
				searchArea.setText("");
				searchArea.setText(""+contactView.printCloseContactDetails(closearraylist, searchText).toString());
					
					
				}
				
				
			});
			
			
	
			return vb;
	 }
	 
	 
	
	//for contact form
	public VBox ContactForm() {
		
		VBox vb  = new VBox();
		vb.setPadding(new Insets(20));
		vb.setSpacing(10);
		vb.setStyle("-fx-background-color:#fff;-fx-border-color:#f1f1f1;");
		
		
		
		//adding widgets
		vb.getChildren().addAll(titlecontacts,lblfname,fname,lbllastname,lastname,
				lblid,uniqureId,lblphone,phone);
		
		

		HBox b1 = new HBox();
		b1.setPadding(new Insets(20));
		b1.setSpacing(20);
		b1.getChildren().addAll(add,remove,list);
		vb.getChildren().add(b1);
		
		//for removing from list
		remove.setOnAction(e->{
			
			boolean isFound = false;
			
			String UID  = uniqureId.getText().toString();
			for (int i = 0; i <arraylist.size(); i++) {
				if(arraylist.get(i).getUniqueID().equalsIgnoreCase(UID)) {
					new Alert(AlertType.INFORMATION,"CONTACT DELETED SUCCESS!").show();
					isFound = true;
					area.setText(""+contactsController.listContacts());//updating....
				}else continue;
			}
			
			if(!isFound) {
				new Alert(AlertType.INFORMATION,"UNIQUE ID NOT FOUND!").show();
			}
			
		});
		
		
		add.setOnAction(e ->{
			
			String FN  = fname.getText().toString();
			String LN  = lastname.getText().toString();
			String UID  = uniqureId.getText().toString();
			String PH  = phone.getText().toString();

			if(FN.length() == 0 || LN.length() == 0 ||UID.length() == 0 ||PH.length() == 0  ) {
				new Alert(AlertType.INFORMATION,"Please enter the valid data....to add contact!").show();
			}else {
				
				//adding to list...
				arraylist.add(new Contacts(FN, LN, UID, PH));
				area.setText(""+contactsController.listContacts());//updating...GUI
				
				
				fname.setText("");
				lastname.setText("");
				uniqureId.setText("");
				phone.setText("");
				
			}
			
			
		});
		
		
		list.setOnAction(e ->{
			
			
			area.setText(""+contactsController.listContacts());//displaying...
			
		});
		
		
		return vb;
	}
	
	public VBox areaView() {
		
		VBox a  = new VBox();
		a.setPadding(new Insets(20));
		a.setSpacing(10);
		a.setStyle("-fx-background-color:#fff;-fx-border-color:#999;");
		
		TextLabel lbl3 = new TextLabel(" *** CONTACT INFORMATION *** ","#74a3ed", "#fff",15);
		lbl3.setMaxWidth(500);
		a.getChildren().add(lbl3);
		
		area = new TextArea();
		area.setPrefWidth(400);
		area.setPrefHeight(600);
		a.getChildren().add(area);
		
		
		
		return a;
	}
	
	

	
	
	public HBox Footer() {
		
		HBox b = new HBox();
		b.setPadding(new Insets(20));
		b.setSpacing(20);
		b.setAlignment(Pos.CENTER);
		
		
		
		b.getChildren().addAll(load,save,exit);
		
		
		//when load button pressed
		load.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
					try {
						try {
							arraylist.clear();
							contactsController.readContactList(FILENAME);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("loadded contacts : " + arraylist.size());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				
			}
		});

		//when exit button pressed
		save.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(arraylist.size() == 0) {
					new Alert(AlertType.INFORMATION," there are 0 records , you can't save right now!").show();
				}else {
				//saving to file
				try {
					contactsController.saveContactList(FILENAME);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				if(arraylist.size() != 0 && closearraylist.size() == 0) {
					new Alert(AlertType.INFORMATION," there are 0 records , you can't save right now!").show();
				}else {
				//saving to file
				try {
					contactsController.saveCloseContactList(CLOSE_FILENAME,closearraylist);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
				
				
				new Alert(AlertType.INFORMATION,"Data have been saved to file....successfully!").show();
				}
			}
		});
		
		
		//when exit button pressed
		exit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				stage.close();//closing...
				
			}
		});
		
		
		return b;
	}
	
	
	
}
