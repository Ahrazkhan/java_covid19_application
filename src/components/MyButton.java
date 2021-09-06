package components;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class MyButton extends Button{

	
	public MyButton(String title, String color, int fontSize) {
		// TODO Auto-generated constructor stub
		this.setCursor(Cursor.HAND);
		this.setStyle("-fx-background-color:"+color+";-fx-text-fill:#fff;"
				+ "-fx-background-radius:5px;-fx-border-radius:5px;");
		this.setText(title);
		this.setFont(Font.font(fontSize));
		this.setMaxWidth(1500);
		
	}
	
}
