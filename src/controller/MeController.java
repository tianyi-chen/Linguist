package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Account;
import model.ConnectionDB;

public class MeController {

	@FXML
	Label usernameLabel;
	@FXML
	Label passwordLabel;
	@FXML
	Label passwordRequireLabelFst;
	@FXML
	Label passwordRequireLabelSnd;
	@FXML
	Label passwordChangedLabel;
	@FXML
	Label passwordConfirmLabel;
	@FXML
	Label usernameshowLabel;
	@FXML
	Button logoutButton;
	@FXML
	Button changePasswordButton;
	// @FXML
	// TextField passwordTestField;
	@FXML
	PasswordField passwordField;
	@FXML
	PasswordField passwordConfirmField;

	private ConnectionDB conn = ConnectionDB.getInstance();
	private Account account = Account.getInstance();
	private String passwordFirst;
	private String passwordSecond;

	/**
	 * used to set the userName in Me when the Me button is clicked in MainView
	 */
	public void initialize() {
		usernameshowLabel.setText(account.getUsername());
	}

	/**
	 * used to change the password check user's new password by typing it two
	 * times password cannot be empty
	 *
	 * @param event
	 */
	public void onClickChangePassword() {
		// passwordField.setText(account.getUsername());
		passwordFirst = passwordField.getText();
		passwordSecond = passwordConfirmField.getText();
		boolean bpasswordFst = FormValidation.passwordFieldNotEmpty(passwordField, passwordRequireLabelFst,
				"required!");
		boolean bpasswordSnd = FormValidation.passwordFieldNotEmpty(passwordConfirmField, passwordRequireLabelSnd,
				"required!");
		if (!FormValidation.passwordFormatCorrect(passwordFirst)) {
			passwordChangedLabel.setText("Wrong password format.");
		} else {
			if ((passwordFirst.equals(passwordSecond)) && bpasswordFst && bpasswordSnd) {
				conn.changePassword(account.getUsername(), passwordField.getText());
				passwordChangedLabel.setText("password changed.");
			} else {
				passwordChangedLabel.setText("please ensure you typed same password");
			}
		}

	}

	/**
	 * used to change the user
	 * 
	 * @param event
	 */

	public void onClickLogOut(ActionEvent event) {
		try {
			Stage currentStage = (Stage) logoutButton.getScene().getWindow();
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