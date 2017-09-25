package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import model.Account;
import model.CommandGameScrambler;
import model.ConnectionDB;

public class GameController {
	private ConnectionDB conn = ConnectionDB.getInstance();
	private Account account = Account.getInstance();
	CommandGameScrambler scramble = new CommandGameScrambler(conn, account, Buffer.getInstance().getDictionaryName());
	
	
	@FXML
	TextField translatedWordText;
	
	@FXML
	Text displayedWord;
	
	@FXML
	Text console;
	
	@FXML
	Button next;
	
	@FXML
	Button check;
	
	@FXML
	public void initialize(){
		//promptBox.setText("Unshuffle the word");
		scramble.setEnglishWord();
		scramble.setTranslatedWord();
		scramble.shuffleWord(scramble.getTranslatedWord());
		//translatedWordText.setPromptText("Enter Word Here");
		displayedWord.setText(scramble.getShuffledWord());
		console.setVisible(false);
		translatedWordText.clear();
		translatedWordText.requestFocus();
		//translatedWordText.setVisible(false);
	}
	
	
	
	public void handleEnterOnTranslate(KeyEvent isEnter){
		if (isEnter.getCode() == KeyCode.ENTER) {
			translate();
		}
	}
	
	public void translate(){
		console.setVisible(true);
		String inputText = translatedWordText.getText();
		String translatedWord = scramble.getTranslatedWord();
		if(!translatedWord.equals(inputText)){
			System.out.println("Try Again");
			console.setText("Try Again");
			translatedWordText.clear(); 
		}else{
			console.setText("Correct, " + translatedWord + " can be translated into " +  scramble.getEnglishWord() +".");
			nextWord();
		}
	}
	
	public void nextWord(){
		scramble.setEnglishWord();
		scramble.setTranslatedWord();
		translatedWordText.clear();
		translatedWordText.requestFocus();
		scramble.shuffleWord(scramble.getTranslatedWord());
		displayedWord.setText(scramble.getShuffledWord());
	}
	
}
