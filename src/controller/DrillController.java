package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Account;
import model.ConnectionDB;
import model.Word;

public class DrillController {

	@FXML
	Label contentLabel;
	@FXML
	TextField answerText;
	@FXML
	Label answerLabel;
	@FXML
	Button nextButton;
	@FXML
	Button checkButton;

	private ArrayList<Word> quizList = new ArrayList<Word>();
	private ConnectionDB conn = ConnectionDB.getInstance();
	private Account account = Account.getInstance();
	public static int counter;

	/**
	 * used to setup the drill
	 */
	@FXML
	public void initialize() {
		quizList = Buffer.getInstance().getQuizList();
		contentLabel.setText(quizList.get(0).getContent());
		counter = 0;
	}
	
	@FXML
	public void onClickCheck(ActionEvent event) {
		Word word = quizList.get(counter);
		String answer = quizList.get(counter).getMeaning();
		if (answerText.getText().equals(answer)) {
			answerLabel.setText("correct!");
			word.incrementCorrectGuesses();
			conn.setCorrectGuesses(account.getUsername(), Buffer.getInstance().getDictionaryName(), word.getContent(), word.getCorrectGuesses());
			disableChecking();
		} else {
			answerLabel.setText("wrong! The answer is " + answer);
			word.incrementIncorrectGuesses();
			conn.setIncorrectGuesses(account.getUsername(), Buffer.getInstance().getDictionaryName(), word.getContent(), word.getIncorrectGuesses());
			disableChecking();
		}
		quizList.get(counter).setLearnt(true);
	}
	
	private void disableChecking() {
		checkButton.setDisable(true);
	
	}
	
	private void enableChecking(){
		checkButton.setDisable(false);
	}

	@FXML
	public void onClickNext(ActionEvent event) {
		counter++;
		enableChecking();
		if (quizList.size() > counter) {
			answerLabel.setText("");
			contentLabel.setText(quizList.get(counter).getContent());
			answerText.clear();
		} else {
			try {
				((Pane) Buffer.getInstance().getPane()).getChildren().add((Node) FXMLLoader.load(getClass().getResource("/view/DrillFinished.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}