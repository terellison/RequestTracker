package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowAbout {
	private Stage stage;
	private Scene scene;
	
	public WindowAbout(Stage primaryStage){
		stage = new Stage();
		scene = new Scene(setUpScene(), 500, 200);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("About Request Tracker");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(new Image(getClass().getResource("images/Repository-16.png").toExternalForm()));
		stage.initOwner(primaryStage);
	}
	
	public void Show(){
		stage.showAndWait();
	}
	
	private GridPane setUpScene(){
		GridPane root = new GridPane();
		root.setPadding(new Insets(20));
		root.setHgap(25);
		root.setVgap(15);
		
		ImageView imgIcon = new ImageView(new Image(getClass().getResource("images/Repository_100.png").toExternalForm()));
		Text txtAbout = new Text("Request Tracker 2017\nChaz-Rae L. Moncrieffe\nCOP2940 Computer Programming Internship");
		
		root.add(imgIcon, 0, 0);
		root.add(txtAbout, 1, 0);
	
		return root;
	}
}
/*
 *Eclipse IDE for Java Developers

Version: Neon.2 Release (4.6.2)
Build id: 20161208-0600
(c) Copyright Eclipse contributors and others 2000, 2016.  All rights reserved. Eclipse and the Eclipse logo are trademarks of the Eclipse Foundation, Inc., 
https://www.eclipse.org/. The Eclipse logo cannot be altered without Eclipse's permission. Eclipse logos are provided for use under the Eclipse logo and trademark guidelines, 
https://www.eclipse.org/logotm/. Oracle and Java are trademarks or registered trademarks of Oracle and/or its affiliates. Other names may be trademarks of their respective owners.

This product includes software developed by other open source projects including the Apache Software Foundation, https://www.apache.org/. 
 */
