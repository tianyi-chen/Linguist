package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CommandGameScrambler extends AbstractCommand {
	String englishWord;
	String translatedWord;
	//String wordToBeUsed = getEnglishWord();
	//will use dictornary to pick word and its translation
	//String wordToBeUsedGerman = getTranslatedWord();
	String shuffledWord;
	private String dictionaryName;
	Dictionary dictionary;
	Random rand = new Random();
	int randomNum;
	
	
	
	public CommandGameScrambler(ConnectionDB conn, Account account, String dictionaryName) {
		super(conn, account);
		this.dictionaryName = dictionaryName;
		new ArrayList<Word>();
	}
	
//	public static void main(String[] args){
//		String wordToBeUsed = "Apple";
//		//will use dictornary to pick word and its translation
//		String wordToBeUsedGerman = "Apfel";
//		/*ArrayList<Character> letters = new ArrayList<Character>(wordToBeUsed.length());  //creates an array list of the letters in the word the length of the word
//		for(char i : wordToBeUsed.toCharArray()){
//			letters.add(i);
//		}
//		Collections.shuffle(letters);
//		//shuffles the letters
//		char[]shuffled = new char[letters.size()]; //
//		for(int i=0 ; i<shuffled.length ; i++){
//			shuffled[i] = letters.get(i);
//		}
//		String shuffledWord = new String(shuffled); //restringing the letters*/
//		//String shuffledWord = new CommandGameScrambler().shuffleWord("Apple");
//		System.out.println("Unshuffle the word:");
//		System.out.println(shuffledWord);
//		Scanner scan1 = new Scanner(System.in);
//		String answer1 = scan1.nextLine();
//		
//		
//		if(answer1.equals(wordToBeUsed)){
//		System.out.println("CORRECT ANSWER!!");
//		System.out.println("What is the translation of "+wordToBeUsed+"?");
//		Scanner scan2 = new Scanner(System.in);
//		String answer2 = scan2.nextLine();
//		if(answer2.equals(wordToBeUsedGerman)){
//		System.out.println("CORRECT ANSWER!!");
//		}else{
//		System.out.println("incorrect.");
//		}
//		}else{
//		System.out.println("incorrect.");
//		}
//	}
	
	public String shuffleWord(String word){
		ArrayList<Character> letters = new ArrayList<Character>(word.length());  //creates an array list of the letters in the word the length of the word
		for(char i : word.toCharArray()){
			letters.add(i);
		}
		Collections.shuffle(letters);
		//shuffles the letters
		char[]shuffled = new char[letters.size()]; //
		for(int i=0 ; i<shuffled.length ; i++){
			shuffled[i] = letters.get(i);
		}
		shuffledWord = new String(shuffled);
		return shuffledWord;
	}
	
	public String getShuffledWord(){
		return shuffledWord;
	}
	
	public String getEnglishWord(){
		return englishWord;
	}
	
	public void setEnglishWord(){
		execute();
		randomNum = rand.nextInt(dictionary.getUnlearntList().getUnlearntWords().size() - 1);
		englishWord = dictionary.getUnlearntList().getUnlearntWords().get(randomNum).getMeaning();
	}
	
	public void setTranslatedWord(){
		execute();
		translatedWord = dictionary.getUnlearntList().getUnlearntWords().get(randomNum).getContent();
	}
	
	public String getTranslatedWord(){
		return translatedWord;
	}

	@Override
	public boolean execute() {
	 dictionary = account.getDictionary(dictionaryName);
		if (dictionary.getDictionaryContent().size() == 0) {
			return false;
		}
		return true;
	}
	
//	public void getRandomWord(){
//		wordSelection.add(e)
//		System.out.println("HEEEEEEEYYYYY " + dictionary.get(4).getContent());
//	}
}
