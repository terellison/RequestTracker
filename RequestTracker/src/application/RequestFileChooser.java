package application;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * The Class RequestFileChooser.
 * Provides a static FileChooser object to be
 * used throughout the program in order to keep the settings
 * of the current FileChooser session. 
 * 
 * @author Chaz-Rae L. Moncrieffe
 * @since 3/1/2017
 */
public class RequestFileChooser {
	private static RequestFileChooser requestFileChooser;
	private FileChooser fileChooser;

	/**
	 * Gets the instance of the RequestFileChooser class.
	 *
	 * @return the RequestFileChooser
	 */
	public static RequestFileChooser get(){
		if(requestFileChooser == null){
			requestFileChooser = new RequestFileChooser();
		}
		return requestFileChooser;
	}
	
	/**
	 * Instantiates a new RequestFileChooser.
	 */
	private RequestFileChooser(){
		fileChooser = new FileChooser();
		ExtensionFilter textFilter = new ExtensionFilter("TXT files (*.txt)", "*.txt");
		ExtensionFilter wordFilter = new ExtensionFilter("All files (*.*)", ".*");
		fileChooser.getExtensionFilters().addAll(textFilter, wordFilter);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the String
	 */
	public void setTitle(String title){
		fileChooser.setTitle(title);
	}
	
	/**
	 * Sets the initial file name.
	 *
	 * @param fileName the new String
	 */
	public void setInitialFileName(String fileName){
		fileChooser.setInitialFileName(fileName);
	}
	
	/**
	 * Shows save dialog.
	 *
	 * @param stage the Stage
	 * @return the file
	 */
	public File showSaveDialog(Stage stage){
		return fileChooser.showSaveDialog(stage);
	}
}
