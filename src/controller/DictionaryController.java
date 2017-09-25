package controller;

import java.io.File;
import java.io.IOException;
import controller.FormValidation;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Account;
import model.CommandAddWord;
import model.CommandDeleteWord;
import model.CommandImportDictionary;
import model.CommandRemoveDictionary;
import model.ConnectionDB;
import model.Dictionary;
import model.Word;

public class DictionaryController {

	@FXML
	TableView<WordView> dictionaryTable = new TableView<WordView>();
	@FXML
	TableColumn<WordView, String> contentColumn = new TableColumn<WordView, String>();
	@FXML
	TableColumn<WordView, String> classColumn = new TableColumn<WordView, String>();
	@FXML
	TableColumn<WordView, String> meaningColumn = new TableColumn<WordView, String>();
	@FXML
	TableColumn<WordView, String> exampleColumn = new TableColumn<WordView, String>();
	
	@FXML
	ListView<String> dictionaryNameListView = new ListView<String>();
	
	@FXML
	ObservableList<String> dictionaryNameObservable = FXCollections.observableArrayList();
	@FXML
	ObservableList<WordView> dictionaryContentObservable = FXCollections.observableArrayList();

	@FXML
	TextField dictionarySearch = new TextField();
	@FXML
	TextField wordSearch = new TextField();
	
	@FXML
	Button addButton, deleteButton, importButton, removeButton, exportButton;
	@FXML
	HBox wordManipulationButtons = new HBox();

	/**
	 * Label in AddWordWindow
	 */
	@FXML
	Label wordLabel, wordClassLabel, meaningLabel, exampleLabel, contentRequireLabel, classRequireLabel, meaningRequireLabel, exampleRequireLabel;
	/**
	 * TextField in AddWordWindow
	 */
	@FXML
	TextField wordText, wordClassText, meaningText, exampleText;
	/**
	 * Button in AddWordWindow
	 */
	@FXML
	Button cancelButton, OKButtonforAddWord;
	
	/**
	 * Label in ImportDictionaryWindow
	 */
	@FXML
	Label pathLabel, dictionaryNameLabel, filePathRequireLabel, dictionaryNameRequireLabel;
	/**
	 * TextField in ImportDictionaryWindow
	 */
	@FXML
	TextField filePathText, dictionaryNameText;
	/**
	 * Button in ImportDictionaryWindow
	 */
	@FXML
	Button OKButtonforImport, chooseFile;

	private IntegerProperty index = new SimpleIntegerProperty();

	private String dictionarySelected = new String();

	private ConnectionDB conn = ConnectionDB.getInstance();
	private Account account = Account.getInstance();
	private String username = account.getUsername();

