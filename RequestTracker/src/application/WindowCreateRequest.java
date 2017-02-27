package application;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowCreateRequest {
	private Stage stage;
	private Scene scene;
	
	public WindowCreateRequest(Stage primaryStage) throws Exception{
		stage = new Stage();
		scene = new Scene(setUpScene(),450,670);	
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Create Request");
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
		
		Label lblRequested = new Label("Date Requested");
		Label lblCompleted = new Label("Date Completed");
		Label lblDescription = new Label("Description");
		Label lblTech = new Label("Choose Technician");
		Label lblNotes = new Label("Notes");
		DatePicker dtpRequested = new DatePicker();
		DatePicker dtpCompleted = new DatePicker();
		CheckBox chkCompleted = new CheckBox("Completed");
		TextArea txaDescription = new TextArea();
		TextArea txaNotes = new TextArea();
		ListView<Technician> lsvTech = new ListView<Technician>(TechnicianList.get().getAll());
		Button btnClose = new Button("Close");
		Button btnCreate = new Button("Create");
		
		InterfaceMethods methods = new InterfaceMethods();
		
		root.add(lblRequested, 0, 0);
		GridPane.setHalignment(lblRequested, HPos.RIGHT);
		
		root.add(dtpRequested, 1, 0);
		GridPane.setHalignment(dtpRequested, HPos.LEFT);
		
		root.add(lblCompleted, 0, 1);
		GridPane.setHalignment(lblCompleted, HPos.RIGHT);
		
		dtpCompleted.setDisable(true);
		root.add(dtpCompleted, 1, 1);
		GridPane.setHalignment(dtpCompleted, HPos.LEFT);
		
		root.add(chkCompleted, 1, 2);
		GridPane.setHalignment(chkCompleted, HPos.LEFT);
		
		root.add(lblDescription, 0, 3);
		GridPane.setHalignment(lblDescription, HPos.RIGHT);
		GridPane.setValignment(lblDescription, VPos.TOP);
		
		txaDescription.setWrapText(true);
		txaDescription.setPrefColumnCount(10);
		txaDescription.setPrefRowCount(5);
		root.add(txaDescription, 1, 3);
		GridPane.setHalignment(txaDescription, HPos.LEFT);
		
		root.add(lblTech, 0, 4);
		GridPane.setHalignment(lblTech, HPos.RIGHT);
		GridPane.setValignment(lblTech, VPos.TOP);
		
		lsvTech.setPrefHeight(100);
		root.add(lsvTech, 1, 4);
		GridPane.setHalignment(lsvTech, HPos.LEFT);
		
		root.add(lblNotes, 0, 5);
		GridPane.setHalignment(lblNotes, HPos.RIGHT);
		GridPane.setValignment(lblNotes, VPos.TOP);
		
		txaNotes.setWrapText(true);
		txaNotes.setPrefColumnCount(10);
		txaNotes.setPrefRowCount(5);
		root.add(txaNotes, 1, 5);
		GridPane.setHalignment(txaNotes, HPos.LEFT);
					
		btnCreate.setDefaultButton(true);
		btnClose.setCancelButton(true);
		tlpCloseCreate.getChildren().addAll(btnCreate, btnClose);
	    tlpCloseCreate.setAlignment(Pos.CENTER_RIGHT); 
		root.add(tlpCloseCreate, 1, 6);
		GridPane.setHalignment(tlpCloseCreate, HPos.RIGHT);
		
		methods.checkboxListener(chkCompleted, dtpCompleted);
		methods.techListView(lsvTech);
		methods.stageClose(btnClose, stage);
		methods.btnCreateRequest(
				btnCreate, dtpRequested, dtpCompleted, 
				chkCompleted, txaDescription, txaNotes, 
				lsvTech, stage);
		
		return root;
	}
}
