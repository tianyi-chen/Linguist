package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Account;
import model.CommandDrill;
import model.ConnectionDB;

public class DrillInitilizeController {

	@FXML
	Button startButton;
	@FXML
	ChoiceBox<String> choiceBox;
	@FXML
	Label amountLabel;
	@FXML
	Label fromLabel;
	@FXML
	TextField amountText;
	@FXML
	TextField dictionaryNameText;
	@FXML
	Label dictionaryRequiredLabel;
	@FXML
	Label amountRequiredLabel;
	
	private ConnectionDB conn = ConnectionDB.getInstance();
	private Account account = Account.getInstance();

	/**
	 * used to setup the drill initializer
	 */
	@FXML
	public void initialize() {
		choiceBox.getItems().addAll(conn.getDictionaryNames(account.getUsername()));
	}

	@FXML
	public void onClickStart(ActionEvent event) {
		boolean dictionaryNotEmpty = FormValidation.choiceBoxNotEmpty(choiceBox, dictionaryRequiredLabel, "required!");
		boolean amountNotEmpty = FormValidation.textFieldNotEmpty(amountText, amountRequiredLabel, "required!");
		boolean amountInCorrctFormat = FormValidation.amountInCorrctFormat(amountText, amountRequiredLabel, "Invalid amount!");
		if(amountNotEmpty&&dictionaryNotEmpty&&amountInCorrctFormat){
			CommandDrill cmd = new CommandDrill(conn, account, choiceBox.getValue(),
					Integer.parseInt(amountText.getText()));
			if (cmd.execute()) {
				try {
					Buffer.getInstance().setQuizList(cmd.getQuizList());
					Buffer.getInstance().setDictionaryName(choiceBox.getValue());
					((Pane) Buffer.getInstance().getPane()).getChildren()
							.add(FXMLLoader.load(getClass().getResource("/view/Drill.fxml")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}