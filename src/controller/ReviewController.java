package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Account;
import model.Dictionary;
import model.Word;

public class ReviewController {

	@FXML
	TableView<WordReview> reviewTable = new TableView<WordReview>();
	@FXML
	TableColumn<WordReview, String> contentColumn = new TableColumn<WordReview, String>();
	@FXML
	TableColumn<WordReview, String> classColumn = new TableColumn<WordReview, String>();
	@FXML
	TableColumn<WordReview, String> meaningColumn = new TableColumn<WordReview, String>();
	@FXML
	TableColumn<WordReview, Number> proficiencyColumn = new TableColumn<WordReview, Number>();
	
	@FXML
	ListView<String> dictionaryNameListView = new ListView<String>();
	
	@FXML
	ObservableList<String> dictionaryNameObservable = FXCollections.observableArrayList();
	@FXML
	ObservableList<WordReview> LearntListObservable = FXCollections.observableArrayList();
	
	private Account account = Account.getInstance();
	
	/**
	 * used to set up the review view
	 */
	@FXML
	public void initialize() {
		// set up the table 
		contentColumn.setCellValueFactory(new PropertyValueFactory<WordReview, String>("content"));
		classColumn.setCellValueFactory(new PropertyValueFactory<WordReview, String>("wordClass"));
		meaningColumn.setCellValueFactory(new PropertyValueFactory<WordReview, String>("meaning"));
		proficiencyColumn.setCellValueFactory(new PropertyValueFactory<WordReview, Number>("proficiency"));
		
		// show dictionary names in the ListView
		showListofDictionaries();
		
		// show the learned words of the selected dictionary in the TableView
		dictionaryNameListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	showLearntWords(newValue);
		    }
		});
		
	}
	
	/**
	 * used to show the dictionary names in the ListView
	 */
	private void showListofDictionaries() {
		dictionaryNameObservable.clear();
		for (int i = 0; i < account.getDictionaryList().size(); i++) {
			dictionaryNameObservable.add(account.getDictionaryList().get(i).getName());
		}
		dictionaryNameListView.setItems(dictionaryNameObservable);
	}
	
	/**
	 * used to show the learned words, i.e. the content, meaning & proficiency
	 * @param dictionaryName the name of the dictionary
	 */
	private void showLearntWords(String dictionaryName) {
		LearntListObservable.clear();
		Dictionary learntList = account.getDictionary(dictionaryName);
		
		String proficiency, correct, incorrect;	
		for (int i = 0; i < learntList.getDictionaryContent().size(); i++) {
			
			Word w = learntList.getWord(learntList.getDictionaryContent().get(i).getContent());
//			System.out.println(w.getCorrectGuesses() + "  " + w.getIncorrectGuesses());
			if(!(w.getCorrectGuesses() == 0 && w.getIncorrectGuesses() == 0)){			
				correct = Integer.toString(w.getCorrectGuesses());
				incorrect = Integer.toString(w.getIncorrectGuesses() + w.getCorrectGuesses());
				proficiency = correct + "/" + incorrect;
				LearntListObservable.add(new WordReview(w.getContent(), w.getWordClass(), w.getMeaning(), proficiency));
		
			}
		}
		reviewTable.setItems(LearntListObservable);
	}

}