package model;

import java.util.ArrayList;
import java.util.Random;

public class UnlearntList {
	
	private ArrayList<Word> unlearntList;
	
	public UnlearntList () {
		unlearntList = new ArrayList<Word>();
	}
	
	public void addWord(Word word) {
		for (Word w : unlearntList) {
			if (word.contentEquals(w)) {
				return;
			}
		}
		if (!word.isLearnt()) {
			unlearntList.add(word);
		}
	}
	
	public void removeWord(String content) {
		int index = 0;
		for (Word w : unlearntList) {
			if (w.getContent().equals(content)) {
				break;
			}
			index++;
		}
		if (index >= unlearntList.size()) {
			return;
		}
		unlearntList.remove(index);
	}
	
	public ArrayList<Word> generateDrillWords(int number) {
		if (number >= unlearntList.size()){
			return unlearntList;
		}
		ArrayList<Word> drillWords = new ArrayList<Word>();
		int indices[] = generateRandomNumber(number);
		for (int i = 0; i< indices.length; i++) {
			drillWords.add(unlearntList.get(indices[0]));
			unlearntList.remove(indices[0]);
		}
		return drillWords;
	}
	
	private int[] generateRandomNumber(int number) {
		Random random = new Random ();
		int[] randomNumbers = new int[number];
        boolean[]  bool = new boolean[number];
        int randInt = 0;
        for (int j = 0; j < number ; j++) {
            do {
                randInt  = random.nextInt(number);
            } while(bool[randInt]); 
            bool[randInt] = true;
            randomNumbers[j] = randInt;
        }  
		return randomNumbers;
	}
	
	public ArrayList<Word> getUnlearntWords() {
		return unlearntList;
	}

}
