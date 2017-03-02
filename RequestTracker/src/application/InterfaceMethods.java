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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * The Class InterfaceMethods.
 * Assigns methods to various interface nodes in the program.
 * They were moved here to improve code readability and 
 * maintainability.
 * 
 * @author Chaz-Rae L. Moncrieffe
 * @since 3/1/2017
 */
/*
 * Table of Contents 
 *	1.0 MenuItems and Toolbar Buttons
 *		1.1 New Request
 *		1.2 New Tech
 *		1.3 Print All Requests
 *		1.4 Print Open Requests
 *		1.5 Print Closed Requests
 *		1.6 Print All Techs
 *		1.7 About MenuItem
 *		1.8 Exit MenuItem
 *		1.9 Delete All Requests
 *		1.10 Delete All Technicians
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
 *		5.8 Request Id Column
 *		5.9 Technician ListView		
 *		5.10 First Name Column
 *		5.11 Last Name Column
 *		5.12 ID Number Column
 *	6.0 Add TableColumns to TableView
 *		6.1 Add Request Columns
 *		6.2 Add Technician Columns
 *	7.0 Private Helper Functions
 *		7.1 Save File
 */
public class InterfaceMethods {
	/********** 1.0 MenuItems and Toolbar Buttons ***********/
	/**
	 * 1.1 New Request
	 * 
	 * @param obj the Object
	 * @param stage the Stage
	 * @param tbvR the TableView
	 * @param rList the RequestList
	 */
	public void objNewRequest(Object obj, Stage stage, TableView<Request> tbvR, RequestList rList){
		if(obj.getClass() == Button.class){
			((Button)obj).setOnAction(e -> {
				try {
					WindowCreateRequest createWindow = new WindowCreateRequest(stage);
					createWindow.Show();
					tbvR.setItems(rList.getAll());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			});
		}
		else if(obj.getClass() == MenuItem.class){
			((MenuItem)obj).setOnAction(e -> {
				try {
					WindowCreateRequest createWindow = new WindowCreateRequest(stage);
					createWindow.Show();
					tbvR.setItems(rList.getAll());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			});
		}
	}
	
	/**
	 * 1.2 New Tech
	 *
	 * @param obj the Object
	 * @param stage the Stage
	 * @param tbvT the TableView
	 * @param lsvT the ListView
	 * @param tList the TechnicianList
	 */
	public void objNewTech(Object obj, Stage stage, TableView<Technician> tbvT, ListView<Technician> lsvT, TechnicianList tList){
		if(obj.getClass() == Button.class){
			((Button)obj).setOnAction(e -> {
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
		else if(obj.getClass() == MenuItem.class){
			((MenuItem)obj).setOnAction(e -> {
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
	}
	
	/**
	 * 1.3 Print All Requests.
	 *
	 * @param obj the Object
	 * @param fch the RequestFileChooser
	 * @param stage the Stage
	 * @param rList the RequestList
	 */
	public void objPrintAllRequests(Object obj, RequestFileChooser fch, Stage stage, RequestList rList){
		if(obj.getClass() == Button.class){
			((Button)obj).setOnAction(e -> {
				try{
					fch.setTitle("Save All Requests");
					fch.setInitialFileName("all_requests");
					
					File file = fch.showSaveDialog(stage);
					if(file != null){
						SaveFile(rList.printAllRequests(), file,stage);
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
				
			});
		}
		else if(obj.getClass() == MenuItem.class){
			((MenuItem)obj).setOnAction(e -> {
				try{
					fch.setTitle("Save All Requests");
					fch.setInitialFileName("all_requests");
					
					File file = fch.showSaveDialog(stage);
					if(file != null){
						SaveFile(rList.printAllRequests(), file,stage);
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
				
			});
		}
	}
	
	/**
	 * 1.4 Print Open Requests.
	 *
	 * @param obj the Object
	 * @param fch the RequestFileChoosed
	 * @param stage the Stage
	 * @param rList the RequestList
	 */
	public void objPrintOpenRequests(Object obj, RequestFileChooser fch, Stage stage, RequestList rList){
		if(obj.getClass() == Button.class){
			((Button)obj).setOnAction(e -> {
				try{
					fch.setTitle("Save Open Requests");
					fch.setInitialFileName("all_open_requests");
					
					File file = fch.showSaveDialog(stage);
					if(file != null){
						SaveFile(rList.printOpenRequests(), file,stage);
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
				
			});
		}
		else if(obj.getClass() == MenuItem.class){
			((MenuItem)obj).setOnAction(e -> {
				try{
					fch.setTitle("Save Open Requests");
					fch.setInitialFileName("all_open_requests");
					
					File file = fch.showSaveDialog(stage);
					if(file != null){
						SaveFile(rList.printOpenRequests(), file, stage);
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
				
			});
		}
	}
	
	
	/**
	 * 1.5 Print Closed Requests.
	 *
	 * @param obj the Object
	 * @param fch the RequestFileChooser
	 * @param stage the Stage
	 * @param rList the RequestList
	 */
	public void objPrintClosedRequests(Object obj, RequestFileChooser fch, Stage stage, RequestList rList){
		if(obj.getClass() == Button.class){
			((Button)obj).setOnAction(e -> {
				try{
					fch.setTitle("Save Closed Requests");
					fch.setInitialFileName("all_closed_requests");
					
					File file = fch.showSaveDialog(stage);
					if(file != null){
						SaveFile(rList.printClosedRequests(), file,stage);
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
				
			});
		}
		else if(obj.getClass() == MenuItem.class){
			((MenuItem)obj).setOnAction(e -> {
				try{
					fch.setTitle("Save Closed Requests");
					fch.setInitialFileName("all_closed_requests");
					
					File file = fch.showSaveDialog(stage);
					if(file != null){
						SaveFile(rList.printClosedRequests(), file, stage);
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
				
			});
		}
	}
	
	/**
	 * 1.6 Print All Techs.
	 *
	 * @param obj the Object
	 * @param fch the RequestFileChooser
	 * @param stage the Stage
	 * @param tList the TechnicianList
	 */
	public void objPrintAllTechs(Object obj, RequestFileChooser fch, Stage stage, TechnicianList tList){
		if(obj.getClass() == Button.class){
			((Button)obj).setOnAction(e -> {
				try{
					fch.setTitle("Save All Technicians");
					fch.setInitialFileName("all_technicians");
					
					File file = fch.showSaveDialog(stage);
					if(file != null){
						SaveFile(tList.printAllTechs(), file, stage);
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
				
			});
		}
		else if(obj.getClass() == MenuItem.class){
			((MenuItem)obj).setOnAction(e -> {
				try{
					fch.setTitle("Save All Technicians");
					fch.setInitialFileName("all_technicians");
					
					File file = fch.showSaveDialog(stage);
					if(file != null){
						SaveFile(tList.printAllTechs(), file, stage);
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
				
			});
		}
	}
	
	/**
	 * 1.7 About
	 *
	 * @param mni the MenuItem
	 * @param stage the Stage
	 */
	public void mniAbout(MenuItem mni, Stage stage){
		mni.setOnAction(e -> {
			WindowAbout createWindow = new WindowAbout(stage);
			createWindow.Show();
		});
	}
	
	/**
	 * 1.8 Exit
	 *
	 * @param mni the MenuItem
	 * @param sql the SQLiteJDBC
	 * @param stage the Stage
	 */
	public void mniExit(MenuItem mni, SQLiteJDBC sql, Stage stage){
		mni.setOnAction(e -> {
			try{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Close Program");
				alert.setHeaderText("Are You Sure?");
				alert.setContentText("Do you want to close Request Tracker?");
				alert.initOwner(stage);
				
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == ButtonType.OK){
					sql.closeDbConnection();
					System.exit(0);
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		});
	}
	
	/**
	 * 1.9 Delete All Requests
	 *
	 * @param obj the Object
	 * @param dtpReq the DatePicker
	 * @param dtpCom the DatPicker
	 * @param chkCom the CheckBox
	 * @param txaDes the TextArea
	 * @param txaNot the TextArea
	 * @param lsvT the ListView
	 * @param rList the RequestList
	 * @param tbvR the TableView
	 * @param stage the Stage
	 */
	public void objDeleteAllRequests(
			Object obj, DatePicker dtpReq, DatePicker dtpCom,
			CheckBox chkCom, TextArea txaDes, TextArea txaNot,
			ListView<Technician> lsvT, RequestList rList, 
			TableView<Request> tbvR, Stage stage){
		if(obj.getClass() == Button.class){
			((Button) obj).setOnAction(e -> {
				try{
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Deletion Confirmation");
					alert.setHeaderText("Are You Sure?");
					alert.setContentText("Are you sure you want to delete all requests?");
					alert.initOwner(stage);
					
					Optional<ButtonType> result = alert.showAndWait();		
					if(result.get() == ButtonType.OK){
						rList.deleteAll();
						tbvR.setItems(rList.getAll());	
						
						dtpReq.setValue(null);
						dtpCom.setValue(null);
						dtpCom.setDisable(true);
						chkCom.setSelected(false);
						txaDes.setText(null);
						txaNot.setText(null);
						lsvT.setItems(null);
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			});
		}
		else if(obj.getClass() == MenuItem.class){
			((MenuItem) obj).setOnAction(e -> {
				try{
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Deletion Confirmation");
					alert.setHeaderText("Are You Sure?");
					alert.setContentText("Are you sure you want to delete all requests?");
					alert.initOwner(stage);
					
					Optional<ButtonType> result = alert.showAndWait();		
					if(result.get() == ButtonType.OK){
						rList.deleteAll();
						tbvR.setItems(rList.getAll());	
						
						dtpReq.setValue(null);
						dtpCom.setValue(null);
						dtpCom.setDisable(true);
						chkCom.setSelected(false);
						txaDes.setText(null);
						txaNot.setText(null);
						lsvT.setItems(null);
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			});
		}

	}
	
	/**
	 * 1.10 Delete All Technicians
	 *
	 * @param obj the Object
	 * @param rList the RequestList
	 * @param tList the TechnicianList
	 * @param txfFir the TextField
	 * @param txfLas the TextField
	 * @param txfId the TextField
	 * @param tbvR the TableView
	 * @param tbvT the TableView
	 * @param lsvT the ListView
	 * @param stage the Stage
	 */
	public void objDeleteAllTechs(
			Object obj, RequestList rList, TechnicianList tList,
			TextField txfFir, TextField txfLas, TextField txfId,
			TableView<Request> tbvR, TableView<Technician> tbvT,
			ListView<Technician> lsvT, Stage stage){
		if(obj.getClass() == Button.class){
			((Button)obj).setOnAction(e -> {
				try{
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Deletion Confirmation");
					alert.setHeaderText("Are You Sure?");
					alert.setContentText("Are you sure you want to delete all technicians?");
					alert.initOwner(stage);
					
					Optional<ButtonType> result = alert.showAndWait();
					if(result.get() == ButtonType.OK){
						tList.deleteAll();
						tbvR.setItems(rList.getAll());
						tbvT.setItems(tList.getAll());
						lsvT.setItems(tList.getAll());	
						
						txfFir.setText("");
						txfLas.setText("");
						txfId.setText("");
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			});	
		}
		else if(obj.getClass() == MenuItem.class){
			((MenuItem)obj).setOnAction(e -> {
				try{
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Deletion Confirmation");
					alert.setHeaderText("Are You Sure?");
					alert.setContentText("Are you sure you want to delete all technicians?");
					alert.initOwner(stage);
					
					Optional<ButtonType> result = alert.showAndWait();
					if(result.get() == ButtonType.OK){
						tList.deleteAll();
						tbvR.setItems(rList.getAll());
						tbvT.setItems(tList.getAll());
						lsvT.setItems(tList.getAll());	
						
						txfFir.setText("");
						txfLas.setText("");
						txfId.setText("");
					}
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			});	
		}
	}
	/********** 2.0 Form Buttons ***********/
	/**
	 * 2.1 Save Request Changes
	 *
	 * @param btn the Button
	 * @param tbvR the TableView
	 * @param lsvT the ListView
	 * @param dtpReq the DatePicker
	 * @param dtpCom the DatePicker
	 * @param txaDes the TextArea
	 * @param txaNot the TextArea
	 * @param chkCom the CheckBox
	 * @param rList the RequestList
	 * @param tList the TechnicianList
	 */
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
	
	/**
	 * 2.2 Save Tech Changes
	 *
	 * @param btn the Button
	 * @param txfFir the TextField
	 * @param txfLas the TextField
	 * @param txfId the TextField
	 * @param tbvR the TableView
	 * @param tbvT the TableView
	 * @param lsvT the ListView
	 * @param rList the RequestList
	 * @param tList the TechnicianList
	 */
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
	
	/**
	 * 2.3 Delete Request
	 *
	 * @param btn the Button
	 * @param tbvR the TableView
	 * @param lsvT the ListView
	 * @param dtpReq the DatePicker
	 * @param dtpCom the DatePicker
	 * @param txaDes the TextArea
	 * @param txaNot the TextArea
	 * @param chkCom the CheckBox
	 * @param rList the RequestList
	 * @param stage the Stage
	 */
	public void btnDeleteRequest(
			Button btn, TableView<Request> tbvR, ListView<Technician> lsvT,
			DatePicker dtpReq, DatePicker dtpCom, TextArea txaDes, TextArea txaNot, 
			CheckBox chkCom, RequestList rList, Stage stage){
		btn.setOnAction(e -> {
			try{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Deletion Confirmation");
				alert.setHeaderText("Are You Sure?");
				alert.setContentText("Are you sure you want to delete this?");
				alert.initOwner(stage);
				
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
	
	/**
	 * 2.4 Delete Tech
	 *
	 * @param btn the Button
	 * @param txfFir the TextField
	 * @param txfLas the TextField
	 * @param txfId the TextField
	 * @param tbvR the TableView
	 * @param tbvT the TableView
	 * @param lsvT the ListView
	 * @param rList the RequestList
	 * @param tList the TechnicianList
	 * @param stage the Stage
	 */
	public void btnDeleteTech(
			Button btn, TextField txfFir, TextField txfLas, 
			TextField txfId, TableView<Request> tbvR, TableView<Technician> tbvT,
			ListView<Technician> lsvT, RequestList rList, TechnicianList tList, Stage stage){
		btn.setOnAction(e -> {
			try{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Deletion Confirmation");
				alert.setHeaderText("Are You Sure?");
				alert.setContentText("Are you sure you want to delete this?");
				alert.initOwner(stage);
				
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
	
	/**
	 * 2.5 Create Request
	 *
	 * @param btn the Button
	 * @param dtpReq the DatePicker
	 * @param dtpCom the DatePicker
	 * @param chkCom the CheckBox
	 * @param txaDes the TextArea
	 * @param txaNot the TextArea
	 * @param lsvT the ListView
	 * @param stage the Stage
	 */
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
	
	/**
	 * 2.6 Create Tech
	 *
	 * @param btn the Button
	 * @param txfFir the TextField
	 * @param txfLas the TextField
	 * @param txfId the TextField
	 * @param stage the Stage
	 */
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
	
	/********** 3.0 Stage ***********/
	/**
	 * 3.1 Primary Stage Close Request
	 *
	 * @param stage the Stage
	 * @param data the SQLiteJDBC
	 */
	public void onCloseRequest(Stage stage, SQLiteJDBC data){
		stage.setOnCloseRequest(e -> {
			try {
				data.closeDbConnection();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
	
	/**
	 * 3.2 Stage Close
	 *
	 * @param btn the Button
	 * @param stage the Stage
	 */
	public void stageClose(Button btn, Stage stage){
		btn.setOnAction(e -> {	
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Cancel");
			alert.setHeaderText("Are You Sure?");
			alert.setContentText("If you cancel, your input will not be saved.");
			alert.initOwner(stage);
			
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == ButtonType.OK){
				stage.close();
			}
		});
	}
	
	/********** 4.0 Listeners **********/
	/**
	 * 4.1 Request Table
	 *
	 * @param tbvR the TableView
	 * @param dtpReq the DatePicker
	 * @param dtpComp the dtp comp
	 * @param txaDes the TextArea
	 * @param txaNot the TextArea
	 * @param lsvT the ListView
	 * @param chkCom the CheckBox
	 * @param btnSav the Button
	 * @param btnDel the Button
	 * @param tList the TechnicianList
	 */
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
	
	/**
	 * 4.2 Tech Table
	 *
	 * @param tbvT the TableView
	 * @param txfFir the TextField
	 * @param txfLas the TextField
	 * @param txfId the TextField
	 * @param btnSav the Button
	 * @param btnDel the Button
	 */
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
	
	/**
	 * 4.3 CheckBox
	 *
	 * @param chk the CheckBox
	 * @param dtp the DatePicker
	 */
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
	
	/**
	 * 4.4 TextField
	 *
	 * @param txf the TextField
	 */
	public void textfieldListener(
			TextField txf){
		txf.textProperty().addListener((ovs, oldV, newV) -> {
			if(!newV.matches("\\d*")){
				txf.setText(newV.replaceAll("[^\\d]", ""));
			}
		});
	}
	
	/********** 5.0 Cell Values ***********/
	/**
	 * 5.1 Date Requested Column
	 *
	 * @param col the TableColumn
	 */
	public void dateRequestedCol(TableColumn<Request, String> col){
		col.setMinWidth(150);
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
	
	/**
	 * 5.2 Date Completed Column
	 *
	 * @param col the TableColumn
	 */
	public void dateCompletedCol(TableColumn<Request, String> col){
		col.setMinWidth(150);
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
	
	/**
	 * 5.3 Description Column
	 *
	 * @param col the TableColumn
	 */
	public void descriptionCol(TableColumn<Request, String> col){
		col.setMinWidth(100);
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
	
	/**
	 * 5.4 Tech Id Column
	 *
	 * @param col the TableColumn
	 * @param tList the TechnicianList
	 */
	public void techIdCol(TableColumn<Request, String> col, TechnicianList tList){
		col.setMinWidth(150);
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
	
	/**
	 * 5.5 Tech Name Column
	 *
	 * @param col the TableColumn
	 * @param tList the TechnicianList
	 */
	public void techNameCol(TableColumn<Request, String> col, TechnicianList tList){
		col.setMinWidth(150);
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
	
	/**
	 * 5.6 Notes Column
	 *
	 * @param col the TableColumn
	 */
	public void notesCol(TableColumn<Request, String> col){
		col.setMinWidth(100);
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
	
	/**
	 * 5.7 Is Completed Column
	 *
	 * @param col the TableColumn
	 */
	public void isCompletedCol(TableColumn<Request, String> col){
		col.setMinWidth(100);
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
	
	/**
	 * 5.8 Request Id Column
	 *
	 * @param col the TableColumn
	 */
	public void idColumn(TableColumn<Request, String> col){
		col.setMinWidth(100);
		col.setCellValueFactory(new Callback<CellDataFeatures<Request, String>, ObservableValue<String>>(){

			@Override
			public ObservableValue<String> call(CellDataFeatures<Request, String> data) {
				return new SimpleStringProperty(Integer.toString(data.getValue().getId()));
			}
			
		});
	}
	
	/**
	 * 5.9 Technician ListView.
	 *
	 * @param lsv the ListView
	 */
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
	
	/**
	 *  5.10 First Name Column
	 *  
	 *  @param col the TableColumn
	 */
	public void firstName(TableColumn<Technician, String> col){
		col.setMinWidth(150);
		col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	}
	
	/**
	 *  5.11 Last Name Column
	 *  
	 *  @param col the TableColumn
	 */
	public void lastName(TableColumn<Technician, String> col){
		col.setMinWidth(150);
		col.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	}
	
	/**
	 *  5.12 ID Number Column
	 *  
	 *  @param col the TableColumn
	 */
	public void idNumber(TableColumn<Technician, String> col){
		col.setMinWidth(150);
		col.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
	}
	
	/************ 6.0 Add TableColumns to TableView ************/
	/**
	 * 6.1 Add Request Columns
	 *
	 * @param tbvR the TableView
	 * @param columns the TableColumn[]
	 */
	public void addRequestColumns(TableView<Request> tbvR, @SuppressWarnings("unchecked") TableColumn<Request, String>...columns){
		for(TableColumn<Request, String> t:columns){
			tbvR.getColumns().add(t);
		}
		tbvR.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	/**
	 * 6.2 Add Technician Columns
	 * 
	 * @param tbvT the TableView
	 * @param columns the TableColumn[]
	 */
	public void addTechColumns(TableView<Technician> tbvT, @SuppressWarnings("unchecked") TableColumn<Technician, String>...columns){
		for(TableColumn<Technician, String> t:columns){
			tbvT.getColumns().add(t);
		}
		tbvT.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	/********** 7.0 Private Helper Functions ***********/
	/**
	 * 7.1 Save File
	 * 
	 * @param content the String
	 * @param file the File
	 * @param stage the Stage
	 */
	private void SaveFile(String content, File file, Stage stage){
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
			alert.initOwner(stage);
			
			alert.showAndWait();
		}
        catch (Exception e) {
            e.printStackTrace();
        }
         
    }
}
