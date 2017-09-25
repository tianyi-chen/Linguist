package controller;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FormValidation {

	public static boolean textFieldNotEmpty(TextField tf) {
		if (tf.getText() != null && !tf.getText().isEmpty()) {
			return true;
		}
		return false;
	}
	public static boolean choiceBoxNotEmpty(ChoiceBox<String> cb) {
		if (cb.getValue() != null && !cb.getValue().isEmpty()) {
			return true;
		}
		return false;
	}
	public static boolean amountIsInteger(TextField tf) {
		 try { 
		        Integer.parseInt(tf.getText()); 
		    } catch(NumberFormatException e) { 
		        return false; 
		    }
		if (Integer.parseInt(tf.getText())>0) {
			return true;
		}
		return false;
	}

	
	public static boolean textFieldNotEmpty(TextField tf, Label l, String validationText) {
		if (!textFieldNotEmpty(tf)) {
			l.setText(validationText);
			return false;
		}
		l.setText(null);
		return true;
	}

	public static boolean passwordFieldNotEmpty(PasswordField pf, Label l, String validationText) {
		if (!textFieldNotEmpty(pf)) {
			l.setText(validationText);
			return false;
		}
		l.setText(null);
		return true;
	}
	
	public static boolean choiceBoxNotEmpty(ChoiceBox<String> cb, Label l, String validationText) {
		if (!choiceBoxNotEmpty(cb)) {
			l.setText(validationText);
			return false;
		}
		l.setText(null);
		return true;
	}
	
	public static boolean amountInCorrctFormat(TextField tf, Label l, String validationText) {
		if (!amountIsInteger(tf)) {
			l.setText(validationText);
			return false;
		}
		l.setText(null);
		return true;
	}

	public static boolean passwordFormatCorrect(String password) {
		boolean isAtLeast8 = password.length() >= 8;
		// Checks for at least 8 characters
		boolean hasSpecial = !password.matches("[A-Za-z0-9 ]*");
		// Checks at least one char is not alpha numeric
		boolean noConditions = !(password.contains("AND") || password.contains("NOT"));
		if (isAtLeast8 && hasSpecial && noConditions) {
			return true;
		}
		return false;
	}

}