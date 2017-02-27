/*
 * References 
 * 
 * Getting buttons in TableView
 * *	https://gist.github.com/jewelsea/3081826
 * Making parent window unclickable when child stage is open
 * *	http://stackoverflow.com/questions/24920429/
 * *	opening-secondary-window-in-java-fx-as-child-forces-overlap-anyway-to-overcom
 * Getting plain text into listview cells
 * *	http://java-buddy.blogspot.com/2013/05/implement-javafx-listview-for-custom.html
 * Getting technician properties within data object into table column
 * *	http://stackoverflow.com/questions/24769296/binding-nested-object-properties-to-tableview-in-javafx
 * Updating TableView with the showAndWait function
 * *	http://stackoverflow.com/questions/34590798/how-to-refresh-parent-window-after-closing-child-window-in-javafx
 * SQLite JDBC  reference
 * *	https://www.tutorialspoint.com/sqlite/sqlite_java.htm
 * SQLite prepared statements
 * *	http://javarevisited.blogspot.com/2012/03/why-use-preparedstatement-in-java-jdbc.html
 * *	http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
 * GUI Guideline
 * *	http://www.oracle.com/technetwork/java/index-136139.html
 * *	http://www.oracle.com/technetwork/java/hig-136903.html
 * Menus
 * *	http://o7planning.org/en/11125/javafx-menu-tutorial
 * *	http://java-buddy.blogspot.com/2012/02/javafx-20-add-submenu.html
 * *	http://www.java2s.com/Code/Java/JavaFX/SetacceleratorforMenu.htm
 * Get rid of extra column in table view
 * *	http://www.superglobals.net/remove-extra-column-tableview-javafx/
 * Get details of request into fields on click
 * *	http://stackoverflow.com/questions/26424769/javafx8-how-to-create-listener-for-selection-of-row-in-tableview
 * SplitPane Details
 * *	http://fxexperience.com/2011/06/splitpane-in-javafx-2-0/
 * Add listener to DatePicker
 * *	http://stackoverflow.com/questions/31460059/javafx-datepicker-how-to-update-date-in-a-second-datepicker-object
 * TilePane for buttons in forms
 * *	https://docs.oracle.com/javafx/2/api/javafx/scene/layout/TilePane.html
 * Boolean in database bit column
 * *	https://db.apache.org/ojb/docu/guides/jdbc-types.html
 * Save to file in filechooser
 * *	http://java-buddy.blogspot.com/2012/05/save-file-with-javafx-filechooser.html
 * *	http://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
 * *	https://docs.oracle.com/javase/8/javafx/api/javafx/stage/FileChooser.html
 * *	http://stackoverflow.com/questions/32318974/javafx-filechooser-initial-directory
 * Icons
 * *	https://icons8.com/
 * Toolbar
 * *	http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ToolBar.html
 * *	http://www.dummies.com/programming/java/using-the-toolbar-class-in-javafx/
 * *	https://blog.idrsolutions.com/2014/03/create-stacked-menus-in-javafx/
 * Alert Dialogs
 * *	http://code.makery.ch/blog/javafx-dialogs-official/
 * Scrolling Listview to selected item
 * *	http://stackoverflow.com/questions/11088612/javafx-select-item-in-listview
 */

package application;
	

