package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import model.Account;
import model.CommandGameScrambler;
import model.ConnectionDB;


public class ScramblerInitialiseController {

	@FXML
	ChoiceBox<String> choiceBox;

	private ConnectionDB conn = ConnectionDB.getInstance();
	private Account account = Account.getInstance();

	@FXML
	public void initialize() {
		choiceBox.getItems().addAll(conn.getDictionaryNames(account.getUsername()));
	}	

	@FXML
	public void onClickStart(ActionEvent event) {
		CommandGameScrambler scramble = new CommandGameScrambler(conn, account, choiceBox.getValue().toString());
		if (scramble.execute()) {
			try {
				Buffer.getInstance().setDictionaryName(choiceBox.getValue());
				((Pane) Buffer.getInstance().getPane()).getChildren()
						.add(FXMLLoader.load(getClass().getResource("/view/GameScrambler.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
