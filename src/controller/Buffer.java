package controller;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import model.Word;

public class Buffer {

	private static Buffer instance;

	public static Buffer getInstance() {
		if (instance == null) {
			instance = new Buffer();
		}   	
	    return instance;
	}
	
	private String dictionaryName;
	
	@FXML
	private ObservableList<WordView> dictionaryContentObservable = FXCollections.observableArrayList();
	@FXML
	private ObservableList<String> dictionaryNameObservable = FXCollections.observableArrayList();
	
	@FXML
	Pane content;
	
	private ArrayList<Word> quizList = new ArrayList<Word>();
	
	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}
	
	public String getDictionaryName() {
		return dictionaryName;
	}

	public void setDictionaryContentObservable(ObservableList<WordView> dictionaryContentObservable) {
		this.dictionaryContentObservable = dictionaryContentObservable;
	}

	public ObservableList<WordView> getDictionaryContentObservalbe() {
		return dictionaryContentObservable;
	}

	public void setDictionaryNameObservable(ObservableList<String> dictionaryNameObservable) {
		this.dictionaryNameObservable = dictionaryNameObservable;
	}

	public ObservableList<String> getDictionaryNameObservalbe() {
		return dictionaryNameObservable;
	}

	public void setPane(Pane content) {
		this.content = content;
	}

	public Parent getPane() {
		return content;
	}

	public void setQuizList(ArrayList<Word> quizList) {
		this.quizList = quizList;
	}

	public ArrayList<Word> getQuizList() {
		return quizList;
	}

}