import application.database.SQLiteJDBC;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{			
		// Initializes static classes
		SQLiteJDBC database = SQLiteJDBC.get();
		RequestFileChooser fileChooser = RequestFileChooser.get();			
		RequestList requestList = RequestList.get();
		TechnicianList techList = TechnicianList.get();
		
		// Nodes
		BorderPane root = new BorderPane();
		VBox vbxMenu = new VBox();
		MenuBar mnbMain = new MenuBar();
		ToolBar tlbMain = new ToolBar();
		TabPane tbpPane = new TabPane();
		SplitPane splRequest = new SplitPane();
		SplitPane splTech = new SplitPane();
		BorderPane bdpRequestDetails = new BorderPane();
		BorderPane bdpTechDetails = new BorderPane();
		GridPane grdRequestDetails = new GridPane();
		GridPane grdTechDetails = new GridPane();
		TilePane tlpRequest = new TilePane();
		TilePane tlpTech = new TilePane();
		
		// For tbpPane
		Tab tbRequest = new Tab("Requests");
		Tab tbTech = new Tab("Technicians");
		
		// For mnbMain
		Menu mnFile = new Menu("File");			
		Menu mnHelp = new Menu("Help");
		Menu submnNew = new Menu("New");
		MenuItem mniNewRequest = new MenuItem("Request");
		MenuItem mniNewTech = new MenuItem("Technician");
		Menu submnPrint = new Menu("Save");
		MenuItem mniPrintAllRequests = new MenuItem("All Requests");
		MenuItem mniPrintOpenRequests = new MenuItem("Open Requests");
		MenuItem mniPrintClosedRequests = new MenuItem("Completed Requests");
		MenuItem mniPrintAllTechs = new MenuItem("All Technicians");
		MenuItem mniExit = new MenuItem("Exit");
		MenuItem mniAbout = new MenuItem("About Request Tracker");
		
		// For tlbMain
		Button btnNewRequest = new Button();
		Button btnNewTech = new Button();
		Button btnPrintAllRequests = new Button();
		Button btnPrintOpenRequests = new Button();
		Button btnPrintClosedRequests = new Button();
		Button btnPrintAllTechs = new Button();
		
		// For tbRequest, tbTech
		TableView<Request> tbvRequest = new TableView<>(requestList.getAll());
		TableView<Technician> tbvTech = new TableView<>(techList.getAll());

		// For grdRequestDetails
		Label lblRequested = new Label("Date Requested");
		Label lblCompleted = new Label("Date Completed");
		Label lblDescription = new Label("Description");
		Label lblNotes = new Label("Notes");
		Label lblFirstName = new Label("First Name");
		Label lblLastName = new Label("Last Name");
		Label lblIdNumber = new Label("ID Number");
		DatePicker dtpRequested = new DatePicker();
		DatePicker dtpCompleted = new DatePicker();
		CheckBox chkCompleted = new CheckBox("Completed");
		TextArea txaDescription = new TextArea();
		TextArea txaNotes = new TextArea();
		Button btnSaveRequestChanges = new Button("Save Changes");
		Button btnDeleteRequest = new Button("Delete");
		
		// For grdTechDetails
		TextField txfFirstName = new TextField();
		TextField txfLastName = new TextField();
		TextField txfIdNumber = new TextField();
		Label lblTech = new Label("Choose Technician");
		ListView<Technician> lsvTech = new ListView<Technician>();
		Button btnSaveTechChanges = new Button("Save Changes");
		Button btnDeleteTech = new Button("Delete");
		
		InterfaceMethods methods = new InterfaceMethods();
		/*------------ Menu ------------*/
		// Sets keyboard shortcut and image for MenuItems
		mniNewRequest.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
		mniNewRequest.setGraphic(new ImageView(getClass().getResource("images/Add File-16.png").toExternalForm()));
		
		mniNewTech.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
		mniNewTech.setGraphic(new ImageView(getClass().getResource("images/Add File Filled-16.png").toExternalForm()));
		
		mniPrintAllRequests.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
		mniPrintAllRequests.setGraphic(new ImageView(getClass().getResource("images/Save-16.png").toExternalForm()));
		
		mniPrintAllTechs.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
		mniPrintAllTechs.setGraphic(new ImageView(getClass().getResource("images/Save Filled-16.png").toExternalForm()));
		
		mniPrintOpenRequests.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_DOWN));
		mniPrintOpenRequests.setGraphic(new ImageView(getClass().getResource("images/Open Book-16.png").toExternalForm()));
		
		mniPrintClosedRequests.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_DOWN, KeyCombination.SHIFT_DOWN));
		mniPrintClosedRequests.setGraphic(new ImageView(getClass().getResource("images/Generic Book File Type-16.png").toExternalForm()));
		
		mniAbout.setGraphic(new ImageView(getClass().getResource("images/Repository-16.png").toExternalForm()));
		
		mniExit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
		
		// Adds MenuItems to SubMenus
		submnNew.getItems().addAll(mniNewRequest, mniNewTech);
		submnPrint.getItems().addAll(mniPrintAllRequests, mniPrintAllTechs, mniPrintOpenRequests, mniPrintClosedRequests);			

		// Adds SubMenus and MenuItems to Menus
		mnFile.getItems().addAll(submnNew, submnPrint, new SeparatorMenuItem(), mniExit);
		mnHelp.getItems().add(mniAbout);
		
		// Adds Menus to MenuBar 
		mnbMain.getMenus().addAll(mnFile, mnHelp);
		/*------------- Menu End ------------*/
		
		/*------------ ToolBar ------------*/
		btnNewRequest.setTooltip(new Tooltip("New Request\tCtrl+N"));
		btnNewRequest.setGraphic(new ImageView(getClass().getResource("images/Add File-16.png").toExternalForm()));
		
		btnNewTech.setTooltip(new Tooltip("New Technician\tCtrl+Shift+N"));
		btnNewTech.setGraphic(new ImageView(getClass().getResource("images/Add File Filled-16.png").toExternalForm()));
		
		btnPrintAllRequests.setTooltip(new Tooltip("Save All Requests\tCtrl+S"));
		btnPrintAllRequests.setGraphic(new ImageView(getClass().getResource("images/Save-16.png").toExternalForm()));
		
		btnPrintAllTechs.setTooltip(new Tooltip("Save All Technicians\tCtrl+Shift+S"));
		btnPrintAllTechs.setGraphic(new ImageView(getClass().getResource("images/Save Filled-16.png").toExternalForm()));
		
		btnPrintOpenRequests.setTooltip(new Tooltip("Save All Open Requests\tAlt+S"));
		btnPrintOpenRequests.setGraphic(new ImageView(getClass().getResource("images/Open Book-16.png").toExternalForm()));
		
		btnPrintClosedRequests.setTooltip(new Tooltip("Save All Closed Requests\tAlt+Shift+S"));
		btnPrintClosedRequests.setGraphic(new ImageView(getClass().getResource("images/Generic Book File Type-16.png").toExternalForm()));
		
		tlbMain.getItems().addAll(new Separator(), btnNewRequest, btnNewTech, new Separator(), btnPrintAllRequests, btnPrintAllTechs, btnPrintOpenRequests, btnPrintClosedRequests, new Separator());
		/*------------ ToolBar End ------------*/
					
		/*------------Request Table Columns ------------*/
		TableColumn<Request, String> dateRequestedCol = new TableColumn<Request, String>("Date Requested");
		dateRequestedCol.setMinWidth(150);
		methods.dateRequestedCol(dateRequestedCol);
		
		TableColumn<Request, String> dateCompletedCol = new TableColumn<Request, String>("Date Completed");
		dateCompletedCol.setMinWidth(150);
		methods.dateCompletedCol(dateCompletedCol);

		TableColumn<Request, String> descriptionCol = new TableColumn<Request, String>("Description");
		descriptionCol.setMinWidth(100);
		methods.descriptionCol(descriptionCol);
		
		TableColumn<Request, String> techIdCol = new TableColumn<Request, String>("Technician ID");
		techIdCol.setMinWidth(150);
		methods.techIdCol(techIdCol, techList);
				
		TableColumn<Request, String> techNameCol = new TableColumn<Request, String>("Technician Name");
		techNameCol.setMinWidth(150);
		methods.techNameCol(techNameCol, techList);
		
		TableColumn<Request, String> notesCol = new TableColumn<Request, String>("Notes");
		notesCol.setMinWidth(100);
		methods.notesCol(notesCol);
		
		TableColumn<Request, String> isCompletedCol = new TableColumn<Request, String>("Completed");
		isCompletedCol.setMinWidth(100);
		methods.isCompletedCol(isCompletedCol);
		
		// Adds TableColumns to TableView
		tbvRequest.getColumns().add(dateRequestedCol);
		tbvRequest.getColumns().add(descriptionCol);
		tbvRequest.getColumns().add(techIdCol);
		tbvRequest.getColumns().add(techNameCol);
		tbvRequest.getColumns().add(notesCol);
		tbvRequest.getColumns().add(isCompletedCol);
		tbvRequest.getColumns().add(dateCompletedCol);
		tbvRequest.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		/*------------ Data Table Columns End ------------*/
		
		/*------------ Tech Table Columns ------------*/
		// Print firstName
		TableColumn<Technician, String> firstNameCol = new TableColumn<Technician, String>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		// Print lastName
		TableColumn<Technician, String> lastNameCol = new TableColumn<Technician, String>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		// Print idNumber
		TableColumn<Technician, Double> idNumberCol = new TableColumn<Technician, Double>("ID Number");
		idNumberCol.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
		
		// Adds TableColumns to TableView
		tbvTech.getColumns().add(firstNameCol);
		tbvTech.getColumns().add(lastNameCol);
		tbvTech.getColumns().add(idNumberCol);
		tbvTech.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		/*------------ Tech Table Columns End ------------*/
		
		/*------------ Request Show Details ------------*/
		grdRequestDetails.setPadding(new Insets(20));
		grdRequestDetails.setVgap(15);
		
		tlpRequest.setHgap(2);
		tlpRequest.setPrefColumns(1);
		
		lblRequested.setWrapText(true);
		grdRequestDetails.add(lblRequested, 0, 0);
		GridPane.setHalignment(lblRequested, HPos.LEFT);
		
		grdRequestDetails.add(dtpRequested, 0, 1);
		GridPane.setHalignment(dtpRequested, HPos.LEFT);
		
		grdRequestDetails.add(lblCompleted, 0, 2);
		GridPane.setHalignment(lblCompleted, HPos.LEFT);
		
		dtpCompleted.setDisable(true);
		grdRequestDetails.add(dtpCompleted, 0, 3);
		GridPane.setHalignment(dtpCompleted, HPos.LEFT);
		
		grdRequestDetails.add(chkCompleted, 0, 4);
		GridPane.setHalignment(chkCompleted, HPos.LEFT);
		
		grdRequestDetails.add(lblDescription, 0, 5);
		GridPane.setHalignment(lblDescription, HPos.LEFT);
		
		txaDescription.setWrapText(true);
		txaDescription.setPrefColumnCount(10);
		txaDescription.setPrefRowCount(7);
		grdRequestDetails.add(txaDescription, 0, 6);
		GridPane.setHalignment(txaDescription, HPos.LEFT);
		
		lblTech.setWrapText(true);
		grdRequestDetails.add(lblTech, 0, 7);
		GridPane.setHalignment(lblTech, HPos.LEFT);
		
		lsvTech.setPrefHeight(200);
		methods.techListView(lsvTech);
		grdRequestDetails.add(lsvTech, 0, 8);
		GridPane.setHalignment(lsvTech, HPos.LEFT);	
		
		grdRequestDetails.add(lblNotes, 0, 9);
		GridPane.setHalignment(lblNotes, HPos.LEFT);
		
		txaNotes.setWrapText(true);
		txaNotes.setPrefColumnCount(10);
		txaNotes.setPrefRowCount(7);
		grdRequestDetails.add(txaNotes, 0, 10);
		GridPane.setHalignment(txaNotes, HPos.LEFT);
		
		btnDeleteRequest.setVisible(false);
		btnDeleteRequest.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		btnSaveRequestChanges.setVisible(false);
		btnSaveRequestChanges.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		tlpRequest.getChildren().addAll(btnSaveRequestChanges, btnDeleteRequest );
		tlpRequest.setTileAlignment(Pos.CENTER_RIGHT);
		tlpRequest.setAlignment(Pos.CENTER_RIGHT);
		grdRequestDetails.add(tlpRequest, 0, 11);
		GridPane.setHalignment(tlpRequest, HPos.RIGHT);		
		/*------------ Data Show Details End ------------*/
		
		/*------------ Tech Show Details ------------*/
		grdTechDetails.setPadding(new Insets(20));
		grdTechDetails.setHgap(25);
		grdTechDetails.setVgap(15);
		
		tlpTech.setHgap(2);
		tlpTech.setPrefColumns(1);
		
		grdTechDetails.add(lblFirstName, 0, 0);
		GridPane.setHalignment(lblFirstName, HPos.LEFT);
		
		grdTechDetails.add(txfFirstName, 0, 1);
		GridPane.setHalignment(txfFirstName, HPos.LEFT);
		
		grdTechDetails.add(lblLastName, 0, 2);
		GridPane.setHalignment(lblLastName, HPos.LEFT);
		
		grdTechDetails.add(txfLastName, 0, 3);
		GridPane.setHalignment(txfLastName, HPos.LEFT);
		
		grdTechDetails.add(lblIdNumber, 0, 4);
		GridPane.setHalignment(lblIdNumber, HPos.LEFT);
		
		txfIdNumber.setEditable(true);
		grdTechDetails.add(txfIdNumber, 0, 5);
		GridPane.setHalignment(txfIdNumber, HPos.LEFT);	
		
		btnDeleteTech.setVisible(false);
		btnDeleteTech.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		btnSaveTechChanges.setVisible(false);
		btnSaveTechChanges.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		tlpTech.getChildren().addAll(btnSaveTechChanges, btnDeleteTech);
		tlpTech.setTileAlignment(Pos.CENTER_RIGHT);
		tlpTech.setAlignment(Pos.CENTER_RIGHT);
		grdTechDetails.add(tlpTech, 0, 6);
		GridPane.setHalignment(tlpTech, HPos.RIGHT);
		/*------------ Tech Show Details End ------------*/
			
		/*------------ Add Nodes to Root ------------*/
		// Adds GridPanes to BorderPanes
		bdpRequestDetails.setCenter(grdRequestDetails);
		bdpTechDetails.setCenter(grdTechDetails);
		
		// Adds TableViews and BorderPanes to SplitPanes 
		splRequest.getItems().addAll(tbvRequest, bdpRequestDetails);
		splTech.getItems().addAll(tbvTech, bdpTechDetails);
		splRequest.setDividerPositions(0.65);
		splTech.setDividerPositions(0.65);
		
		SplitPane.setResizableWithParent(bdpRequestDetails, Boolean.FALSE);
		SplitPane.setResizableWithParent(bdpTechDetails, Boolean.FALSE);
		
		// Adds SplitPanes to Tabs
		tbRequest.setContent(splRequest);
		tbRequest.setClosable(false);
		tbTech.setContent(splTech);
		tbTech.setClosable(false);
		
		// Adds Tabs to TabPane
		tbpPane.getTabs().addAll(tbRequest, tbTech);
		
		vbxMenu.getChildren().addAll(mnbMain, tlbMain);
		
		// Adds MenuBar and TabPane to BorderPane
		root.setTop(vbxMenu);
		root.setCenter(tbpPane);
		/*------------ Add Nodes to Root End ------------*/
		
		/*------------ Interface Action Code ------------*/
		methods.mniNewRequest(mniNewRequest, primaryStage, tbvRequest, requestList);
		methods.mniNewRequest(btnNewRequest, primaryStage, tbvRequest, requestList);
		methods.mniNewTech(mniNewTech, primaryStage, tbvTech, lsvTech, techList);
		methods.mniNewTech(btnNewTech, primaryStage, tbvTech, lsvTech, techList);
		methods.mniPrintAllRequests(mniPrintAllRequests, fileChooser, primaryStage, requestList);
		methods.mniPrintAllRequests(btnPrintAllRequests, fileChooser, primaryStage, requestList);
		methods.mniPrintOpenRequests(mniPrintOpenRequests, fileChooser, primaryStage, requestList);
		methods.mniPrintOpenRequests(btnPrintOpenRequests, fileChooser, primaryStage, requestList);
		methods.mniPrintAllTechs(mniPrintAllTechs, fileChooser, primaryStage, techList);
		methods.mniPrintAllTechs(btnPrintAllTechs, fileChooser, primaryStage, techList);
		methods.mniPrintClosedRequests(mniPrintClosedRequests, fileChooser, primaryStage, requestList);
		methods.mniPrintClosedRequests(btnPrintClosedRequests, fileChooser, primaryStage, requestList);
		methods.mniAbout(mniAbout, primaryStage);
		methods.mniExit(mniExit, database);
		methods.btnSaveRequestChanges(
				btnSaveRequestChanges, 
				tbvRequest, lsvTech, 
				dtpRequested, dtpCompleted, 
				txaDescription, txaNotes, 
				chkCompleted, requestList, techList);
		methods.btnSaveTechChanges(
				btnSaveTechChanges, txfFirstName,
				txfLastName, txfIdNumber, 
				tbvRequest, tbvTech, lsvTech, 
				requestList, techList);
		methods.btnDeleteRequest(
				btnDeleteRequest, tbvRequest, 
				lsvTech, dtpRequested, 
				dtpCompleted, txaDescription,
				txaNotes, chkCompleted, requestList);
		methods.btnDeleteTech(
				btnDeleteTech, txfFirstName, 
				txfLastName, txfIdNumber, 
				tbvRequest, tbvTech, lsvTech, 
				requestList, techList);
		methods.checkboxListener(chkCompleted, dtpCompleted);
		methods.textfieldListener(txfIdNumber);
		methods.requestTableListener(
				tbvRequest, dtpRequested, 
				dtpCompleted, txaDescription,
				txaNotes, lsvTech, chkCompleted,
				btnSaveRequestChanges, btnDeleteRequest, techList);
		methods.techTableListener(
				tbvTech, txfFirstName,
				txfLastName, txfIdNumber,
				btnSaveTechChanges, btnDeleteTech);
		methods.onCloseRequest(primaryStage, database);
		/*------------ Interface Action Code End ------------*/
		Scene scene = new Scene(root,800,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Request Tracker");
		primaryStage.setMaximized(true);
		primaryStage.getIcons().add(new Image(getClass().getResource("images/Repository-16.png").toExternalForm()));
		primaryStage.show();
	} 
		
	public static void main(String[] args) {
		launch(args);
	}
}

