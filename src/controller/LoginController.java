package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Account;
import model.ConnectionDB;

public class LoginController extends MainController {

	@FXML
	Label usernameLabel;
	@FXML
	Label passwordLabel;
	@FXML
	TextField usernameText;
	@FXML
	PasswordField passwordField;
	@FXML
	Button cancelButton;
	@FXML
	Button OKButton;
	@FXML
	Button signUpButton;
	@FXML
	Label usernameRequireLabel;
	@FXML
	Label passwordRequireLabel;
	@FXML
	Label loginLabel;

	private ConnectionDB conn = ConnectionDB.getInstance();
	private Account account = Account.getInstance();

	/**
	 * used to setup the login window
	 */
	@FXML
	public void initialize() {

	}

	/**
	 * used to cancel login and close the login window
	 * @param event
	 * unable cancel for sign up.
	 */
	/*@FXML
	public void onClickCancel(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}*/
	
	/**
	 * used to open a sign up window.
	 * 
	 */
	@FXML
	public void onClickSignUp(ActionEvent event) {
		Parent root;
		try {
			((Stage) OKButton.getScene().getWindow()).close();
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Linguist");
			Scene scene = new Scene(root, 800, 535);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			}
		}

	/**
	 * used to call startup() on ENTER pressed
	 * @param event
	 */
	@FXML
	public void buttonPressed(KeyEvent enter) {
		if (enter.getCode().toString().equals("ENTER")) {
			startup();
		}
	}

	/**
	 * used to call startup() on OK clicked
	 * @param event
	 */
	@FXML
	public void onClickOK(ActionEvent event) {
		startup();
	}
	
	/**
	 * used to check username & password and pass them to MainController
	 */
	private void startup() {
		loginLabel.setText("");
		boolean busername = FormValidation.textFieldNotEmpty(usernameText, usernameRequireLabel, "required!");
		boolean bpassword = FormValidation.passwordFieldNotEmpty(passwordField, passwordRequireLabel, "required!");
		if (busername && bpassword) {
			if (conn.matchUsernamePassword(usernameText.getText(), passwordField.getText())) {
				((Stage) OKButton.getScene().getWindow()).close();
				account.setUsername(usernameText.getText());
				account.setPassword(passwordField.getText());
				Parent root;
				try {
					root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
					Stage stage = new Stage();
					stage.setTitle("Linguist");
					Scene scene = new Scene(root, 800, 535);
					scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				loginLabel.setText("Invalid username or password!");
			}
		}
	}

}