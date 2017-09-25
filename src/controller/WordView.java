package controller;

import javafx.beans.property.SimpleStringProperty;

public class WordView {
	
	private SimpleStringProperty content;
	private SimpleStringProperty wordClass;
	private SimpleStringProperty meaning;
	private SimpleStringProperty example;

	public WordView(String content, String wordClass, String meaning, String example) {
		this.content = new SimpleStringProperty(content);
		this.wordClass = new SimpleStringProperty(wordClass);
		this.meaning = new SimpleStringProperty(meaning);
		this.example = new SimpleStringProperty(example);
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

	public String getExample() {
		return example.get();
	}

	public void setExample(String example) {
		this.example.set(example);
	}

}