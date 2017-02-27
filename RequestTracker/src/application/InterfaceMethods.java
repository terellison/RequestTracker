package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Optional;
import java.util.UUID;

import application.database.SQLiteJDBC;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;

/*
 * Table of Contents
 * 
 * This class assigns methods to various interface nodes in the program.
 * They were moved here to improve code readability.
 * 
 *	1.0 MenuItems and Toolbar Buttons
 *		1.1 New Request MenuItem
 *		1.2 New Request Toolbar Button
 *		1.3 New Tech MenuItem
 *		1.4 New Tech Toolbar Button
 *		1.5 Print All Requests MenuItem
 *		1.6 Print All Requests Toolbar Button
 *		1.7 Print Open Requests MenuItem
 *		1.8 Print Open Request Toolbar Button
 *		1.9 Print Closed Requests MenuItem
 *		1.10 Print Closed Requests Toolbar Button
 *		1.11 Print All Techs MenuItem
 *		1.12 Print All Techs Toolbar Button
 *		1.13 About MenuItem
 *		1.14 Exit MenuItem
 *	2.0 Form Buttons
 *		2.1 Save Request Changes
 *		2.2 Save Tech Changes
 *		2.3 Delete Request
 *		2.4 Delete Tech
 *		2.5 Create Request
 *		2.6 Create Tech
 *	3.0 Stage
 *		3.1 Primary Stage Close Request
 *		3.2 Stage Close
 *	4.0 Listeners
 *		4.1 Request Table
 *		4.2 Tech Table
 *		4.3 CheckBox
 *		4.4 TextField
 *	5.0 Cell Values
 *		5.1 Date Requested Column
 *		5.2 Date Completed Column
 *		5.3 Description Column
 *		5.4 Tech Id Column
 *		5.5 Tech Name Column
 *		5.6 Notes Column
 *		5.7 Is Completed Column
 *		5.8 Technician ListView		
 *	6.0 Private Helper Functions
 *		6.1 Save File
 */
