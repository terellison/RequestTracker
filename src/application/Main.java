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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * The Class Main.
 * 
 * This is where the entire program starts. Its main components are
 * the menu bar, toolbar, tabpane, and splitpanes.
 * 
 * Focuses of the placement of nodes and their graphics. All
 * action methods are separated into another class that is called
 * frequently in this class.
 * 
 * @author Chaz-Rae L. Moncrieffe
 * @since 3/1/2017
 * @see InterfaceMethods
 */
/*
 * Table of Contents
 * 	1.0 Variables
 * 		1.1 Initializes static instances 
 * 		1.2 Major Nodes
 *	 	1.3 Tabs: TabPane tbpPane
 *		1.4 Menus & MenuItems: MenuBar mnbMain
 *		1.5 Buttons: ToolBar tlbMain
 *		1.6 TableViews Request & Tech:  SplitPanes splRequest & splTech
 *		1.7 TableColumns: TableView tbvRequest
 *		1.8 TableColumns: TableView tbvTech
 *		1.9 Scene Controls: GridPane grdRequestDetails
 *		1.10 Scene Controls: GridPane grdTechDetails
 *		1.11 InterfaceMethods initialization
 *	2.0 Menu
 *		2.1 MenuItem New Request
 *		2.2 MenuItem New Tech
 *		2.3 MenuItem Print All Requests
 *		2.4 MenuItem Print All Techs
 *		2.5 MenuItem Print Open Requests
 *		2.6 MenuItem Print Closed Requests
 *		2.7 MenuItem Delete All Requests
 *		2.8 MenuItem Delete All Techs
 *		2.9 MenuItem About
 *		2.10 MenuItem Exit
 *		2.11 MenuItem Export
 *		2.12 Add MenuItems to SubMenus
 *		2.13 Add SubMenus and MenuItems to Menus
 *		2.14 Add Menus to MenuBar
 *	3.0 Toolbar
 *		3.1 New Request
 *		3.2 New Tech
 *		3.3 Print All Requests
 *		3.4 Print All Techs
 *		3.5 Print Open Requests
 *		3.6 Print Closed Requests
 *		3.7 Delete All Requests
 *		3.8 Delete All Techs
 *		3.9 Add ToolBar Buttons to ToolBar
 *	4.0 Request Table Columns
 *		4.1 Request Id 
 *		4.2 Date Requested
 *		4.3 Date Completed
 *		4.4 Description 
 *		4.5 Tech Id
 *		4.6 Tech Name
 *		4.7 Notes
 *		4.8 Is Completed
 *		4.9 Adds TableColumns to TableView
 *	5.0 Tech Table Columns
 *		5.1 First Name
 *		5.2 Last Name
 *		5.3 ID Number
 *		5.4 Adds TableColumns to TableView
 *	6.0 Request Details
 *		6.1 GridPane Request Settings
 *		6.2 Label Requested	
 *		6.3 DatePicker Requested
 *		6.4 Label Completed
 *		6.5 DatePicker Completed
 *		6.6 CheckBox Completed
 *		6.7 Label Description
 *		6.8 TextArea Description
 *		6.9 Label Tech
 *		6.10 ListView Tech
 *		6.11 Label Notes
 *		6.12 TextArea Notes
 *		6.13 Button Delete Request
 *		6.14 Button Save Request Changes
 *		6.15 TilePane Request
 *	7.0 Tech Details
 *		7.1 GridPane Tech Settings
 *		7.2 Label First Name
 *		7.3 TextField First Name
 *		7.4 Label Last Name
 *		7.5 TextField Last Name
 *		7.6 Label ID Number
 *		7.7 TextField ID Number
 *		7.8 Button Delete Tech
 *		7.9 Button Save Tech Changes
 *		7.10 TilePane Tech
 *	8.0 Add Nodes to Root
 *		8.1 Add GridPanes to BorderPanes
 *		8.2 Add TableViews and BorderPanes to SplitPanes
 *		8.3 Add SplitPanes to Tabs
 *		8.4 Add Tabs to TabPane
 *		8.5 Add MenuBar and ToolBar to VBox
 *		8.6 Add VBox and TabPane to BorderPane
 *	9.0 Interface Actions and Listeners
 *		9.1 MenuItems and ToolBar Buttons
 *		9.2 Form Buttons
 *		9.3 Listeners
 *		9.4 Stage Close
 *	10.0 Primary Stage
 *		10.1 Add BorderPane root to Scene scene
 *		10.2 Add Scene scene to Stage primaryStage
 *		10.3 Additional Attributes
 *		10.4 Show Stage primaryStage
 */
