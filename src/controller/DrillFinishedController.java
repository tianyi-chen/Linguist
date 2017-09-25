package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DrillFinishedController {

	@FXML
	Label finishedLabel;
	@FXML
	Button restartButton;
	
	/**
	 * used to setup the drill finished view
	 */
	@FXML
	public void initialize() {

	}
	
	@FXML
	public void onClickRestart(ActionEvent event) {
		Pane content = (Pane) Buffer.getInstance().getPane();
		content.getChildren().clear();
		try {
			DrillController.counter = 0;
			content.getChildren().add(FXMLLoader.load(getClass().getResource("/view/DrillInitialize.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}