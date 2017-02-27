package application;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/*
 *	This class provides a static FileChooser object to be
 *	used throughout the program in order to keep the settings
 *	of the current FileChooser session. 
 */

public class RequestFileChooser {
	private static RequestFileChooser requestFileChooser;
	private FileChooser fileChooser;

	public static RequestFileChooser get(){
		if(requestFileChooser == null){
			requestFileChooser = new RequestFileChooser();
		}
		return requestFileChooser;
	}
	
	public RequestFileChooser(){
		fileChooser = new FileChooser();
		ExtensionFilter textFilter = new ExtensionFilter("TXT files (*.txt)", "*.txt");
		ExtensionFilter wordFilter = new ExtensionFilter("All files (*.*)", ".*");
		fileChooser.getExtensionFilters().addAll(textFilter, wordFilter);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	
	}
	
	public void setTitle(String title){
		fileChooser.setTitle(title);
	}
	
	public void setInitialFileName(String fileName){
		fileChooser.setInitialFileName(fileName);
	}
	
	public File showSaveDialog(Stage stage){
		return fileChooser.showSaveDialog(stage);
	}
}