	/**
	 * used to set up the dictionary view
	 */
	@FXML
	public void initialize() {
		wordManipulationButtons.setVisible(false);
		
		// set up the table
		index.set(-1);
		contentColumn.setCellValueFactory(new PropertyValueFactory<WordView, String>("content"));
		classColumn.setCellValueFactory(new PropertyValueFactory<WordView, String>("wordClass"));
		meaningColumn.setCellValueFactory(new PropertyValueFactory<WordView, String>("meaning"));
		exampleColumn.setCellValueFactory(new PropertyValueFactory<WordView, String>("example"));

		// show dictionary names in the ListView
		showListofDictionaries();

		// show the content of the selected dictionary in the TableView
		dictionaryNameListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				showDictionaryContent(newValue);
				dictionarySelected = newValue;
				wordManipulationButtons.setVisible(true);
			}
		});

		// get index of row clicked on in TableView
		dictionaryTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				index.set(dictionaryContentObservable.indexOf(newValue));
			}
		});

		// enable cell editing
		dictionaryTable.setEditable(true);
		classColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		classColumn.setOnEditCommit(new EventHandler<CellEditEvent<WordView, String>>() {
			@Override
			public void handle(CellEditEvent<WordView, String> t) {
				((WordView) t.getTableView().getItems().get(t.getTablePosition().getRow())).setMeaning(t.getNewValue());
				String content = dictionaryContentObservable.get(index.get()).getContent();
				account.getDictionary(dictionarySelected).getWord(content).setWordClass(t.getNewValue());
				conn.setWordClass(username, dictionarySelected, content, t.getNewValue());
			}
		});
		
		meaningColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		meaningColumn.setOnEditCommit(new EventHandler<CellEditEvent<WordView, String>>() {
			@Override
			public void handle(CellEditEvent<WordView, String> t) {
				((WordView) t.getTableView().getItems().get(t.getTablePosition().getRow())).setMeaning(t.getNewValue());
				String content = dictionaryContentObservable.get(index.get()).getContent();
				account.getDictionary(dictionarySelected).getWord(content).setMeaning(t.getNewValue());
				conn.setMeaning(username, dictionarySelected, content, t.getNewValue());
			}
		});

		exampleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		exampleColumn.setOnEditCommit(new EventHandler<CellEditEvent<WordView, String>>() {
			@Override
			public void handle(CellEditEvent<WordView, String> t) {
				((WordView) t.getTableView().getItems().get(t.getTablePosition().getRow())).setExample(t.getNewValue());
				String content = dictionaryContentObservable.get(index.get()).getContent();
				account.getDictionary(dictionarySelected).getWord(content).setExample(t.getNewValue());
				conn.setExample(username, dictionarySelected, content, t.getNewValue());
			}
		});	
		
		// search in ListView
		dictionarySearch.textProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				searchDictionary((String) oldValue, (String) newValue);
			}
		});
		
		// search in TableView
		wordSearch.textProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				searchWord((String) oldValue, (String) newValue);
			}
		});
		
	}
	
	/**
	 * used to search a dictionary in the ListView
	 * @param oldValue
	 * @param newValue
	 */
	private void searchDictionary(String oldValue, String newValue) {
	    if (oldValue != null && (newValue.length() < oldValue.length())) {
	    	dictionaryNameListView.setItems(dictionaryNameObservable);
	    }
	    String value = newValue.toUpperCase();
	    ObservableList<String> subentries = FXCollections.observableArrayList();
	    for (String entry : dictionaryNameListView.getItems()) {
	    	if (entry.toUpperCase().contains(value)) {
	    		subentries.add(entry);
	    	}
	    }
	    dictionaryNameListView.setItems(subentries);
	  }
	
	/**
	 * used to search a word/meaning in the TableView
	 * @param oldValue
	 * @param newValue
	 */
	private void searchWord(String oldValue, String newValue) {
	    if (oldValue != null && (newValue.length() < oldValue.length())) {
	    	dictionaryTable.setItems(dictionaryContentObservable);
	    }
	    String value = newValue.toUpperCase();
	    ObservableList<WordView> subentries = FXCollections.observableArrayList();
	    for (WordView entry : dictionaryTable.getItems()) {
	    	if (entry.getContent().toUpperCase().contains(value)) {
	    		subentries.add(entry);
	    	} else if(entry.getMeaning().toUpperCase().contains(value)) {
	    		subentries.add(entry);
	    	}
	    }
	    dictionaryTable.setItems(subentries);
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
	 * used to show the dictionary content, i.e. the content, meaning & example of words
	 * @param dictionaryName the name of the dictionary
	 */
	private void showDictionaryContent(String dictionaryName) {
		dictionaryContentObservable.clear();
		Dictionary dictionaryToShow = account.getDictionary(dictionaryName);
		for (int i = 0; i < dictionaryToShow.getDictionaryContent().size(); i++) {
			Word w = dictionaryToShow.getDictionaryContent().get(i);
			dictionaryContentObservable.add(new WordView(w.getContent(), w.getWordClass(), w.getMeaning(), w.getExample()));
		}
		dictionaryTable.setItems(dictionaryContentObservable);
	}

	/**
	 * used to show the add word window/ give a warning if no dictionary is selected
	 * @param event
	 */
	@FXML
	public void onClickAdd(ActionEvent event) {
		try {
			Buffer.getInstance().setDictionaryName(dictionarySelected);
			Buffer.getInstance().setDictionaryContentObservable(dictionaryContentObservable);
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddWordWindow.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Add a new word to dictionary");
			stage.setScene(new Scene(root, 500, 300));
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * used to cancel adding word/ cancel importing a dictionary, and close the window
	 * @param event
	 */
	@FXML
	public void onClickCancel(ActionEvent event) {
		((Stage) cancelButton.getScene().getWindow()).close();
	}

	/**
	 * used to call addWordOK on ENTER pressed
	 * @param enter
	 */
	@FXML
	public void enterPressedforAddWord(KeyEvent enter) {
		if (enter.getCode().toString().equals("ENTER")) {
			addWordOK();
		}
	}
	
	/**
	 * used to call addWordOK on OK clicked
	 * @param event
	 */
	@FXML
	public void onClickOKforAddWord(ActionEvent event) {
		addWordOK();
	}
	
	/**
	 * used to add word to the screen/ give a warning if word already exists
	 */
	private void addWordOK() {
		//table validation
		boolean bcontent = FormValidation.textFieldNotEmpty(wordText, contentRequireLabel, "required!");
		boolean bwordClass = FormValidation.textFieldNotEmpty(wordClassText, classRequireLabel, "required!");
		boolean bmeaning = FormValidation.textFieldNotEmpty(meaningText, meaningRequireLabel, "required!");
		boolean bexample = FormValidation.textFieldNotEmpty(exampleText, exampleRequireLabel, "required!");
		if (bcontent && bwordClass && bmeaning && bexample) {
			String content = wordText.getText();
			String wordClass = wordClassText.getText();
			String meaning = meaningText.getText();
			String example = exampleText.getText();
			Word word = new Word(content, wordClass, meaning, example);

			((Stage) OKButtonforAddWord.getScene().getWindow()).close();

			dictionarySelected = Buffer.getInstance().getDictionaryName();
			if (new CommandAddWord(conn, account, dictionarySelected, word).execute()) {
				dictionaryContentObservable = Buffer.getInstance().getDictionaryContentObservalbe();
				dictionaryContentObservable.add(new WordView(content, wordClass, meaning, example));
				dictionaryTable.setItems(dictionaryContentObservable);
			} else {
				Parent root;
				try {
					root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/AddWordFailure.fxml"));
					Stage stage = new Stage();
					stage.setTitle("Failed to add word!");
					stage.setScene(new Scene(root, 400, 300));
					stage.setResizable(false);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * used to delete word from dictionary
	 * @param event
	 */
	@FXML
	public void onClickDelete(ActionEvent event) {
		int i = index.get();
		if (i > -1) {
			WordView selectedRow = dictionaryContentObservable.get(i);
			new CommandDeleteWord(conn, account, dictionarySelected, selectedRow.getContent()).execute();
			dictionaryContentObservable.remove(i);
			dictionaryTable.getSelectionModel().clearSelection();
		}
	}

	/**
	 * used to import dictionary from a file
	 * @param event
	 */
	@FXML
	public void onClickImport(ActionEvent event) {
		Buffer.getInstance().setDictionaryNameObservable(dictionaryNameObservable);
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ImportDictionaryWindow.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Import a dictionary");
			stage.setScene(new Scene(root, 500, 300));
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * used to call importOK() on ENTER pressed
	 * @param event
	 */
	@FXML
	public void enterPressedforImport(KeyEvent enter) {
		if (enter.getCode().toString().equals("ENTER")) {
			importOK();
		}
	}

	/**
	 * used to call importOK() on OK clicked
	 * @param event
	 */
	@FXML
	public void onClickOKforImport(ActionEvent event) {
		importOK();
	}
	
	/**
	 * used to import a csv file as a dictionary into a database
	 */
	private void importOK() {
		boolean bfilePath = FormValidation.textFieldNotEmpty(filePathText, filePathRequireLabel, "required!");
		boolean bdictionaryName = FormValidation.textFieldNotEmpty(dictionaryNameText, dictionaryNameRequireLabel, "required!");
		if (bfilePath && bdictionaryName) {
			String filePath = filePathText.getText();
			String dictionaryName = dictionaryNameText.getText();
			((Stage) OKButtonforImport.getScene().getWindow()).close();
			if (new CommandImportDictionary(conn, account, dictionaryName, filePath).execute()) {
				dictionaryNameObservable = Buffer.getInstance().getDictionaryNameObservalbe();
				dictionaryNameObservable.add(dictionaryName);
				dictionaryNameListView.setItems(dictionaryNameObservable);
			}else{
				Parent root;
				try {
					root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ImportDictionaryFailure.fxml"));
					Stage stage = new Stage();
					stage.setTitle("Failed to import dictionary!");
					stage.setScene(new Scene(root, 400, 300));
					stage.setResizable(false);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * used to open up the file chooser window
	 * @param event
	 */
	@FXML
	public void onClickChooseFileButton(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open a csv File");
		Window mainStage = null;
		File selectedFile = fileChooser.showOpenDialog(mainStage);
		if (selectedFile != null) {
			filePathText.setText(selectedFile.toString());
		}
	}

	/**
	 * used to remove a dictionary
	 * @param event
	 */
	@FXML
	public void onClickRemove(ActionEvent event) {
		if (dictionarySelected.length() == 0) {
			Parent root;
			try {
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/NoDictionarySeleted.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Warning!");
				stage.setScene(new Scene(root, 400, 300));
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			new CommandRemoveDictionary(conn, account, dictionarySelected).execute();
			dictionaryNameListView.getItems().remove(dictionaryNameListView.getSelectionModel().getSelectedIndex());
		}
	}
	
}