public class Main extends Application {
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception{			
		/************ 1.0 Variables ************/
		// 1.1 Initializes static instances 
		SQLiteJDBC database = SQLiteJDBC.get();
		RequestFileChooser fileChooser = RequestFileChooser.get();			
		RequestList requestList = RequestList.get();
		TechnicianList techList = TechnicianList.get();
		
		// 1.2 Major Nodes
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
		
		// 1.3 Tabs: TabPane tbpPane
		Tab tbRequest = new Tab("Requests");
		Tab tbTech = new Tab("Technicians");
		
		// 1.4 Menus & MenuItems: MenuBar mnbMain
		Menu mnFile = new Menu("File");			
		Menu mnHelp = new Menu("Help");
		Menu mnEdit = new Menu("Edit");
		Menu submnNew = new Menu("New");
		Menu submnPrint = new Menu("Save");
		Menu submnExport = new Menu("Export");
		Menu submnDelete = new Menu("Delete");
		MenuItem mniNewRequest = new MenuItem("Request");
		MenuItem mniNewTech = new MenuItem("Technician");
		MenuItem mniPrintAllRequests = new MenuItem("All Requests");
		MenuItem mniPrintOpenRequests = new MenuItem("Open Requests");
		MenuItem mniPrintClosedRequests = new MenuItem("Completed Requests");
		MenuItem mniPrintAllTechs = new MenuItem("All Technicians");
		MenuItem mniExport = new MenuItem("Database to SQL");
		MenuItem mniExit = new MenuItem("Exit");
		MenuItem mniDeleteAllRequests = new MenuItem("All Requests");
		MenuItem mniDeleteAllTechs = new MenuItem("All Technicians");
		MenuItem mniAbout = new MenuItem("About Request Tracker");
		
		// 1.5 Buttons: ToolBar tlbMain
		Button btnNewRequest = new Button();
		Button btnNewTech = new Button();
		Button btnPrintAllRequests = new Button();
		Button btnPrintOpenRequests = new Button();
		Button btnPrintClosedRequests = new Button();
		Button btnPrintAllTechs = new Button();
		Button btnDeleteAllRequests = new Button();
		Button btnDeleteAllTechs = new Button();
		
		// 1.6 TableViews Request & Tech:  SplitPanes splRequest & splTech
		TableView<Request> tbvRequest = new TableView<>(requestList.getAll());
		TableView<Technician> tbvTech = new TableView<>(techList.getAll());
		
		// 1.7 TableColumns: TableView tbvRequest
		TableColumn<Request, String> idCol = new TableColumn<Request, String>("Request Id");
		TableColumn<Request, String> dateRequestedCol = new TableColumn<Request, String>("Date Requested");
		TableColumn<Request, String> dateCompletedCol = new TableColumn<Request, String>("Date Completed");
		TableColumn<Request, String> descriptionCol = new TableColumn<Request, String>("Description");
		TableColumn<Request, String> techIdCol = new TableColumn<Request, String>("Technician ID");
		TableColumn<Request, String> techNameCol = new TableColumn<Request, String>("Technician Name");
		TableColumn<Request, String> notesCol = new TableColumn<Request, String>("Notes");
		TableColumn<Request, String> isCompletedCol = new TableColumn<Request, String>("Completed");
		
		// 1.8 TableColumns: TableView tbvTech
		TableColumn<Technician, String> firstNameCol = new TableColumn<Technician, String>("First Name");
		TableColumn<Technician, String> lastNameCol = new TableColumn<Technician, String>("Last Name");
		TableColumn<Technician, String> idNumberCol = new TableColumn<Technician, String>("ID Number");
		

		// 1.9 Scene Controls: GridPane grdRequestDetails
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
		ListView<Technician> lsvTech = new ListView<Technician>();
		Button btnSaveRequestChanges = new Button("Save Changes");
		Button btnDeleteRequest = new Button("Delete");
		
		// 1.10 Scene Controls: GridPane grdTechDetails
		Label lblFirstName = new Label("First Name");
		Label lblLastName = new Label("Last Name");
		Label lblIdNumber = new Label("ID Number");
		TextField txfFirstName = new TextField();
		TextField txfLastName = new TextField();
		TextField txfIdNumber = new TextField();
		Button btnSaveTechChanges = new Button("Save Changes");
		Button btnDeleteTech = new Button("Delete");
		
		// 1.11 InterfaceMethods initialization
		InterfaceMethods methods = new InterfaceMethods();
		
		/************ 2.0 Menu ************/
		// 2.1 MenuItem New Request
		mniNewRequest.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
		mniNewRequest.setGraphic(new ImageView(getClass().getResource("images/Add File-16.png").toExternalForm()));
		
		// 2.2 MenuItem New Tech
		mniNewTech.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
		mniNewTech.setGraphic(new ImageView(getClass().getResource("images/Add File Filled-16.png").toExternalForm()));
		
		// 2.3 MenuItem Print All Requests
		mniPrintAllRequests.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
		mniPrintAllRequests.setGraphic(new ImageView(getClass().getResource("images/Save-16.png").toExternalForm()));
		
		// 2.4 MenuItem Print All Techs
		mniPrintAllTechs.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
		mniPrintAllTechs.setGraphic(new ImageView(getClass().getResource("images/Save Filled-16.png").toExternalForm()));
		
		// 2.5 MenuItem Print Open Requests
		mniPrintOpenRequests.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_DOWN));
		mniPrintOpenRequests.setGraphic(new ImageView(getClass().getResource("images/Open Book-16.png").toExternalForm()));
		
		// 2.6 MenuItem Print Closed Requests
		mniPrintClosedRequests.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_DOWN, KeyCombination.SHIFT_DOWN));
		mniPrintClosedRequests.setGraphic(new ImageView(getClass().getResource("images/Generic Book File Type-16.png").toExternalForm()));
		
		// 2.7 MenuItem Delete All Requests
		mniDeleteAllRequests.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
		mniDeleteAllRequests.setGraphic(new ImageView(getClass().getResource("images/Delete-16.png").toExternalForm()));
		
		// 2.8 MenuItem Delete All Techs
		mniDeleteAllTechs.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
		mniDeleteAllTechs.setGraphic(new ImageView(getClass().getResource("images/Delete Filled-16.png").toExternalForm()));
		
		// 2.9 MenuItem About
		mniAbout.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
		mniAbout.setGraphic(new ImageView(getClass().getResource("images/Repository-16.png").toExternalForm()));
		
		// 2.10 MenuItem Exit
		mniExit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
		
		// 2.11 MenuItem Export
		mniExport.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
		mniExport.setGraphic(new ImageView(getClass().getResource("images/Export-16.png").toExternalForm()));
		
		// 2.12 Add MenuItems to SubMenus
		submnNew.getItems().addAll(mniNewRequest, mniNewTech);
		submnPrint.getItems().addAll(mniPrintAllRequests, mniPrintAllTechs, mniPrintOpenRequests, mniPrintClosedRequests);
		submnExport.getItems().add(mniExport);
		submnDelete.getItems().addAll(mniDeleteAllRequests, mniDeleteAllTechs);

		// 2.13 Add SubMenus and MenuItems to Menus
		mnFile.getItems().addAll(submnNew, submnPrint, new SeparatorMenuItem(), submnExport,  new SeparatorMenuItem(), mniExit);
		mnEdit.getItems().add(submnDelete);
		mnHelp.getItems().add(mniAbout);
		
		// 2.14 Add Menus to MenuBar 
		mnbMain.getMenus().addAll(mnFile, mnEdit, mnHelp);
		
		/************ 3.0 ToolBar ************/
		// 3.1 New Request
		btnNewRequest.setTooltip(new Tooltip("New Request\tCtrl+N"));
		btnNewRequest.setGraphic(new ImageView(getClass().getResource("images/Add File-16.png").toExternalForm()));
		
		// 3.2 New Tech
		btnNewTech.setTooltip(new Tooltip("New Technician\tCtrl+Shift+N"));
		btnNewTech.setGraphic(new ImageView(getClass().getResource("images/Add File Filled-16.png").toExternalForm()));
		
		// 3.3 Print All Requests
		btnPrintAllRequests.setTooltip(new Tooltip("Save All Requests\tCtrl+S"));
		btnPrintAllRequests.setGraphic(new ImageView(getClass().getResource("images/Save-16.png").toExternalForm()));
		
		// 3.4 Print All Techs
		btnPrintAllTechs.setTooltip(new Tooltip("Save All Technicians\tCtrl+Shift+S"));
		btnPrintAllTechs.setGraphic(new ImageView(getClass().getResource("images/Save Filled-16.png").toExternalForm()));
		
		// 3.5 Print Open Requests
		btnPrintOpenRequests.setTooltip(new Tooltip("Save All Open Requests\tAlt+S"));
		btnPrintOpenRequests.setGraphic(new ImageView(getClass().getResource("images/Open Book-16.png").toExternalForm()));
		
		// 3.6 Print Closed Requests
		btnPrintClosedRequests.setTooltip(new Tooltip("Save All Closed Requests\tAlt+Shift+S"));
		btnPrintClosedRequests.setGraphic(new ImageView(getClass().getResource("images/Generic Book File Type-16.png").toExternalForm()));
		
		// 3.7 Delete All Requests
		btnDeleteAllRequests.setTooltip(new Tooltip("Delete All Requests\t Ctrl+D"));
		btnDeleteAllRequests.setGraphic(new ImageView(getClass().getResource("images/Delete-16.png").toExternalForm()));
		
		// 3.8 Delete All Techs
		btnDeleteAllTechs.setTooltip(new Tooltip("Delete All Technicians\tCtrl+Shift+D"));
		btnDeleteAllTechs.setGraphic(new ImageView(getClass().getResource("images/Delete Filled-16.png").toExternalForm()));
		
		// 3.9 Add ToolBar Buttons to ToolBar
		tlbMain.getItems().addAll(
				new Separator(), btnNewRequest, btnNewTech, 
				new Separator(), btnPrintAllRequests, btnPrintAllTechs, btnPrintOpenRequests, btnPrintClosedRequests, 
				new Separator(), btnDeleteAllRequests, btnDeleteAllTechs, new Separator());
					
		/************ 4.0 Request Table Columns ************/
		// 4.1 Request Id 
		methods.idColumn(idCol);
		
		// 4.2 Date Requested
		methods.dateRequestedCol(dateRequestedCol);
		
		// 4.3 Date Completed
		methods.dateCompletedCol(dateCompletedCol);

		// 4.4 Description 
		methods.descriptionCol(descriptionCol);
		
		// 4.5 Tech Id
		methods.techIdCol(techIdCol, techList);
				
		// 4.6 Tech Name
		methods.techNameCol(techNameCol, techList);
		
		// 4.7 Notes
		methods.notesCol(notesCol);
		
		// 4.8 Is Completed
		methods.isCompletedCol(isCompletedCol);
		
		// 4.9 Adds TableColumns to TableView
		methods.addRequestColumns(
				tbvRequest, idCol, dateRequestedCol, 
				descriptionCol, techIdCol, techNameCol,
				notesCol, isCompletedCol, dateCompletedCol);
		
		/************ 5.0 Tech Table Columns ************/
		// 5.1 First Name
		methods.firstName(firstNameCol);
		
		// 5.2 Last Name
		methods.lastName(lastNameCol);
		
		// 5.3 ID Number
		methods.idNumber(idNumberCol);
		
		// 5.4 Adds TableColumns to TableView
		methods.addTechColumns(tbvTech, firstNameCol, lastNameCol, idNumberCol);
		
		/************ 6.0 Request Details ************/
		// 6.1 GridPane Request Settings
		grdRequestDetails.setPadding(new Insets(20));
		grdRequestDetails.setVgap(15);
		
		// 6.2 Label Requested
		lblRequested.setWrapText(true);
		grdRequestDetails.add(lblRequested, 0, 0);
		GridPane.setHalignment(lblRequested, HPos.LEFT);
		
		// 6.3 DatePicker Requested
		grdRequestDetails.add(dtpRequested, 0, 1);
		GridPane.setHalignment(dtpRequested, HPos.LEFT);
		
		// 6.4 Label Completed
		grdRequestDetails.add(lblCompleted, 0, 2);
		GridPane.setHalignment(lblCompleted, HPos.LEFT);
		
		// 6.5 DatePicker Completed
		dtpCompleted.setDisable(true);
		grdRequestDetails.add(dtpCompleted, 0, 3);
		GridPane.setHalignment(dtpCompleted, HPos.LEFT);
		
		// 6.6 CheckBox Completed
		grdRequestDetails.add(chkCompleted, 0, 4);
		GridPane.setHalignment(chkCompleted, HPos.LEFT);
		
		// 6.7 Label Description
		grdRequestDetails.add(lblDescription, 0, 5);
		GridPane.setHalignment(lblDescription, HPos.LEFT);
		
		// 6.8 TextArea Description
		txaDescription.setWrapText(true);
		txaDescription.setPrefColumnCount(10);
		txaDescription.setPrefRowCount(7);
		grdRequestDetails.add(txaDescription, 0, 6);
		GridPane.setHalignment(txaDescription, HPos.LEFT);
		
		// 6.9 Label Tech
		lblTech.setWrapText(true);
		grdRequestDetails.add(lblTech, 0, 7);
		GridPane.setHalignment(lblTech, HPos.LEFT);
		
		// 6.10 ListView Tech
		lsvTech.setPrefHeight(200);
		methods.techListView(lsvTech);
		grdRequestDetails.add(lsvTech, 0, 8);
		GridPane.setHalignment(lsvTech, HPos.LEFT);	
		
		// 6.11 Label Notes
		grdRequestDetails.add(lblNotes, 0, 9);
		GridPane.setHalignment(lblNotes, HPos.LEFT);
		
		// 6.12 TextArea Notes
		txaNotes.setWrapText(true);
		txaNotes.setPrefColumnCount(10);
		txaNotes.setPrefRowCount(7);
		grdRequestDetails.add(txaNotes, 0, 10);
		GridPane.setHalignment(txaNotes, HPos.LEFT);
		
		// 6.13 Button Delete Request
		btnDeleteRequest.setVisible(false);
		btnDeleteRequest.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		// 6.14 Button Save Request Changes
		btnSaveRequestChanges.setVisible(false);
		btnSaveRequestChanges.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		// 6.15 TilePane Request
		tlpRequest.setHgap(2);
		tlpRequest.setPrefColumns(2);
		tlpRequest.getChildren().addAll(btnSaveRequestChanges, btnDeleteRequest );
		tlpRequest.setTileAlignment(Pos.CENTER_RIGHT);
		tlpRequest.setAlignment(Pos.CENTER_RIGHT);
		grdRequestDetails.add(tlpRequest, 0, 11);
		GridPane.setHalignment(tlpRequest, HPos.RIGHT);	
		
		/************ 7.0 Tech Details ************/ 
		// 7.1 GridPane Tech Settings
		grdTechDetails.setPadding(new Insets(20));
		grdTechDetails.setHgap(25);
		grdTechDetails.setVgap(15);
		
		// 7.2 Label First Name
		grdTechDetails.add(lblFirstName, 0, 0);
		GridPane.setHalignment(lblFirstName, HPos.LEFT);
		
		// 7.3 TextField First Name
		grdTechDetails.add(txfFirstName, 0, 1);
		GridPane.setHalignment(txfFirstName, HPos.LEFT);
		
		// 7.4 Label Last Name
		grdTechDetails.add(lblLastName, 0, 2);
		GridPane.setHalignment(lblLastName, HPos.LEFT);
		
		// 7.5 TextField Last Name
		grdTechDetails.add(txfLastName, 0, 3);
		GridPane.setHalignment(txfLastName, HPos.LEFT);
		
		// 7.6 Label ID Number
		grdTechDetails.add(lblIdNumber, 0, 4);
		GridPane.setHalignment(lblIdNumber, HPos.LEFT);
		
		// 7.7 TextField ID Number
		txfIdNumber.setEditable(true);
		grdTechDetails.add(txfIdNumber, 0, 5);
		GridPane.setHalignment(txfIdNumber, HPos.LEFT);	
		
		// 7.8 Button Delete Tech
		btnDeleteTech.setVisible(false);
		btnDeleteTech.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		// 7.9 Button Save Tech Changes
		btnSaveTechChanges.setVisible(false);
		btnSaveTechChanges.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		// 7.10 TilePane Tech
		tlpTech.setHgap(2);
		tlpTech.setPrefColumns(2);
		tlpTech.getChildren().addAll(btnSaveTechChanges, btnDeleteTech);
		tlpTech.setTileAlignment(Pos.CENTER_RIGHT);
		tlpTech.setAlignment(Pos.CENTER_RIGHT);
		grdTechDetails.add(tlpTech, 0, 6);
		GridPane.setHalignment(tlpTech, HPos.RIGHT);
			
		/************ 8.0 Add Nodes to Root ************/
		// 8.1 Add GridPanes to BorderPanes
		bdpRequestDetails.setCenter(grdRequestDetails);
		bdpTechDetails.setCenter(grdTechDetails);
		
		// 8.2 Add TableViews and BorderPanes to SplitPanes 
		splRequest.getItems().addAll(tbvRequest, bdpRequestDetails);
		splTech.getItems().addAll(tbvTech, bdpTechDetails);
		splRequest.setDividerPositions(0.65);
		splTech.setDividerPositions(0.65);
		
		SplitPane.setResizableWithParent(bdpRequestDetails, Boolean.FALSE);
		SplitPane.setResizableWithParent(bdpTechDetails, Boolean.FALSE);
		
		// 8.3 Add SplitPanes to Tabs
		tbRequest.setContent(splRequest);
		tbRequest.setClosable(false);
		tbTech.setContent(splTech);
		tbTech.setClosable(false);
		
		// 8.4 Add Tabs to TabPane
		tbpPane.getTabs().addAll(tbRequest, tbTech);
		
		// 8.5 Add MenuBar and ToolBar to VBox
		vbxMenu.getChildren().addAll(mnbMain, tlbMain);
		
		// 8.6 Add VBox and TabPane to BorderPane
		root.setTop(vbxMenu);
		root.setCenter(tbpPane);
		
		/************ 9.0 Interface Actions and Listeners ************/
		// 9.1 MenuItems and ToolBar Buttons
		methods.objNewRequest(mniNewRequest, primaryStage, tbvRequest, requestList);
		methods.objNewRequest(btnNewRequest, primaryStage, tbvRequest, requestList);
		
		methods.objNewTech(mniNewTech, primaryStage, tbvTech, lsvTech, techList);
		methods.objNewTech(btnNewTech, primaryStage, tbvTech, lsvTech, techList);
		
		methods.objPrintAllRequests(mniPrintAllRequests, fileChooser, primaryStage, requestList);
		methods.objPrintAllRequests(btnPrintAllRequests, fileChooser, primaryStage, requestList);
		
		methods.objPrintOpenRequests(mniPrintOpenRequests, fileChooser, primaryStage, requestList);
		methods.objPrintOpenRequests(btnPrintOpenRequests, fileChooser, primaryStage, requestList);
		
		methods.objPrintAllTechs(mniPrintAllTechs, fileChooser, primaryStage, techList);
		methods.objPrintAllTechs(btnPrintAllTechs, fileChooser, primaryStage, techList);
		
		methods.objPrintClosedRequests(mniPrintClosedRequests, fileChooser, primaryStage, requestList);
		methods.objPrintClosedRequests(btnPrintClosedRequests, fileChooser, primaryStage, requestList);
		
		methods.mniAbout(mniAbout, primaryStage);
		methods.mniExit(mniExit, database, primaryStage);
		methods.mniExport(mniExport, fileChooser, primaryStage, requestList);
		
		methods.objDeleteAllRequests(
				mniDeleteAllRequests, dtpRequested, 
				dtpCompleted, chkCompleted, 
				txaDescription, txaNotes, lsvTech, 
				requestList, tbvRequest, primaryStage);
		methods.objDeleteAllRequests(
				btnDeleteAllRequests, dtpRequested, 
				dtpCompleted, chkCompleted, 
				txaDescription, txaNotes, lsvTech, 
				requestList, tbvRequest, primaryStage);
		
		methods.objDeleteAllTechs(
				mniDeleteAllTechs, requestList, 
				techList, txfFirstName, txfLastName, 
				txfIdNumber, tbvRequest, 
				tbvTech, lsvTech, primaryStage);
		methods.objDeleteAllTechs(
				btnDeleteAllTechs,requestList, 
				techList, txfFirstName, txfLastName, 
				txfIdNumber, tbvRequest, 
				tbvTech, lsvTech, primaryStage);
		
		// 9.2 Form Buttons
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
				txaNotes, chkCompleted, 
				requestList, primaryStage);
		
		methods.btnDeleteTech(
				btnDeleteTech, txfFirstName, 
				txfLastName, txfIdNumber, 
				tbvRequest, tbvTech, lsvTech, 
				requestList, techList, primaryStage);
		
		// 9.3 Listeners
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
		
		// 9.4 Stage Close
		methods.onCloseRequest(primaryStage, database);
	
		/************ 10.0 Primary Stage ************/
		// 10.1 Add BorderPane root to Scene scene
		Scene scene = new Scene(root,800,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		// 10.2 Add Scene scene to Stage primaryStage
		primaryStage.setScene(scene);
		
		// 10.3 Additional Attributes
		primaryStage.setTitle("Request Tracker");
		primaryStage.setMaximized(true);
		primaryStage.getIcons().add(new Image(getClass().getResource("images/Repository-16.png").toExternalForm()));
		
		//10.4 Show Stage primaryStage
		primaryStage.show();
	} 
	
	public static void main(String[] args) {
		launch(args);
	}
}

