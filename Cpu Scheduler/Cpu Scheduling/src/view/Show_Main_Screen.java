/*
 * 
 * Created By Giuseppe Barbieri
 * Cpu Scheduler App
 * Com 310-S01
 * 11/04/2018
 * 
 */

package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Show_Main_Screen {

	/*
	 * This class takes the parameter Stage and first loads the fxml class which holds the information for the main screen view.
	 * It then creates the scene, and then shows the stage.
	 * 
	 * For each view I like to create 3 classes.
	 * 		1.) Main_Screen_View.fxml holds the fxml code for the GUI.
	 * 		2.) Show_Main_Screen which basically loads the stage and sets a controller to it. 
	 * 		3.) Main_Screen_View_Controller controls all the functions of the view.
	 * 
	 */
	public Show_Main_Screen(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main_Screen_View.fxml"));
			AnchorPane  root = loader.load();
			@SuppressWarnings("unused")
			Main_Screen_View_Controller controller = loader.getController();
			Scene scene = new Scene(root, 1134, 411);
			scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
