package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.Account;
import model.ConnectionDB;

public class MainController {

	@FXML
	Parent dictionaryContent;
	@FXML
	DictionaryController dictionaryController = new DictionaryController();

	@FXML
	Pane content;

	@FXML
	Button dictionaryButton;
	@FXML
	Button drillButton;
	@FXML
	Button gameButton;
	@FXML
	Button reviewButton;
	@FXML
	Button meButton;

	private ConnectionDB conn = ConnectionDB.getInstance();
	private Account account = Account.getInstance();
	private String username = account.getUsername();

	/**
	 * used to setup the account
	 */
	@FXML
	public void initialize() {
		// get dictionaries from database, save a copy in the account
		ArrayList<String> dictionaryNames = conn.getDictionaryNames(username);
		if (dictionaryNames.size() != 0) {
			for (int i = 0; i < dictionaryNames.size(); i++) {
				String dictionaryName = dictionaryNames.get(i);
				account.addDictionary(dictionaryName);
				account.setDictionary(dictionaryName, conn.getDictionary(username + dictionaryName));
			}
		}

		// show the dictionary view
		dictionaryController.initialize();
		try {
			content.getChildren().add(FXMLLoader.load(getClass().getResource("/view/DictionaryContent.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * used to load the DictionaryContent in MainView
	 * @param event
	 */
	@FXML
	public void onClickDictionary(ActionEvent event) {
		content.getChildren().clear();
		try {
			content.getChildren().add(FXMLLoader.load(getClass().getResource("/view/DictionaryContent.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * used to load the ReviewContent in MainView
	 * @param event
	 */
	@FXML
	public void onClickReview(ActionEvent event) {
		content.getChildren().clear();
		try {
			content.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ReviewContent.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * used to load the DrillInitilize in MainView
	 * @param event
	 */
	@FXML
	public void onClickDrill(ActionEvent event) {
		content.getChildren().clear();
		try {
			content.getChildren().add(FXMLLoader.load(getClass().getResource("/view/DrillInitialize.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Buffer.getInstance().setPane(content);
	}

	@FXML
	public void onClickMe(ActionEvent event) {
		content.getChildren().clear();
		try {
			content.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Me.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Buffer.getInstance().setPane(content);
	}

	@FXML
	public void onClickGame(ActionEvent event) {
		content.getChildren().clear();
		try {
			content.getChildren().add(FXMLLoader.load(getClass().getResource("/view/GameSelection.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Buffer.getInstance().setPane(content);
	}

}