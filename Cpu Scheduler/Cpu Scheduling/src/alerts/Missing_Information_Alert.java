package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Missing_Information_Alert {

	public Missing_Information_Alert(String s1) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Detected!");
		alert.setHeaderText(s1);
		alert.setContentText("Click ok to continue.");
		alert.showAndWait();
	}
}
