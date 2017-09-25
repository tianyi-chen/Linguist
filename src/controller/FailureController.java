package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FailureController {

	@FXML
	Label failureLabel;
	@FXML
	Button iSeeButton;
	
	@FXML
	Label formatLabel;
	@FXML
	Label rowLabel;
	
	/**
	 * used to setup the "failed to add word"/ "failed to import dictionary" warning
	 */
	@FXML
	public void initialize() {

	}
	
	@FXML
	public void onClickISee(ActionEvent event) {
		((Stage) iSeeButton.getScene().getWindow()).close();
	}
}