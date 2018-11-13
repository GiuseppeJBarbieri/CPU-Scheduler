/*
 * 
 * Created By Giuseppe Barbieri
 * Cpu Scheduler App
 * Com 310-S01
 * 11/04/2018
 * 
 */

package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Show_Main_Screen;

public class App extends Application {

	/*
	 * This class is just used to start the GUI.
	 * 
	 * When the GUI is started it calls start method which will create a new instance of 
	 * the class Show_Main_Screen which loads the stage.
	 * 
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		new Show_Main_Screen(arg0);
	}

}