public class InterfaceMethods {
	/************ 1.0 MenuItems and Toolbar Buttons ************/
	// 1.1 New Request MenuItem
	public void mniNewRequest(MenuItem mni, Stage stage, TableView<Request> tbvR, RequestList rList){
		mni.setOnAction(e -> {
			try {
				WindowCreateRequest createWindow = new WindowCreateRequest(stage);
				createWindow.Show();
				tbvR.setItems(rList.getAll());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		});
	}
	
	// 1.2 New Request Toolbar Button
	public void mniNewRequest(Button btn, Stage stage, TableView<Request> tbvR, RequestList rList){
		btn.setOnAction(e -> {
			try {
				WindowCreateRequest createWindow = new WindowCreateRequest(stage);
				createWindow.Show();
				tbvR.setItems(rList.getAll());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		});
	}
	
	// 1.3 New Tech MenuItem
	public void mniNewTech(MenuItem mni, Stage stage, TableView<Technician> tbvT, ListView<Technician> lsvT, TechnicianList tList){
		mni.setOnAction(e -> {
			try {
				WindowCreateTechnician createWindow = new WindowCreateTechnician(stage);
				createWindow.Show();
				tbvT.setItems(tList.getAll());
				lsvT.setItems(tList.getAll());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
	
	// 1.4 New Tech Toolbar Button
	public void mniNewTech(Button btn, Stage stage, TableView<Technician> tbvT, ListView<Technician> lsvT, TechnicianList tList){
		btn.setOnAction(e -> {
			try {
				WindowCreateTechnician createWindow = new WindowCreateTechnician(stage);
				createWindow.Show();
				tbvT.setItems(tList.getAll());
				lsvT.setItems(tList.getAll());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
	
	// 1.5 Print All Requests MenuItem
	public void mniPrintAllRequests(MenuItem mni, RequestFileChooser fch, Stage stage, RequestList rList){
		mni.setOnAction(e -> {
			try{
				fch.setTitle("Save All Requests");
				fch.setInitialFileName("all_requests");
				
				File file = fch.showSaveDialog(stage);
				if(file != null){
					SaveFile(rList.printAllRequests(), file);
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
			
		});
	}
	
	// 1.6 Print All Requests Toolbar Button
	public void mniPrintAllRequests(Button btn, RequestFileChooser fch, Stage stage, RequestList rList){
		btn.setOnAction(e -> {
			try{
				fch.setTitle("Save All Requests");
				fch.setInitialFileName("all_requests");
				
				File file = fch.showSaveDialog(stage);
				if(file != null){
					SaveFile(rList.printAllRequests(), file);
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
			
		});
	}
	
	// 1.7 Print Open Requests MenuItem
	public void mniPrintOpenRequests(MenuItem mni, RequestFileChooser fch, Stage stage, RequestList rList){
		mni.setOnAction(e -> {
			try{
				fch.setTitle("Save Open Requests");
				fch.setInitialFileName("all_open_requests");
				
				File file = fch.showSaveDialog(stage);
				if(file != null){
					SaveFile(rList.printOpenRequests(), file);
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
			
		});
	}
	
	// 1.8 Print Open Request Toolbar Button
	public void mniPrintOpenRequests(Button btn, RequestFileChooser fch, Stage stage, RequestList rList){
		btn.setOnAction(e -> {
			try{
				fch.setTitle("Save Open Requests");
				fch.setInitialFileName("all_open_requests");
				
				File file = fch.showSaveDialog(stage);
				if(file != null){
					SaveFile(rList.printOpenRequests(), file);
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
			
		});
	}
	
	// 1.9 Print Closed Requests MenuItem
	public void mniPrintClosedRequests(MenuItem mni, RequestFileChooser fch, Stage stage, RequestList rList){
		mni.setOnAction(e -> {
			try{
				fch.setTitle("Save Closed Requests");
				fch.setInitialFileName("all_closed_requests");
				
				File file = fch.showSaveDialog(stage);
				if(file != null){
					SaveFile(rList.printClosedRequests(), file);
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
			
		});
	}
	
	// 1.10 Print Closed Requests Toolbar Button
	public void mniPrintClosedRequests(Button btn, RequestFileChooser fch, Stage stage, RequestList rList){
		btn.setOnAction(e -> {
			try{
				fch.setTitle("Save Closed Requests");
				fch.setInitialFileName("all_closed_requests");
				
				File file = fch.showSaveDialog(stage);
				if(file != null){
					SaveFile(rList.printClosedRequests(), file);
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
			
		});
	}
	
	// 1.11 Print All Techs MenuItem
	public void mniPrintAllTechs(MenuItem mni, RequestFileChooser fch, Stage stage, TechnicianList tList){
		mni.setOnAction(e -> {
			try{
				fch.setTitle("Save All Technicians");
				fch.setInitialFileName("all_technicians");
				
				File file = fch.showSaveDialog(stage);
				if(file != null){
					SaveFile(tList.printAllTechs(), file);
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
			
		});
	}
	
	// 1.12 Print All Techs Toolbar Button
	public void mniPrintAllTechs(Button btn, RequestFileChooser fch, Stage stage, TechnicianList tList){
		btn.setOnAction(e -> {
			try{
				fch.setTitle("Save All Technicians");
				fch.setInitialFileName("all_technicians");
				
				File file = fch.showSaveDialog(stage);
				if(file != null){
					SaveFile(tList.printAllTechs(), file);
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
			
		});
	}
	
	// 1.13 About MenuItem
	public void mniAbout(MenuItem mni, Stage stage){
		mni.setOnAction(e -> {
			WindowAbout createWindow = new WindowAbout(stage);
			createWindow.Show();
		});
	}
	
	// 1.14 Exit MenuItem
	public void mniExit(MenuItem mni, SQLiteJDBC sql){
		mni.setOnAction(e -> {
			try{
				sql.closeDbConnection();
				System.exit(0);
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		});
	}
	
	/************ 2.0 Form Buttons ************/
	// 2.1 Save Request Changes
	public void btnSaveRequestChanges(
			Button btn, TableView<Request> tbvR, ListView<Technician> lsvT,
			DatePicker dtpReq, DatePicker dtpCom, TextArea txaDes, TextArea txaNot, 
			CheckBox chkCom, RequestList rList, TechnicianList tList){
		btn.setOnAction(e -> {
			try{
				Request r = tbvR.getSelectionModel().getSelectedItem();
				int index = lsvT.getSelectionModel().getSelectedIndex();
				StringBuilder dtp1 = new StringBuilder(""), dtp2 = new StringBuilder("");
				
				if(dtpReq.getValue() != null){
					dtp1.append(dtpReq.getValue().toString());
				}
				if(dtpCom.getValue() != null){
					dtp2.append(dtpCom.getValue().toString());
				}
				
				switch(index){
					case -1:	r.setRequested(dtp1.toString());
								r.setCompleted(dtp2.toString());
								r.setDescription(txaDes.getText());
								r.setTechId(new Technician("n/a", "n/a", "n/a").getUUID());
								r.setNotes(txaNot.getText());
								r.setIsCompleted(chkCom.isSelected());
								break;
					default:	r.setRequested(dtp1.toString());
								r.setCompleted(dtp2.toString());
								r.setDescription(txaDes.getText());
								r.setTechId(tList.getAll().get(index).getUUID());
								r.setNotes(txaNot.getText());
								r.setIsCompleted(chkCom.isSelected());
				}
				rList.editRequest(r);
				tbvR.setItems(rList.getAll());
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		});
	}
	
	// 2.2 Save Tech Changes
	public void btnSaveTechChanges(
			Button btn, TextField txfFir, 
			TextField txfLas, TextField txfId,
			TableView<Request> tbvR, TableView<Technician> tbvT,
			ListView<Technician> lsvT, RequestList rList, TechnicianList tList){
		btn.setOnAction(e -> {
			try{
				Technician t = tbvT.getSelectionModel().getSelectedItem();
				t.setFirstName(txfFir.getText());
				t.setLastName(txfLas.getText());
				t.setIdNumber(txfId.getText());
				t.setFullName();
				
				tList.editTechnician(t);
				tbvT.setItems(tList.getAll());
				lsvT.setItems(tList.getAll());
				tbvR.setItems(rList.getAll());
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		});
	}
	
	// 2.3 Delete Request
	public void btnDeleteRequest(
			Button btn, TableView<Request> tbvR, ListView<Technician> lsvT,
			DatePicker dtpReq, DatePicker dtpCom, TextArea txaDes, TextArea txaNot, 
			CheckBox chkCom, RequestList rList){
		btn.setOnAction(e -> {
			try{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Deletion Confirmation");
				alert.setHeaderText("Are You Sure?");
				alert.setContentText("Are you sure you want to delete this?");
				
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == ButtonType.OK){
					rList.deleteRequest(tbvR.getSelectionModel().getSelectedItem());
					tbvR.setItems(rList.getAll());
					dtpReq.setValue(null);
					dtpCom.setValue(null);
					txaDes.setText("");
					txaNot.setText("");
					lsvT.getItems().clear();
					chkCom.setSelected(false);
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		});
	}
	
	// 2.4 Delete Tech
	public void btnDeleteTech(
			Button btn, TextField txfFir, TextField txfLas, 
			TextField txfId, TableView<Request> tbvR, TableView<Technician> tbvT,
			ListView<Technician> lsvT, RequestList rList, TechnicianList tList){
		btn.setOnAction(e -> {
			try{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Deletion Confirmation");
				alert.setHeaderText("Are You Sure?");
				alert.setContentText("Are you sure you want to delete this?");
				
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == ButtonType.OK){
					tList.deleteTechnician(tbvT.getSelectionModel().getSelectedItem());
					txfFir.setText("");
					txfLas.setText("");
					txfId.setText("");
					
					tbvR.setItems(rList.getAll());
					tbvT.setItems(tList.getAll());
					lsvT.setItems(tList.getAll());	
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		});
		
	}
	
	// 2.5 Create Request
	public void btnCreateRequest(
			Button btn, DatePicker dtpReq, DatePicker dtpCom,
			CheckBox chkCom, TextArea txaDes, TextArea txaNot, 
			ListView<Technician> lsvT, Stage stage){
		btn.setOnAction(e -> {
			try{
				int techIndex = lsvT.getSelectionModel().getSelectedIndex();
				StringBuilder requestedDate = new StringBuilder("");
				StringBuilder completedDate = new StringBuilder("");
				Technician t;
				
				if(dtpReq.getValue() != null){
					requestedDate.append(dtpReq.getValue().toString());
				}
				if(dtpCom.getValue() != null){
					completedDate.append(dtpCom.getValue().toString());
				}
				
				switch(techIndex){
					case -1:	t = new Technician("n/a/", "n/a", "n/a");
								RequestList.get()
									.addRequest(new Request(
											requestedDate.toString(),
											completedDate.toString(),
											txaDes.getText(),
											t.getUUID(),
											txaNot.getText(),
											chkCom.isSelected()
									));
								break;
					default:	t = TechnicianList.get().getAll().get(techIndex);
								RequestList.get()
								.addRequest(new Request(
										requestedDate.toString(),
										completedDate.toString(),
										txaDes.getText(),
										t.getUUID(),
										txaNot.getText(),
										chkCom.isSelected()
								));
								break;
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
			stage.close();
		});
	}
	
	// 2.6 Create Tech
	public void btnCreateTech(
			Button btn, TextField txfFir, TextField txfLas, 
			TextField txfId, Stage stage){
		btn.setOnAction(e -> {
			try{
				TechnicianList.get()
					.addTechnician(new Technician(
							txfFir.getText(), 
							txfLas.getText(), 
							txfId.getText()
					));
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
			stage.close();
		});
	}
	
	/************ 3.0 Stage ************/
	// 3.1 Primary Stage Close Request
	public void onCloseRequest(Stage stage, SQLiteJDBC data){
		stage.setOnCloseRequest(e -> {
			try {
				data.closeDbConnection();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
	
	// 3.2 Stage Close
	public void stageClose(Button btn, Stage stage){
		btn.setOnAction(e -> {	
			stage.close();
		});
	}
	
	/************ 4.0 Listeners ***********/
	// 4.1 Request Table
	public void requestTableListener(
			TableView<Request> tbvR, DatePicker dtpReq, DatePicker dtpComp,
			TextArea txaDes, TextArea txaNot, ListView<Technician> lsvT,
			CheckBox chkCom, Button btnSav, Button btnDel,
			TechnicianList tList){
		tbvR.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
			try{
				if(newSel != null){
					if(!newSel.getRequested().equals("")){
						dtpReq.setValue(newSel.getRequestedLocalDate());
					}
					else{
						dtpReq.setValue(null);
					}
					
					if(!newSel.getCompleted().equals("")){
						dtpComp.setValue(newSel.getCompletedLocalDate());
					}
					else{
						dtpComp.setValue(null);
					}
					txaDes.setText(newSel.getDescription());
					txaNot.setText(newSel.getNotes());
					lsvT.setItems(tList.getAll());
					lsvT.getSelectionModel().select(
						tList.searchTechList(
							UUID.fromString(newSel.getTechId())
						)
					);
					lsvT.scrollTo(tList.searchTechList(UUID.fromString(newSel.getTechId())));
					chkCom.setSelected(newSel.isCompleted());
					btnSav.setVisible(true);
					btnDel.setVisible(true);
				}
				else{
					
					
					btnSav.setVisible(false);
					btnDel.setVisible(false);
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		});
	}
	
	// 4.2 Tech Table
	public void techTableListener(
			TableView<Technician> tbvT, TextField txfFir, TextField txfLas, 
			TextField txfId, Button btnSav, Button btnDel){
		tbvT.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
			try{
				if(newSel != null){
					txfFir.setText(newSel.getFirstName());
					txfLas.setText(newSel.getLastName());
					txfId.setText(newSel.getIdNumber());
					btnSav.setVisible(true);
					btnDel.setVisible(true);
				}
				else{
					btnSav.setVisible(false);
					btnDel.setVisible(false);
				}
			}
			catch(Exception e1){
				
			}
		});
	}
	
	// 4.3 CheckBox
	public void checkboxListener(
			CheckBox chk, DatePicker dtp){
		chk.selectedProperty().addListener((ov, oldV, newV) -> {
			if(newV){
				dtp.setDisable(!newV);
			}
			else{
				dtp.setDisable(!newV);
				dtp.setValue(null);
			}
		});
	}
	
	// 4.4 TextField
	public void textfieldListener(
			TextField txf){
		txf.textProperty().addListener((ovs, oldV, newV) -> {
			if(!newV.matches("\\d*")){
				txf.setText(newV.replaceAll("[^\\d]", ""));
			}
		});
	}
	
	/************ 5.0 Cell Values ************/
	// 5.1 Date Requested Column
	public void dateRequestedCol(TableColumn<Request, String> col){
		col.setCellValueFactory(new Callback<CellDataFeatures<Request, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(CellDataFeatures<Request, String> data) {
				if(data.getValue().getRequested().equals("")){
					return new SimpleStringProperty("n/a");
				}
				else{
					return new SimpleStringProperty(data.getValue().getRequested());
				}
			}
			
		});
	}
	
	// 5.2 Date Completed Column
	public void dateCompletedCol(TableColumn<Request, String> col){
		col.setCellValueFactory(new Callback<CellDataFeatures<Request, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(CellDataFeatures<Request, String> data) {
				if(data.getValue().getCompleted().equals("")){
					return new SimpleStringProperty("n/a");
				}
				else{
					return new SimpleStringProperty(data.getValue().getCompleted());
				}
			}
			
		});
	}
	
	// 5.3 Description Column
	public void descriptionCol(TableColumn<Request, String> col){
		col.setCellValueFactory(new Callback<CellDataFeatures<Request, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(CellDataFeatures<Request, String> data) {
				if(data.getValue().getDescription().equals("")){
					return new SimpleStringProperty("n/a");
				}
				else{
					return new SimpleStringProperty(data.getValue().getDescription());
				}
			}
			
		});
	}
	
	// 5.4 Tech Id Column
	public void techIdCol(TableColumn<Request, String> col, TechnicianList tList){
		col.setCellValueFactory(new Callback<CellDataFeatures<Request, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(CellDataFeatures<Request, String> data) {							
				try {
					Technician t = tList.getTech(UUID.fromString(data.getValue().getTechId()));
					if(t != null){
						return t.idProperty();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return new SimpleStringProperty("n/a");
			}
		});
	}
	
	// 5.5 Tech Name Column
	public void techNameCol(TableColumn<Request, String> col, TechnicianList tList){
		col.setCellValueFactory(new Callback<CellDataFeatures<Request, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(CellDataFeatures<Request, String> data) {
				try {
					Technician t = tList.getTech(UUID.fromString(data.getValue().getTechId()));
					
					if(t != null){
						return t.nameProperty();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return new SimpleStringProperty("n/a");
			}
			
		});
	}
	
	// 5.6 Notes Column
	public void notesCol(TableColumn<Request, String> col){
		col.setCellValueFactory(new Callback<CellDataFeatures<Request, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(CellDataFeatures<Request, String> data) {
				if(data.getValue().getNotes().equals("")){
					return new SimpleStringProperty("n/a");
				}
				else{
					return new SimpleStringProperty(data.getValue().getNotes());
				}
			}
			
		});
	}
	
	// 5.7 Is Completed Column
	public void isCompletedCol(TableColumn<Request, String> col){
		col.setCellValueFactory(new Callback<CellDataFeatures<Request, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(CellDataFeatures<Request, String> data) {
				boolean bool = data.getValue().isCompleted();
				
				if(bool){
					return new SimpleStringProperty("Yes");
				}
				return new SimpleStringProperty("No");
			}
			
		});
	}
	
	// 5.8 Technician ListView
	public void techListView(ListView<Technician> lsv){
		lsv.setCellFactory(new Callback<ListView<Technician>, ListCell<Technician>>(){
			@Override
			public ListCell<Technician> call(ListView<Technician> t) {
				
				ListCell<Technician> cell = new ListCell<Technician>(){
					@Override
					protected void updateItem(Technician t, boolean empty) {
						super.updateItem(t, empty);
						if(empty || t == null){
							setGraphic(null);
							setText(null);
						}
						else if(t != null){
							setText(t.getFirstName() + " " + t.getLastName());
						}
					}
				};
				
				return cell;
			}		
		});
	}
	
	/************ 6.0 Private Helper Functions ************/
	// 6.1 Save File
	private void SaveFile(String content, File file){
        try  (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
        	StringBuilder line = new StringBuilder();
        	for(int a = 0; a < content.length(); a++){
        		if(content.charAt(a) == '\n'){
        			
        			bw.write(line.toString());
        			bw.newLine();
        			line = new StringBuilder();
        		}
        		else if(a == content.length() - 1){
        			bw.write(line.append(content.charAt(a)).toString());
        			bw.newLine();
        		}
        		else{
        			line.append(content.charAt(a));
        		}
        	}
        } catch (FileNotFoundException ex){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cannot Access File");
			alert.setContentText("The application cannot access the file because it is being used by another process. Please close the file and try again.");

			alert.showAndWait();
		}
        catch (Exception e) {
            e.printStackTrace();
        }
         
    }
}
