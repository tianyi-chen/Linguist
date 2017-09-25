package model;

public class Word {
	
	private String content;
	private String wordClass;
	private String meaning;
	private String example;
	private boolean isLearnt;
	private double proficiency;
	private int correctGuesses;
	private int incorrectGuesses;

	public Word(String content, String wordClass, String meaning, String example) {
		this.content = content;
		this.wordClass = wordClass;
		this.meaning = meaning;
		this.example = example;
		this.isLearnt = false;
		this.proficiency = 0.0;
		
	}
	
	public Word(String content, String wordClass, String meaning, String example, boolean isLearnt, double proficiency, int correctGuesses, int incorrectGuesses) {
		this.content = content;
		this.wordClass = wordClass;
		this.meaning = meaning;
		this.example = example;
		this.isLearnt = isLearnt;
		this.proficiency = proficiency;
		this.correctGuesses = correctGuesses;
		this.incorrectGuesses = incorrectGuesses;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWordClass() {
		return wordClass;
	}

	public void setWordClass(String wordClass) {
		this.wordClass = wordClass;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public boolean isLearnt() {
		return this.getProficiency() > 0.2;
	}

	public void setLearnt(boolean isLearnt) {
		this.isLearnt = isLearnt;
	}

	public double getProficiency() {
		double proficiency, totalGuesses;
		totalGuesses = this.getCorrectGuesses() + this.getCorrectGuesses();
		
		if(totalGuesses != 0){
			proficiency = this.correctGuesses / totalGuesses;
		}else{
			proficiency = 0;
		}
		return proficiency;
		 
	}

	private void setProficiency(double proficiency) {
		this.proficiency = proficiency;
	}

	public boolean contentEquals(Word word) {
		if(this.content.equals(word.getContent())) {
			return true;
		}
		return false;
	}

	public int getCorrectGuesses() {
		return correctGuesses;
	}

	public void incrementCorrectGuesses() {
		this.correctGuesses++;
	}

	public int getIncorrectGuesses() {
		return incorrectGuesses;
	}

	public void incrementIncorrectGuesses() {
		this.incorrectGuesses++;
	}
	
	public void resetGuesses(){
		this.incorrectGuesses = 0;
		this.correctGuesses = 0;
		
	}
}