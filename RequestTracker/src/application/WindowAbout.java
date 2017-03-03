package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The Class WindowAbout.
 * 
 * Creates a stage that will show
 * an About message with basic info 
 * about the creator of the program.
 * 
 * @author Chaz-Rae L. Moncrieffe
 * @since 3/1/2017
 */
public class WindowAbout {
	private Stage stage;
	private Scene scene;
	
	/**
	 * Instantiates a new WindowAbout.
	 *
	 * @param primaryStage the Stage
	 */
	public WindowAbout(Stage primaryStage){
		stage = new Stage();
		scene = new Scene(setUpScene(), 450, 200);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("About Request Tracker");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(new Image(getClass().getResource("images/Repository-16.png").toExternalForm()));
		stage.initOwner(primaryStage);
	}
	
	/**
	 * Show.
	 * 
	 * Calls showAndWait function of Stage.
	 */
	public void Show(){
		stage.showAndWait();
	}
	
	/**
	 * Sets up the scene.
	 * 
	 * Creates controls and sets them
	 * in the GridPane
	 *
	 * @return the GridPane
	 */
	private GridPane setUpScene(){
		GridPane root = new GridPane();
		root.setPadding(new Insets(20));
		root.setHgap(25);
		root.setVgap(15);
		
		ImageView imgIcon = new ImageView(new Image(getClass().getResource("images/Repository_100.png").toExternalForm()));
		Text txtAbout = new Text("Request Tracker 2017\nChaz-Rae L. Moncrieffe\nCOP2940 Computer Programming Internship\n\nSee the resources for this program at\nhttps://github.com/LunaKB/RequestTracker");
		
		root.add(imgIcon, 0, 0);
		root.add(txtAbout, 1, 0);
	
		return root;
	}
}
