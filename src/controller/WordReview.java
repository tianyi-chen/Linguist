package controller;

import javafx.beans.property.SimpleStringProperty;

public class WordReview {
	
	private SimpleStringProperty content;
	private SimpleStringProperty wordClass;
	private SimpleStringProperty meaning;
	private SimpleStringProperty proficiency;

	public WordReview(String content, String wordClass, String meaning, String proficiency) {
		this.content = new SimpleStringProperty(content);
		this.wordClass = new SimpleStringProperty(wordClass);
		this.meaning = new SimpleStringProperty(meaning);
		this.proficiency = new SimpleStringProperty(proficiency);
	}

	public String getContent() {
		return content.get();
	}

	public void setContent(String content) {
		this.content.set(content);
	}
	
	public String getWordClass() {
		return wordClass.get();
	}

	public void setWordClass(String wordClass) {
		this.wordClass.set(wordClass);
	}

	public String getMeaning() {
		return meaning.get();
	}

	public void setMeaning(String meaning) {
		this.meaning.set(meaning);
	}

	public String getProficiency() {
		return proficiency.get();
	}

	public void setProficiency(String proficiency) {
		this.proficiency.set(proficiency);
	}

}
