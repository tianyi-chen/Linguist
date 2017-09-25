package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class GameSelectionController {
	@FXML
	Button scramblerButton;
	
	@FXML
	public void onClickScrambler(){
		Pane content = (Pane) Buffer.getInstance().getPane();
		content.getChildren().clear();
		try {
			content.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ScramblerInitialize.fxml")));
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
//		System.out.println("Scrambler was pressed");
	}
}
