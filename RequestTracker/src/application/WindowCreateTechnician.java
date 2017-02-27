package application;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowCreateTechnician {
	private Stage stage;
	private Scene scene;
	
	public WindowCreateTechnician(Stage primaryStage) throws Exception{
		stage = new Stage();
		scene = new Scene(setUpScene(), 350, 300);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Create Technician");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(new Image(getClass().getResource("images/Repository-16.png").toExternalForm()));
		stage.initOwner(primaryStage);
	}
	
	public void Show(){
		stage.showAndWait();
	}
	
	private GridPane setUpScene() throws Exception{
		GridPane root = new GridPane();
		root.setPadding(new Insets(20));
		root.setHgap(25);
		root.setVgap(15);
		
		TilePane tlpCloseCreate = new TilePane();
		tlpCloseCreate.setHgap(8);
	    tlpCloseCreate.setPrefColumns(2);
		
		Label lblFirstName = new Label("First Name");
		Label lblLastName = new Label("Last Name");
		Label lblIdNumber = new Label("ID Number");
		TextField txfFirstName = new TextField();
		TextField txfLastName = new TextField();
		TextField txfIdNumber = new TextField();
		Button btnClose = new Button("Close");
		Button btnCreate = new Button("Create");
		
		InterfaceMethods methods = new InterfaceMethods();
		
		root.add(lblFirstName, 0, 0);
		GridPane.setHalignment(lblFirstName, HPos.RIGHT);
		
		root.add(txfFirstName, 1, 0);
		GridPane.setHalignment(txfFirstName, HPos.LEFT);
		
		root.add(lblLastName, 0, 1);
		GridPane.setHalignment(lblLastName, HPos.RIGHT);
		
		root.add(txfLastName, 1, 1);
		GridPane.setHalignment(txfLastName, HPos.LEFT);
		
		root.add(lblIdNumber, 0, 2);
		GridPane.setHalignment(lblIdNumber, HPos.RIGHT);
		
		txfIdNumber.setEditable(true);
		root.add(txfIdNumber, 1, 2);
		GridPane.setHalignment(txfIdNumber, HPos.LEFT);	
		
		btnCreate.setDefaultButton(true);
		btnClose.setCancelButton(true);
	    tlpCloseCreate.getChildren().addAll(btnCreate, btnClose);
	    tlpCloseCreate.setAlignment(Pos.CENTER_RIGHT); 
		root.add(tlpCloseCreate, 1, 3);
		GridPane.setHalignment(tlpCloseCreate, HPos.RIGHT);
		
		methods.textfieldListener(txfIdNumber);
		methods.stageClose(btnClose, stage);
		methods.btnCreateTech(
				btnCreate, txfFirstName, txfLastName, 
				txfIdNumber, stage);
		return root;
	}
	
}
