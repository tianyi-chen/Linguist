package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WarningController {
	
	@FXML
	Label warningLabel;
	@FXML
	Button iSeeButton;
	
	/**
	 * used to setup the "no dictionary selected" warning
	 */
	@FXML
	public void initialize() {

	}
	
	@FXML
	public void onClickISee(ActionEvent event) {
		((Stage) iSeeButton.getScene().getWindow()).close();
	}

}