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

public class SignUpController {

	@FXML
	Label usernameLabel;
	@FXML
	Label passwordLabel;
	@FXML
	Label emailLabel;
	@FXML
	Label signUpLabel;
	@FXML
	Label usernameRequireLabel;
	@FXML
	Label passwordRequireLabel;


	@FXML
	TextField emailField;
	@FXML
	TextField usernameText;
	@FXML
	PasswordField passwordField;
	@FXML
	PasswordField passwordconfirmField;
	@FXML	
	Button confirmButton;
	@FXML
	Button backButton;
	
	private ConnectionDB conn = ConnectionDB.getInstance();
	private Account account = Account.getInstance();
	private String password;
	private String confirmPassword;

	/**
	 * used to setup the login window
	 */
	@FXML
	public void initialize() {

	}

	@FXML
	public void onConfirm(ActionEvent event) {
		startup();

	}
	
	/**
	 * used to check username & password and pass them to MainController
	 */
	private void startup() {
		signUpLabel.setText("");
		password = passwordField.getText();
		confirmPassword = passwordconfirmField.getText();
		boolean busername = FormValidation.textFieldNotEmpty(usernameText, usernameRequireLabel, "required!");
		boolean bpassword = FormValidation.passwordFieldNotEmpty(passwordField, passwordRequireLabel, "required!");
		boolean bconfirmPassword = FormValidation.passwordFieldNotEmpty(passwordconfirmField, passwordRequireLabel, "required!");
		conn.createNewUser(usernameText.getText(), password);
		if (busername && bpassword&&bconfirmPassword&&(password.equals(confirmPassword))) {
			if (conn.matchUsernamePassword(usernameText.getText(), passwordField.getText())) {
				((Stage) confirmButton.getScene().getWindow()).close();
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
				signUpLabel.setText("password shold longer than 8 and contain special characters!");
			}
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

	
	public void onClickBack(ActionEvent event) {
		try {
			Stage currentStage = (Stage) backButton.getScene().getWindow();
			currentStage.close();
			account.logout();
			Parent root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/LoginWindow.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Log in to Linguist");
			stage.setScene(new Scene(root, 500, 300));
			stage.setResizable(false);
			stage.show();
			// Platform.exit();
			// : -)
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
